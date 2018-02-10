/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import userIdEntity.User;

/**
 *
 * @author tezk
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "IDTrackerServerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
    
    public User findByUserId(Integer userId) {
        Query query = em.createNamedQuery("User.findByIdusers");
        query.setParameter("idusers", userId);
        List <User> list = query.getResultList();
        if (list!=null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public User findByUsername(String username) {
        Query query = em.createNamedQuery("User.findByUsername");
        query.setParameter("username", username);
        List <User> list = query.getResultList();
        if (list!=null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
}
