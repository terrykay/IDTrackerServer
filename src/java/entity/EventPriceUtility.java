/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import IDTrackerTO.EventPriceTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 *
 * @author tezk
 */
public class EventPriceUtility {

    public static EventPriceTO getAsTO(EventPrice aPrice) {
        EventPriceTO newPrice = new EventPriceTO();
        newPrice.setId(aPrice.getId());
        newPrice.setCost(aPrice.getCost());
       // newPrice.setCustomerIsAttendingEventCollection(CustomerIsAttendingEventUtility.getAsTO(aPrice.getCustomerIsAttendingEventCollection()));
        newPrice.setDescription(aPrice.getDescription());
        // No need to navigate from EventPrice to Event
        //newPrice.setEventCollection(EventUtility.getAsTO(aPrice.getEventCollection()));

        return newPrice;
    }

    public static Collection<EventPriceTO> getAsTO(Collection<EventPrice> prices) {
        Collection<EventPriceTO> newPrices = new ArrayList();

        for (EventPrice eachPrice : prices) {
            newPrices.add(getAsTO(eachPrice));
        }

        return newPrices;
    }

    public static EventPrice getAsEntity(EventPriceTO aPrice) {
        EventPrice newPrice = new EventPrice();
        if (aPrice == null)
            return newPrice;
        newPrice.setId(aPrice.getId());
        newPrice.setCost(aPrice.getCost());
       // newPrice.setCustomerIsAttendingEventCollection(CustomerIsAttendingEventUtility.getAsEntity(aPrice.getCustomerIsAttendingEventCollection()));
        newPrice.setDescription(aPrice.getDescription());
        // No need to navigate from EventPrice to Event


        return newPrice;
    }

    public static Collection<EventPrice> getAsEntity(Collection<EventPriceTO> prices) {
        Collection<EventPrice> newPrices = new ArrayList();

        if (prices != null) {
            for (EventPriceTO eachPrice : prices) {
                newPrices.add(getAsEntity(eachPrice));
            }
        }

        return newPrices;
    }
}
