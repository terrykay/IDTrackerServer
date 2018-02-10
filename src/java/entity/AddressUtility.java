/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import IDTrackerTO.AddressTO;
import IDTrackerTO.CustomerTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 *
 * @author tezk
 */
public class AddressUtility {
    public static AddressTO getAsTO(Address anAddress) {
        if (anAddress == null)
            return null;
        
        AddressTO newAddress = new AddressTO();
        
        newAddress.setId(anAddress.getId());
        newAddress.setAddressLineOne(anAddress.getAddressLineOne());
        newAddress.setAddressLineTwo(anAddress.getAddressLineTwo());
        newAddress.setTown(anAddress.getTown());
        newAddress.setCounty(anAddress.getCounty());
        newAddress.setPostcode(anAddress.getPostcode());
        newAddress.setCountry(anAddress.getCountry());
        // Customer is respinsible for Customer relationship, use only ID, not full object
        if (anAddress.getCustomerCollection().size() > 0)
        for (Customer eachCustomer : anAddress.getCustomerCollection()) {
            newAddress.getCustomerCollection().add(eachCustomer.getId());
        }

        return newAddress;
    }
    
    public static Address getAsEntity(AddressTO anAddress) {
        if (anAddress == null)
            return null;
        
        Address newAddress = new Address();
        
        newAddress.setId(anAddress.getId());
        newAddress.setAddressLineOne(anAddress.getAddressLineOne());
        newAddress.setAddressLineTwo(anAddress.getAddressLineTwo());
        newAddress.setTown(anAddress.getTown());
        newAddress.setCounty(anAddress.getCounty());
        newAddress.setPostcode(anAddress.getPostcode());
        newAddress.setCountry(anAddress.getCountry());
        newAddress.setCustomerCollection(new HashSet());

        return newAddress;
    }
    
    public static Collection <AddressTO> getAsTO(Collection <Address> addresses) {
        //Collection <Address> customerList = anAddress.getAddressCollection();
        Collection <AddressTO> newAddressList = new ArrayList();
        for (Address eachAddress : addresses) {
            newAddressList.add(AddressUtility.getAsTO(eachAddress));
        }
        return newAddressList;
    }
    
        public static Collection <Address> getAsEntity(Collection <AddressTO> addresses) {
        //Collection <Address> customerList = anAddress.getAddressCollection();
        Collection <Address> newAddressList = new ArrayList();
        for (AddressTO eachAddress : addresses) {
            newAddressList.add(AddressUtility.getAsEntity(eachAddress));
        }
        return newAddressList;
    }
}
