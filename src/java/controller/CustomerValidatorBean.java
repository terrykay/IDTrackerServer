/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Car;
import entity.Child;
import entity.Customer;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import session.CustomerFacade;

/**
 * Validate that the customer is correctly defined within the database by
 * checking partner links
 *
 * @author tezk
 */
@Stateless
public class CustomerValidatorBean {

    @EJB
    private CustomerFacade customerFacade;

    public Customer validate(Customer who) {
        
        Customer validating = customerFacade.findByID(who.getId());
        Customer partner = validating.getPartnerId();

        // Check kid collections
        try {
        if (partner != null) {
            if (partner.getPartnerId()!= null && !validating.equals(partner.getPartnerId())) {
                // Partners not set reciprocately
                Customer partnersPartner = partner.getPartnerId();
                if (partnersPartner.getPartnerId() != null) {
                    partnersPartner.setPartnerId(null);
                }
            }
            partner.setPartnerId(validating);

            if (who.getChildCollection().size() > partner.getChildCollection().size()) {
                partner.setChildCollection(who.getChildCollection());
            }
            if (who.getChildCollection().size() < partner.getChildCollection().size()) {
                who.setChildCollection(partner.getChildCollection());
            }
            System.out.println("b4 = "+who.getCarCollection().size());
            for (Car eachCar : who.getCarCollection())
                System.out.println("car = "+eachCar.getRegno()+" = "+eachCar.getIdcar());
            customerFacade.edit(who);
            System.out.println("af = "+who.getCarCollection().size());
           // customerFacade.edit(partner);
            System.out.println("2f = "+who.getCarCollection().size());
            for (Car eachCar : who.getCarCollection())
                System.out.println("car = "+eachCar.getRegno()+" = "+eachCar.getIdcar());
        }
        } catch (Exception e) {
            System.err.println("Error encountered during validation : "+e.getMessage());
        }
        return validating;
    }

}
