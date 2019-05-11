/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import IDTrackerTO.EventTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author tezk
 */
public class EventUtility {
    public static EventTO getAsTO(Event anEvent) {
        EventTO newEvent = new EventTO();
        newEvent.setCustomerIsAttendingEventCollection(CustomerIsAttendingEventUtility.getAsTO(anEvent.getCustomerIsAttendingEventCollection()));
        newEvent.setEndDate(anEvent.getEndData());
        newEvent.setEventPriceCollection(EventPriceUtility.getAsTO(anEvent.getEventPriceCollection()));
        newEvent.setEventTitle(anEvent.getEventTitle());
        newEvent.setId(anEvent.getId());
        newEvent.setStartDate(anEvent.getStartDate());
        newEvent.setAttendees(anEvent.getCustomerIsAttendingEventCollection().size());
        return newEvent;
    }
    
    public static Event getAsEntity(EventTO anEvent) {
        Event newEvent = new Event();
        
        newEvent.setCustomerIsAttendingEventCollection(CustomerIsAttendingEventUtility.getAsEntity(anEvent.getCustomerIsAttendingEventCollection()));
        newEvent.setCustomerIsAttendingEventCollection(new HashSet());
        newEvent.setEndData(anEvent.getEndDate());
        newEvent.setEventPriceCollection(EventPriceUtility.getAsEntity(anEvent.getEventPriceCollection()));
        newEvent.setEventTitle(anEvent.getEventTitle());
        newEvent.setId(anEvent.getId());
        newEvent.setStartDate(anEvent.getStartDate());
        newEvent.setCustomerIsAttendingEventCollection(new HashSet());
        
        return newEvent;
    }
    
    public static Collection <EventTO> getAsTO(Collection <Event> events) {
        Collection <EventTO> newCollection = new ArrayList();
        
        for (Event eachEvent : events) 
            newCollection.add(getAsTO(eachEvent));
        
        return newCollection;
    }
    
     public static Collection <Event> getAsEntity(Collection <EventTO> events) {
        Collection <Event> newCollection = new ArrayList();
        
        for (EventTO eachEvent : events) 
            newCollection.add(getAsEntity(eachEvent));
        
        return newCollection;
    }   
}
