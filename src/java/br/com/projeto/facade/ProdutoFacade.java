
package br.com.projeto.facade;

import br.com.projeto.dao.ProdutoDAO;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class ProdutoFacade extends AbstractFacade<ProdutoDAO> {

    @PersistenceContext(unitName = "PROJETO_TCCPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProdutoFacade() {
        super(ProdutoDAO.class);
    }
    
}
