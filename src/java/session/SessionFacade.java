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
import userIdEntity.Session;

/**
 *
 * @author tezk
 */
@Stateless
public class SessionFacade extends AbstractFacade<Session> {

    @PersistenceContext(unitName = "IDTrackerServerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SessionFacade() {
        super(Session.class);
    }
    
    public Session findBySessionId(String sessionId) {
        Query query = em.createNamedQuery("Session.findBySessionid");
        query.setParameter("sessionid", sessionId);
        List <Session> list = query.getResultList();
        if (list !=null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
    
    public List <Session> findByUserId(int id) {
        Query query = em.createNamedQuery("Session.findByUserId");
        query.setParameter("userId", id);
        
        return query.getResultList();
    }
}
