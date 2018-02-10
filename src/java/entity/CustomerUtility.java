/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import IDTrackerTO.CustomerTO;
import IDTrackerTO.VisitTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import session.CustomerFacade;

/**
 *
 * @author tezk
 */
public class CustomerUtility {
    
    public static CustomerTO getAsTO(Customer aCustomer) {
        
        CustomerTO newCustomer = new CustomerTO();
        
        newCustomer.setId(aCustomer.getId());
        
       
        newCustomer.setForename(aCustomer.getForename());
        newCustomer.setMiddlenames(aCustomer.getMiddlenames());
        newCustomer.setSurname(aCustomer.getSurname());
        newCustomer.setDob(aCustomer.getDob());
        newCustomer.setEmail(aCustomer.getEmail());
        newCustomer.setGiftAid(aCustomer.getGiftAid());
        newCustomer.setHobbies(aCustomer.getHobbies());
        newCustomer.setTelephoneOne(aCustomer.getTelephoneOne());
        newCustomer.setTelephoneTwo(aCustomer.getTelephonetwo());
        newCustomer.setOccupation(aCustomer.getOccupation());
        newCustomer.setAddressId(AddressUtility.getAsTO(aCustomer.getAddressId()));
        newCustomer.setMembership(MembershipUtility.getAsTO(aCustomer.getMembershipId()));
        // Customer is responsible for setting links to Address (and Addresses links to Customer)
        newCustomer.setCarCollection(CarUtility.getAsTO(aCustomer.getCarCollection()));
        newCustomer.setChildCollection(ChildUtility.getAsTO(aCustomer.getChildCollection()));
        newCustomer.setImageCollection(ImageUtility.getAsTO(aCustomer.getImageCollection()));
        newCustomer.setPartnerId(aCustomer.getPartnerId()==null?0:aCustomer.getPartnerId().getId());
        if (aCustomer.getRefuse() != null)
            newCustomer.setRefuse(RefuseUtility.getAsTO(aCustomer.getRefuse()));
        newCustomer.setNotes(NotesUtility.getAsTO(aCustomer.getNotesCollection()));
        newCustomer.setNextOfKin(NextOfKinUtility.getAsTO(aCustomer.getNextOfKinCollection()));
        
        if (aCustomer.getAllowPhotographyCollection() !=null && aCustomer.getAllowPhotographyCollection().size()>0) {
            AllowPhotography ap = (AllowPhotography)aCustomer.getAllowPhotographyCollection().toArray()[0];
            newCustomer.setPhotography(ap.getAllowPhotography());
        } else newCustomer.setPhotography(false);
        
        List <VisitTO> visitList = new ArrayList();
        visitList.addAll(VisitUtility.getAsTO(new ArrayList(aCustomer.getVisitCollection())));
        newCustomer.setVisitCollection(visitList);
        
        return newCustomer;
    }
    
    public static Collection <CustomerTO> getAsTO(Collection <Customer> customers) {
        //Collection <Customer> customerList = anAddress.getCustomerCollection();
        Collection <CustomerTO> newCustomerList = new ArrayList();
        for (Customer eachCustomer : customers) {
            newCustomerList.add(getAsTO(eachCustomer));
        }
        return newCustomerList;
    }
    
    public static Customer getAsEntity(CustomerTO aCustomer) {
        Customer newCustomer = new Customer();
        
        newCustomer.setId(aCustomer.getId());
        newCustomer.setForename(aCustomer.getForename());
        newCustomer.setMiddlenames(aCustomer.getMiddlenames());
        newCustomer.setSurname(aCustomer.getSurname());
        newCustomer.setDob(aCustomer.getDob());
        newCustomer.setEmail(aCustomer.getEmail());
        newCustomer.setGiftAid(aCustomer.getGiftAid());
        newCustomer.setHobbies(aCustomer.getHobbies());
        newCustomer.setTelephoneOne(aCustomer.getTelephoneOne());
        newCustomer.setTelephonetwo(aCustomer.getTelephoneTwo());
        newCustomer.setOccupation(aCustomer.getOccupation());
        newCustomer.setAddressId(AddressUtility.getAsEntity(aCustomer.getAddressId())); 
        if (aCustomer.getRefuse()!=null)
            newCustomer.setRefuse(RefuseUtility.getAsEntity(aCustomer.getRefuse()));
        if (aCustomer.getMembership()!= null) {
            newCustomer.setMembershipId(MembershipUtility.getAsEntity(aCustomer.getMembership()));
            //newCustomer.getMembershipId().getCustomerCollection().add(newCustomer);
            
        }
        
        newCustomer.setNotesCollection(NotesUtility.getAsEntity(aCustomer.getNotes()));
        for (Notes eachNote : newCustomer.getNotesCollection()) {
            eachNote.setCustomerId(newCustomer);
        }
        newCustomer.setNextOfKinCollection(NextOfKinUtility.getAsEntity(aCustomer.getNextOfKin()));
        for (NextOfKin eachNOK : newCustomer.getNextOfKinCollection()) {
            eachNOK.setCustomerId(newCustomer);
        }
        
        newCustomer.setCarCollection(CarUtility.getAsEntity(aCustomer.getCarCollection()));
        for (Car eachCar : newCustomer.getCarCollection()) {
            // Can't have left at null!
            eachCar.setCustomerId(newCustomer);
        }
        
        newCustomer.setChildCollection(ChildUtility.getAsEntity(aCustomer.getChildCollection()));
        for (Child eachChild : newCustomer.getChildCollection()) {
            // Can't have left at null!
            if (eachChild.getCustomerCollection()==null)
                eachChild.setCustomerCollection(new ArrayList());
            eachChild.getCustomerCollection().add(newCustomer);
        }
        
        newCustomer.setImageCollection(ImageUtility.getAsEntity(aCustomer.getImageCollection()));
        for (Image eachImage : newCustomer.getImageCollection()) {
            // Can't have left at null!
            eachImage.setCustomerId(newCustomer);
        }
        
        // Make sure every customer gets one of these records
        AllowPhotography allowPhoto = new AllowPhotography();
        allowPhoto.setAllowPhotography(aCustomer.getPhotography());
        allowPhoto.setCustomerId(newCustomer);
        newCustomer.setAllowPhotographyCollection(new ArrayList());
        newCustomer.getAllowPhotographyCollection().add(allowPhoto);
        
        List <Visit> visitList = new ArrayList();
        visitList.addAll(VisitUtility.getAsEntity(new ArrayList(aCustomer.getVisitCollection())));
        for (Visit eachVisist : visitList) {
            eachVisist.setCustomerId(newCustomer);
        }
        newCustomer.setVisitCollection(visitList);
        
        return newCustomer;
    }
    
    public static CustomerTO getAsDisplayTO(Customer aCustomer) {
        /* Return a subset of data, ie:
            No visits
            No address
            No next of kin
            No allow photography
       
        */
        CustomerTO newCustomer = new CustomerTO();
        
        newCustomer.setId(aCustomer.getId());
        
       
        newCustomer.setForename(aCustomer.getForename());
        newCustomer.setMiddlenames(aCustomer.getMiddlenames());
        newCustomer.setSurname(aCustomer.getSurname());
        newCustomer.setDob(aCustomer.getDob());
        newCustomer.setEmail(aCustomer.getEmail());
        newCustomer.setGiftAid(aCustomer.getGiftAid());
        newCustomer.setHobbies(aCustomer.getHobbies());
        newCustomer.setTelephoneOne(aCustomer.getTelephoneOne());
        newCustomer.setTelephoneTwo(aCustomer.getTelephonetwo());
        newCustomer.setOccupation(aCustomer.getOccupation());
       // newCustomer.setAddressId(AddressUtility.getAsTO(aCustomer.getAddressId()));
        newCustomer.setMembership(MembershipUtility.getAsTO(aCustomer.getMembershipId()));
        // Customer is responsible for setting links to Address (and Addresses links to Customer)
        newCustomer.setCarCollection(CarUtility.getAsTO(aCustomer.getCarCollection()));
        newCustomer.setChildCollection(ChildUtility.getAsTO(aCustomer.getChildCollection()));
        newCustomer.setImageCollection(ImageUtility.getAsTO(aCustomer.getImageCollection()));
        newCustomer.setPartnerId(aCustomer.getPartnerId()==null?0:aCustomer.getPartnerId().getId());
        if (aCustomer.getRefuse() != null)
            newCustomer.setRefuse(RefuseUtility.getAsTO(aCustomer.getRefuse()));
        newCustomer.setNotes(NotesUtility.getAsTO(aCustomer.getNotesCollection()));
        //newCustomer.setNextOfKin(NextOfKinUtility.getAsTO(aCustomer.getNextOfKinCollection()));
        
        //if (aCustomer.getAllowPhotographyCollection() !=null && aCustomer.getAllowPhotographyCollection().size()>0) {
        //    AllowPhotography ap = (AllowPhotography)aCustomer.getAllowPhotographyCollection().toArray()[0];
        //    newCustomer.setPhotography(ap.getAllowPhotography());
        //} else newCustomer.setPhotography(false);
        
        //List <VisitTO> visitList = new ArrayList();
        //visitList.addAll(VisitUtility.getAsTO(new ArrayList(aCustomer.getVisitCollection())));
        //newCustomer.setVisitCollection(visitList);
        
        return newCustomer;
    }
    
    public static Collection <CustomerTO> getAsDisplayTO(Collection <Customer> customers) {
        //Collection <Customer> customerList = anAddress.getCustomerCollection();
        Collection <CustomerTO> newCustomerList = new ArrayList();
        for (Customer eachCustomer : customers) {
            newCustomerList.add(getAsDisplayTO(eachCustomer));
        }
        return newCustomerList;
    }
}
