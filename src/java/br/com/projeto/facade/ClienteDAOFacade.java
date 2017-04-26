package br.com.projeto.facade;

import br.com.projeto.dao.ClienteDAO;
import br.com.projeto.dao.UsuarioDAO;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;


@Stateless
public class ClienteDAOFacade extends AbstractFacade<ClienteDAO> {

    @PersistenceContext(unitName = "PROJETO_TCCPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        if (em == null){
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("PROJETO_TCCPU");
            em = factory.createEntityManager();
        }
        return em;
    }

    public ClienteDAOFacade() {
        super(ClienteDAO.class);
    }

    public ClienteDAO FindByUsuario(UsuarioDAO usuarioDAO) throws NoResultException {
        getEntityManager();
        ClienteDAO cliente;
        try {
            cliente = (ClienteDAO) em.createNamedQuery("ClienteDAO.findByUsuario")
                    .setParameter("idusuario", usuarioDAO.getIdusuario()).getSingleResult();
        } catch (NoResultException e) {
            throw new NoResultException("Cliente não encontrado");
        }
        return cliente;
    }
}
