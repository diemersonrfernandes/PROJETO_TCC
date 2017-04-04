/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.facade;

import br.com.projeto.dao.UsuarioDAO;
import br.com.projeto.modelo.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Antonio Augusto
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<UsuarioDAO> {

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

    public UsuarioFacade() {
        super(UsuarioDAO.class);
    }
    
    public UsuarioDAO findUsuarioByName(String nmUsuario) throws NoResultException{
            //log.debug("Obtendo Usuario com o nome: " + nmLogin);

        getEntityManager();
        UsuarioDAO usuario;
        try {
                usuario = (UsuarioDAO) em.createNamedQuery("UsuarioDAO.findByNmusuario")
                                .setParameter("nmusuario", nmUsuario).getSingleResult();
        }catch (NoResultException e){
                throw new NoResultException("Usuário "+ nmUsuario + " não encontrado");
        }
        return usuario;
    }
}
