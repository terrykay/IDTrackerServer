/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Car;
import entity.Child;
import entity.Customer;
import entity.Image;
import java.io.File;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author tezk
 */
@Stateless
public class CustomerFacade extends AbstractFacade<Customer> {

    @PersistenceContext(unitName = "IDTrackerServerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CustomerFacade() {
        super(Customer.class);
    }

    private void setCollections(Customer customer) {
        // Set the collections link back to customer - needed in Image to set the URL
        for (Image eachItem : customer.getImageCollection()) {
            eachItem.setCustomerId(customer);
            
        }
        for (Car eachItem : customer.getCarCollection()) {
            eachItem.setCustomerId(customer);
        }
        for (Child eachItem : customer.getChildCollection()) {
            eachItem.getCustomerCollection().add(customer);
        }
    }

    @Override
    public void create(Customer customer) {
        super.create(customer);
        setCollections(customer);
    }

    @Override
    public void edit(Customer customer) {
        super.edit(customer);
        setCollections(customer);
    }

    public Collection<Customer> findBySurname(String surname) {
        Query q = em.createNamedQuery("Customer.findBySurname");
        q.setParameter("surname", surname);
        List<Customer> results = q.getResultList();
        return results;
    }

    public Customer findByID(Integer id) {
        Query q = em.createNamedQuery("Customer.findById");
        q.setParameter("id", id);
        Customer who = null;
        try {
            who = (Customer) q.getSingleResult();
        } catch (Exception e) {
            // Couldn't find
            return null;
        }
        return who;
    }

}
