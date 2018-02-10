/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Customer;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import session.CustomerFacade;

/**
 *
 * @author tezk
 */
@Stateless
public class BackgroundMaintenanceBean {
    @EJB
    CustomerFacade customerFacade;
    
    @Schedule(dayOfWeek = "*", month = "*", hour = "3", dayOfMonth = "*", year = "*", minute = "0", second = "0", persistent = false)
    public void myTimer() {
        List <Customer>customers = customerFacade.findAll();
        System.out.println(new Date()+" : "+customers.size()+" customers listed");
    }

}
