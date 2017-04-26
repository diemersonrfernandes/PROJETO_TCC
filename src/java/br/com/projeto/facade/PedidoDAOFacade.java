package br.com.projeto.facade;

import br.com.projeto.dao.PedidoDAO;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class PedidoDAOFacade extends AbstractFacade<PedidoDAO> {

    @PersistenceContext(unitName = "PROJETO_TCCPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PedidoDAOFacade() {
        super(PedidoDAO.class);
    }
    
}
