package br.com.projeto.facade;

import br.com.projeto.dao.ItemPedidoDAO;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class ItemPedidoFacade extends AbstractFacade<ItemPedidoDAO> {

    @PersistenceContext(unitName = "PROJETO_TCCPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ItemPedidoFacade() {
        super(ItemPedidoDAO.class);
    }
    
}
