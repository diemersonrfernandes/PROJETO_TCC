package br.com.projeto.controle;

import br.com.projeto.dao.PedidoDAO;
import br.com.projeto.controle.util.JsfUtil;
import br.com.projeto.controle.util.PaginationHelper;
import br.com.projeto.dao.ClienteDAO;
import br.com.projeto.dao.ItemPedidoDAO;
import br.com.projeto.dao.ProdutoDAO;
import br.com.projeto.dao.UsuarioDAO;
import br.com.projeto.facade.ClienteDAOFacade;
import br.com.projeto.facade.ItemPedidoFacade;
import br.com.projeto.facade.PedidoFacade;
import br.com.projeto.modelo.Carrinho;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Named("pedidoBean")
@RequestScoped
public class PedidoBean implements Serializable {

    @EJB
    private ItemPedidoFacade itemPedidoFacade;

    private PedidoDAO current;
    private DataModel items = null;
    @EJB
    private br.com.projeto.facade.PedidoFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    private ClienteDAO clienteDAO;
    private int quantidade;
    //private ProdutoDAO produtoDAO;
    List<Integer> quantidades;

    public List<Integer> getQuantidades() {
        quantidades = new ArrayList<Integer>();
        for (int i = 1 ; i < 11 ;i++ ){
            quantidades.add(i);
        }
        return quantidades;
    }

    public void setQuantidades(List<Integer> quantidades) {
        this.quantidades = quantidades;
    }

//    public ProdutoDAO getProdutoDAO() {
//        return produtoDAO;
//    }
//
//    public void setProdutoDAO(ProdutoDAO produtoDAO) {
//        this.produtoDAO = produtoDAO;
//    }
         
    public PedidoBean() {
    }
    
    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public PedidoDAO getSelected() {
        if (current == null) {
            current = new PedidoDAO();
            selectedItemIndex = -1;
        }
        return current;
    }

    private PedidoFacade getFacade() {
        return ejbFacade;
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
//                    DataModel dataModel = 
//                            new ListDataModel(getFacade().
//                                    findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                    getCliente();
                    DataModel dataModel = 
                            new ListDataModel(getFacade().findAllByCliente(clienteDAO));
                    return  dataModel;
                }
            };
        }
        return pagination;
    }

    ////
    
    
    public void adicionaCarrinho(ProdutoDAO produtoDAO, int qtde) {

//        Object get = FacesContext.getCurrentInstance().getViewRoot().
//                findComponent("formManter:qtde1");//.getAttributes().get("label");
//        
//        Object get2 = FacesContext.getCurrentInstance().getExternalContext().
//                        getRequestParameterMap().get("formManter:qtde1");
        
        Carrinho carrinho = new Carrinho();
        carrinho.setProduto(produtoDAO);
        carrinho.setQuantidade(qtde);
    
        List<Carrinho> listaCarrinho = new ArrayList<Carrinho>();

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

        if (attr.getRequest().getSession().getAttribute("carrinhoSessao") == null) {
            listaCarrinho.add(carrinho);
        } else {
            listaCarrinho = (List<Carrinho>) attr.getRequest().getSession().getAttribute("carrinhoSessao");
            listaCarrinho.add(carrinho);
        }

        attr.getRequest().getSession(true);
        attr.getRequest().getSession().setAttribute("carrinhoSessao", listaCarrinho);

        JsfUtil.addSuccessMessage("Pedido adicionado com sucesso");
    }

    public List<Carrinho> getCarrinho() {

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

        List<Carrinho> listaCarrinho
                = (List<Carrinho>) attr.getRequest().getSession().getAttribute("carrinhoSessao");

        return listaCarrinho;
    }

    public double getCarrinhoSomaValor() {

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        List<Carrinho> listaCarrinho
                = (List<Carrinho>) attr.getRequest().getSession().getAttribute("carrinhoSessao");

        double soma = 0; //new BigDecimal("0");
        for (Carrinho carrinho : listaCarrinho) {
            soma += carrinho.getProduto().getValor().doubleValue();
        }
        return soma;
    }

    public void removeCarrinho() {

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        attr.getRequest().getSession().removeAttribute("carrinhoSessao");
    }
    
    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (PedidoDAO) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new PedidoDAO();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            List<Carrinho> listaCarrinho
                    = (List<Carrinho>) attr.getRequest().getSession().getAttribute("carrinhoSessao");

            double soma = 0; //new BigDecimal("0");
            for (Carrinho carrinho : listaCarrinho) {
                soma += carrinho.getProduto().getValor().doubleValue();
            }
            
            getCliente();
            
            
            //Salva o pedido
            if (current == null) {
                current = new PedidoDAO();
            }
            current.setQuantidade(listaCarrinho.size());
            current.setValor(new BigDecimal(soma));
            current.setIdcliente(clienteDAO);
            current.setDtpedido(new Date());
            getFacade().create(current);
            
            
            for (Carrinho carrinho : listaCarrinho) {
                ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();
                itemPedidoDAO.setIdpedido(current);
                itemPedidoDAO.setIdproduto(carrinho.getProduto());
                itemPedidoDAO.setQuantidade(carrinho.getQuantidade());
                           
                itemPedidoFacade.create(itemPedidoDAO);
            }
            
            //itemPedidoFacade.flush();
            JsfUtil.addSuccessMessage("Pedido criado com sucesso");
            
            removeCarrinho();
            
            return null;
            
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "Erro ao criar pedido");
            return null;
        }
    }
    
    private ClienteDAO getCliente(){
        if (clienteDAO == null){
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            UsuarioDAO usuariodao = (UsuarioDAO)attr.getRequest().getSession().getAttribute("usuario");
            clienteDAO = new ClienteDAOFacade().FindByUsuario(usuariodao);
        }
        
        return clienteDAO;
    }
    public String prepareEdit() {
        current = (PedidoDAO) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PedidoDAOUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (PedidoDAO) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PedidoDAODeleted"));
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

    public PedidoDAO getPedidoDAO(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = PedidoDAO.class)
    public static class PedidoDAOControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PedidoBean controller = (PedidoBean) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "pedidoBean");
            return controller.getPedidoDAO(getKey(value));
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
            if (object instanceof PedidoDAO) {
                PedidoDAO o = (PedidoDAO) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + PedidoDAO.class.getName());
            }
        }

    }

}
