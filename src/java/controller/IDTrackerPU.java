/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Customer;
import java.util.Collection;
import javax.ejb.EJB;
import session.CustomerFacade;

/**
 *
 * @author tezk
 */
public class IDTrackerPU {
    @EJB
    private CustomerFacade customerFacade;
    
    public Collection <Customer> getCustomers() {
        return customerFacade.findAll();
    }
}
