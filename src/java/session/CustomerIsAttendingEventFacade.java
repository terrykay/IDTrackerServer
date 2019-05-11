/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.CustomerIsAttendingEvent;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author tezk
 */
@Stateless
public class CustomerIsAttendingEventFacade extends AbstractFacade<CustomerIsAttendingEvent> {

    @PersistenceContext(unitName = "IDTrackerServerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CustomerIsAttendingEventFacade() {
        super(CustomerIsAttendingEvent.class);
    }
    
}
