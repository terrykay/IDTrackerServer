/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import IDTrackerTO.CustomerIsAttendingEventTO;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author tezk
 */
public class CustomerIsAttendingEventUtility {

    public static CustomerIsAttendingEventTO getAsTO(CustomerIsAttendingEvent anAttendance) {
        CustomerIsAttendingEventTO newAttendance = new CustomerIsAttendingEventTO();

        newAttendance.setBookedIn(anAttendance.getBookedIn());
        newAttendance.setBookedOut(anAttendance.getBookedOut());
        newAttendance.setCancelled(anAttendance.getCancelled());
        newAttendance.setCustomerId(anAttendance.getCustomer().getId());
//        newAttendance.setEvent(EventUtility.getAsTO(anAttendance.getEvent()));
        newAttendance.setEventId(anAttendance.getEvent().getId());
        newAttendance.setEventPrice(EventPriceUtility.getAsTO(anAttendance.getEventPriceAgreed()));
        newAttendance.setInvoicenumber(InvoiceUtility.getAsTO(anAttendance.getInvoicenumber()));
        newAttendance.setOrderDate(anAttendance.getOrderDate());

        return newAttendance;
    }

    public static Collection<CustomerIsAttendingEventTO> getAsTO(Collection<CustomerIsAttendingEvent> attendences) {
        Collection<CustomerIsAttendingEventTO> newIs = new ArrayList();

        for (CustomerIsAttendingEvent eachAtt : attendences) {
            newIs.add(getAsTO(eachAtt));
        }

        return newIs;
    }

    public static CustomerIsAttendingEvent getAsEntity(CustomerIsAttendingEventTO anAttendance) {
        CustomerIsAttendingEvent newAttendance = new CustomerIsAttendingEvent();

        newAttendance.setBookedIn(anAttendance.getBookedIn());
        newAttendance.setBookedOut(anAttendance.getBookedOut());
        newAttendance.setCancelled(anAttendance.getCancelled());
        //newAttendance.setCustomer(customerFacade.findByID(anAttendance.getCustomerId()));
        //System.out.println("Event = "+anAttendance.getEvent());
       // newAttendance.setEvent(EventUtility.getAsEntity(anAttendance.getEvent()));
       // newAttendance.setEventId(anAttendance.getEvent().getId());
        newAttendance.setEventPriceAgreed(EventPriceUtility.getAsEntity(anAttendance.getEventPrice()));
        newAttendance.setInvoicenumber(InvoiceUtility.getAsEntity(anAttendance.getInvoicenumber()));
        newAttendance.setOrderDate(anAttendance.getOrderDate());
 
        CustomerIsAttendingEventPK customerIsAttendingEventPK = new CustomerIsAttendingEventPK();
        customerIsAttendingEventPK.setCustomerId(anAttendance.getCustomerId());
        customerIsAttendingEventPK.setEventId(anAttendance.getEventId());
        newAttendance.setCustomerIsAttendingEventPK(customerIsAttendingEventPK);
        
        return newAttendance;
    }

    public static Collection<CustomerIsAttendingEvent> getAsEntity(Collection<CustomerIsAttendingEventTO> attendences) {
        Collection<CustomerIsAttendingEvent> newIs = new ArrayList();

        if (attendences != null) {
            for (CustomerIsAttendingEventTO eachAtt : attendences) {
                newIs.add(getAsEntity(eachAtt));
            }
        }

        return newIs;
    }
}
