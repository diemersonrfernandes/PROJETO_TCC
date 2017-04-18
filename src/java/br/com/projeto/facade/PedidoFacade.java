/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.facade;

import br.com.projeto.dao.ClienteDAO;
import br.com.projeto.dao.PedidoDAO;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Antonio Augusto
 */
@Stateless
public class PedidoFacade extends AbstractFacade<PedidoDAO> {

    @PersistenceContext(unitName = "PROJETO_TCCPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<PedidoDAO> findAllByCliente(ClienteDAO cliente) {
        getEntityManager();
        List<PedidoDAO> pedido;
        try {
                pedido = (List<PedidoDAO>) em.createNamedQuery("PedidoDAO.findByCliente")
                                .setParameter("cliente", cliente).getResultList();
        }catch (NoResultException e){
                throw new NoResultException("Cliente não encontrado");  
        }
        return pedido;
    }
    public PedidoFacade() {
        super(PedidoDAO.class);
    }
    
}
