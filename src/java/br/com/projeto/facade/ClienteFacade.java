/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.facade;

import br.com.projeto.dao.ClienteDAO;
import br.com.projeto.dao.UsuarioDAO;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/**
 *
 * @author prohgy
 */
@Stateless
public class ClienteFacade extends AbstractFacade<ClienteDAO> {

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

    public ClienteFacade() {
        super(ClienteDAO.class);
    }
    
     public ClienteDAO FindByUsuario(int idUsuario) {
        getEntityManager();
        ClienteDAO cliente;
        try {
                cliente = (ClienteDAO) em.createNamedQuery("ClienteDAO.findByUsuario")
                                .setParameter("idusuario", idUsuario).getSingleResult();
        }catch (NoResultException e){
                throw new NoResultException("Usuário "+ idUsuario + " não encontrado");
        }
        return cliente;
    }
}
