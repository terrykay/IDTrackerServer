/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Customer;
import entity.Image;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import session.CustomerFacade;
import session.ImageFacade;

/**
 *
 * @author tezk
 */
@Stateless
public class BackgroundMaintenanceBean {

    @EJB
    CustomerFacade customerFacade;
    @EJB
    ImageFacade imageFacade;

    @Schedule(dayOfWeek = "*", month = "*", hour = "*", dayOfMonth = "*", year = "*", minute = "02", second = "0", persistent = false)
    public void myTimer() {
        List<Customer> customers = customerFacade.findAll();
        System.out.println(new Date() + " : " + customers.size() + " customers listed");

        Runnable task = new Runnable() {
            public void run() {
                ImageHandlerBean handler = new ImageHandlerBean();
                Collection<Image> images = imageFacade.findByType('i');
                Iterator<Image> i = images.iterator();
                while (i.hasNext()) {
                    Image image = i.next();
                    handler.removeImage(image.getUrl(), image.getCustomerId().getId().toString());
                }
                System.out.println("Removed "+images.size()+" images removed");
            }
        };
        new Thread(task).start();
    }

}
