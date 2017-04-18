package br.com.projeto.controle;

import br.com.projeto.dao.ClienteDAO;
import br.com.projeto.controle.util.JsfUtil;
import br.com.projeto.controle.util.PaginationHelper;
import br.com.projeto.dao.PerfilDAO;
import br.com.projeto.dao.UsuarioDAO;
import br.com.projeto.facade.ClienteDAOFacade;
import br.com.projeto.facade.UsuarioFacade;
import br.com.projeto.util.facesUtil;
import java.io.IOException;

import java.io.Serializable;
import java.util.Map;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.persistence.NoResultException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Named("clienteDAOController")
@RequestScoped
public class ClienteBEAN implements Serializable {

    private ClienteDAO current;
    private UsuarioDAO currentUsuario;
    private DataModel items = null;
    @EJB
    private ClienteDAOFacade ejbFacade;
    @EJB
    private UsuarioFacade usuarioFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public ClienteBEAN() {
        Map<String, String> parameterMap = (Map<String, String>) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        if (parameterMap != null && Boolean.valueOf(parameterMap.get("editCliente"))) {
            prepareEdit();
        }
    }

    public ClienteDAO getSelected() {
        if (current == null) {
            current = new ClienteDAO();
            selectedItemIndex = -1;
        }
        return current;
    }

    public UsuarioDAO getSelectedUsuario() {
        if (currentUsuario == null) {
            currentUsuario = new UsuarioDAO();
        }
        return currentUsuario;
    }

    private ClienteDAOFacade getFacade() {
        if (ejbFacade == null)
            ejbFacade = new ClienteDAOFacade();
        return ejbFacade;
    }

    public String create(int idPerfil) throws IOException {
        try {
            if (getSelected().getId() != null) {
                getFacade().edit(current);
            } else {

                PerfilDAO perfil = new PerfilDAO();
                perfil.setIdperfil(idPerfil); // 3 = Cliente
                currentUsuario.setIdperfil(perfil);

                usuarioFacade.create(currentUsuario);

                current.setIdusuario(currentUsuario);

                getFacade().create(current);
            }
            facesUtil.adiconarMsgInfo("Cadastro realizado com sucesso!");
//            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
//            context.redirect(context.getRequestContextPath() + "pedido.xhtml?faces-redirect=true");
            return "pedido.xhtml?faces-redirect=true";
        } catch (Exception e) {
            facesUtil.adicionarMsgErro("Erro ao realizar cadastro!");
            return null;
        }
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (ClienteDAO) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new ClienteDAO();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ClienteDAOCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        UsuarioDAO usuario = (UsuarioDAO) attr.getRequest().getSession().getAttribute("usuario");

        if (usuario == null) {
            facesUtil.adicionarMsgErro("É preciso logar para continuar está operação!");
            return null;
        } else if (!usuario.getIdperfil().getNmperfil().equals("Cliente")) {
            facesUtil.adicionarMsgErro("É preciso possuir perfl Cliente para alterar seus dados!");
            return null;
        }
        current = getFacade().FindByUsuario(usuario);
        current.setIdusuario(currentUsuario);

        current = (ClienteDAO) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();

        return "manterCliente";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ClienteDAOUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (ClienteDAO) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ClienteDAODeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public ClienteDAO getClienteDAO(java.lang.Integer id) {
        return ejbFacade.find(id);

    }

    @FacesConverter(forClass = ClienteDAO.class)
    public static class ClienteDAOControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ClienteBEAN controller = (ClienteBEAN) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "clienteDAOController");
            return controller.getClienteDAO(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof ClienteDAO) {
                ClienteDAO o = (ClienteDAO) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + ClienteDAO.class.getName());
            }
        }

    }

}
