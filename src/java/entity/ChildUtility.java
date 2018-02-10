/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import IDTrackerTO.ChildTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author tezk
 */
public class ChildUtility {
    public static ChildTO getAsTO(Child aChild) {
        ChildTO newChild = new ChildTO();
        
        newChild.setId(aChild.getId());
        newChild.setForename(aChild.getForename());
        newChild.setSurname(aChild.getSurname());
        newChild.setDob(aChild.getDob());
        // Child relationship controlled by Customer class
    //    newChild.setCustomerId(aChild.getCustomerId().getId());
        return newChild;
    }
    
    public static Collection <ChildTO> getAsTO(Collection <Child> childList) {
        List <ChildTO> newList = new ArrayList();
        for (Child eachChild : childList) {
            newList.add(getAsTO(eachChild));
        }
        return newList;
    }
    
    public static Child getAsEntity(ChildTO aChild) {
        Child newChild = new Child();
        
        newChild.setId(aChild.getId());
        newChild.setForename(aChild.getForename());
        newChild.setSurname(aChild.getSurname());
        newChild.setDob(aChild.getDob());
        newChild.setCustomerCollection(new HashSet());
        // Child relationship controlled by Customer class
        //newChild.setCustomerId(customerId);
        return newChild;
    }
    
        public static Collection <Child> getAsEntity(Collection <ChildTO> childList) {
        List <Child> newList = new ArrayList();
        for (ChildTO eachChild : childList) {
            newList.add(getAsEntity(eachChild));
        }
        return newList;
    }
}
