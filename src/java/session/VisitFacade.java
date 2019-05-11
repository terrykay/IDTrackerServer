/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Customer;
import entity.Visit;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author tezk
 */
@Stateless
public class VisitFacade extends AbstractFacade<Visit> {

    @PersistenceContext(unitName = "IDTrackerServerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VisitFacade() {
        super(Visit.class);
    }
    
    public List<Visit> findByVisitDate(Date visitDate) {
        Query q = em.createNamedQuery("Visit.findByStartDate");
        q.setParameter("startDate", visitDate);
        List<Visit> results = q.getResultList();
        return results;
    }
    
    public List<Visit> findByVisitDate(Date startDate, Date endDate) {
        Query q = em.createNamedQuery("Visit.findByStartBetween");
        q.setParameter("startDate", startDate);
        q.setParameter("endDate", endDate);
        List<Visit> results = q.getResultList();
        return results;
    }
}
