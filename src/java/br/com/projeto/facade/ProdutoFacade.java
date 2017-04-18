/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.facade;

import br.com.projeto.dao.ProdutoDAO;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Antonio Augusto
 */
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
