/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import IDTrackerTO.AddressTO;
import IDTrackerTO.CustomerTO;
import IDTrackerTO.ImageTO;
import entity.Address;
import entity.AddressUtility;
import entity.CarUtility;
import entity.Caravan;
import entity.CaravanUtility;
import entity.ChildUtility;
import entity.Customer;
import entity.CustomerUtility;
import entity.Electricitycharge;
import entity.ImageUtility;
import entity.Invoice;
import entity.Membership;
import entity.Receipt;
import entity.Refuse;
import entity.Visit;
import entity.VisitUtility;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import session.AddressFacade;
import session.AuditFacade;

import session.CarFacade;
import session.CaravanFacade;
import session.ChildFacade;
import session.CustomerFacade;
import session.ImageFacade;
import session.ReceiptFacade;
import session.SessionFacade;
import session.UserFacade;
import session.VisitFacade;
import userIdEntity.Audit;
import userIdEntity.Session;
import userIdEntity.User;

/**
 *
 * @author tezk
 */
@WebService(serviceName = "idtrackerws")

public class idtrackerws {

    @EJB
    private CustomerFacade customerFacade;
    @EJB
    private ImageFacade imageFacade;
    @EJB
    private CarFacade carFacade;
    @EJB
    private ChildFacade childFacade;
    @EJB
    private AddressFacade addressFacade;
    @EJB
    private CaravanFacade caravanFacade;
    @EJB
    private CustomerValidatorBean customerValidatorBean;
    @EJB
    private ImageHandlerBean imageHandlerBean;
    @EJB
    private SessionFacade sessionFacade;
    @EJB
    private UserFacade userFacade;
    @EJB
    private AuditFacade auditFacade;
    @EJB
    private ReceiptFacade receiptFacade;
    @EJB
    private VisitFacade visitFacade;

    /**
     * Web service operation
     *
     * @param sessionID
     * @return
     */
    @WebMethod(operationName = "getCustomers")
    public Collection<CustomerTO> getCustomers(@WebParam(name = "sessionID") String sessionID) {
        Session session = checkSession(sessionID);
        if (session == null) {
            System.out.println("Session is null");
            return null;
        }

        Collection<Customer> customerList = customerFacade.findAll();
        if (customerList.isEmpty()) {
            return new ArrayList();
        }

        addAuditData(session, "Customer list retrieved by " + session.getUserId());
        return CustomerUtility.getAsTO(customerList);
    }

    @WebMethod(operationName = "getDisplayCustomers")
    public Collection<CustomerTO> getDisplayCustomers(@WebParam(name = "sessionID") String sessionID) {
        Session session = checkSession(sessionID);
        if (session == null) {
            System.out.println("Session is null");
            return null;
        }

        Collection<Customer> customerList = customerFacade.findAll();
        if (customerList.isEmpty()) {
            return new ArrayList();
        }

        addAuditData(session, "Customer list retrieved by " + session.getUserId());
        return CustomerUtility.getAsDisplayTO(customerList);
    }

    /**
     * Web service operation
     *
     * @param toCustomer
     * @param sessionID
     * @return
     */
    @WebMethod(operationName = "saveCustomer")
    public String saveCustomer(@WebParam(name = "aCustomer") CustomerTO toCustomer, @WebParam(name = "sessionID") String sessionID) {
        System.out.println("Saving customer");
        Session session = checkSession(sessionID);
        if (session == null) {
            return "session invalid";
        }
        // Is address set? If not, might be trying to persist from getDisplayCustomers
        if (toCustomer.getAddressId() == null) {
            System.err.println("Received incomplete customer to persist : " + toCustomer.getId() + ", " + toCustomer.getForename() + " " + toCustomer.getSurname());
            return "Invalid customer";
        }
        final Customer customer = CustomerUtility.getAsEntity(toCustomer);
        Customer partner = null;
        if (customer.getForename() == null) {
            return "Null forename";
        }
        if (toCustomer.getRefuse() != null) {
            toCustomer.getRefuse().setCustomerId(toCustomer.getId());
        }
        // Partner ID set? We need to find and link as we only get ID
        if (toCustomer.getPartnerId() != null && toCustomer.getPartnerId() != 0) {
            partner = customerFacade.findByID(toCustomer.getPartnerId());
            if (partner != null) {
                customer.setPartnerId(partner);
            }
        }
        // if passed customer is member and linked Caravan has id of -1, needs removing
        System.out.println("check for membership = " + toCustomer.getMembership());
        if (toCustomer.getMembership() != null) {
            if (toCustomer.getMembership().getCaravanCollection() != null
                    && toCustomer.getMembership().getCaravanCollection().size() > 0) {
                customer.getMembershipId().getCaravanCollection().stream().forEach(e -> {
                    e.setMembershipId(customer.getMembershipId());
                });
                if (toCustomer.getMembership() != null
                        && toCustomer.getMembership().getId() != null
                        && toCustomer.getMembership().getId() == -1) {
                    Caravan vanRemove = (Caravan) customer.getMembershipId().getCaravanCollection().toArray()[0];
                    vanRemove.setMembershipId(customer.getMembershipId());
                    caravanFacade.remove(vanRemove);
                }
            }
            // Any electricity charges
            System.out.println("Set membership for charges - value " + customer.getMembershipId().getElectricitychargeCollection());
            System.out.println("Set membership for charges - empty? " + customer.getMembershipId().getElectricitychargeCollection().isEmpty());

            if (customer.getMembershipId() != null
                    && !customer.getMembershipId().getElectricitychargeCollection().isEmpty()) {
                Iterator<Electricitycharge> i = customer.getMembershipId().getElectricitychargeCollection().iterator();
                while (i.hasNext()) {
                    Electricitycharge eachCharge = i.next();
                    eachCharge.setMembershipId(customer.getMembershipId());

                    Iterator<Invoice> j = eachCharge.getInvoiceCollection().iterator();
                    while (j.hasNext()) {
                        Invoice eachInvoice = j.next();
                        if (eachInvoice.getElectricitychargeCollection() == null) {
                            eachInvoice.setElectricitychargeCollection(new ArrayList());
                        }
                        eachInvoice.getElectricitychargeCollection().add(eachCharge);

                        Iterator<Receipt> k = eachInvoice.getReceiptCollection().iterator();
                        while (k.hasNext()) {
                            Receipt eachReceipt = k.next();
                            eachReceipt.setInvoiceInvoicenumber(eachInvoice);
                            receiptFacade.edit(eachReceipt);
                        }
                    }

                    System.out.println("invoices = " + eachCharge.getInvoiceCollection().size());
                    System.out.println("receipts = " + ((Invoice) (eachCharge.getInvoiceCollection().toArray()[0])).getReceiptCollection().size());
                }
            }
        }

        // new customer? If id is set, updating old customer
        String value = null;
        try {
            if (customer.getId() == null || customer.getId() == 0) {
                Refuse tempRefuse = customer.getRefuse();
                customer.setRefuse(null);
                customerFacade.create(customer);
                if (tempRefuse != null) {
                    tempRefuse.setCustomerId(customer.getId());
                    customer.setRefuse(tempRefuse);
                    customerFacade.edit(customer);
                }
            } else {
                // Remove anything in the "remove" collections
                if (!toCustomer.getCarDeleteCollection().isEmpty()) {
                    toCustomer.getCarDeleteCollection().stream().forEach((eachCar) -> {
                        carFacade.remove(CarUtility.getAsEntity(eachCar));
                    });
                }
                if (!toCustomer.getChildDeleteCollection().isEmpty()) {
                    toCustomer.getChildDeleteCollection().stream().forEach((eachChild) -> {
                        childFacade.remove(ChildUtility.getAsEntity(eachChild));
                    });
                }
                if (!toCustomer.getImageDeleteCollection().isEmpty()) {
                    for (ImageTO eachImage : toCustomer.getImageDeleteCollection()) {
                        if (eachImage.getId() != null && eachImage.getId() > 0) {
                            imageFacade.remove(ImageUtility.getAsEntity(eachImage));
                            imageHandlerBean.removeImage(eachImage.getUrl(), toCustomer.getId().toString());
                        }
                    }
                }
                if (!toCustomer.getVisitDeleteCollection().isEmpty()) {
                    toCustomer.getVisitDeleteCollection().stream().forEach(eachVisit -> {
                        Visit asEntity = VisitUtility.getAsEntity(eachVisit);
                        asEntity.setCustomerId(customer);
                        visitFacade.remove(asEntity);
                    });
                }
                // Saving customer with an updated Refuse field is causing errors? 
                Refuse refuse = customer.getRefuse();
                if (customer.getRefuse() != null) {
                    //customer.setRefuse(null);
                    customer.getRefuse().setCustomer(customer);
                    customer.getRefuse().setCustomerId(customer.getId());
                }
                // customer.getRefuse().setCustomer(customer);
                customerFacade.edit(customer);
                if (refuse != null) {
                    customer.setRefuse(refuse);
                    customerFacade.edit(customer);
                }
            }
            value = customer.getId().toString();
            //       customerValidatorBean.validate(customer);
        } catch (EJBException e) {
            System.err.println("Problem saving ejb : " + e.getMessage());
            return "Problem saving";
        }
        Customer verifyCustomer = customerFacade.findByID(Integer.parseInt(value));

        if (partner != null) {
            if (partner.getPartnerId() == null || !partner.getPartnerId().equals(verifyCustomer)) {
                partner.setPartnerId(verifyCustomer);
                customerFacade.edit(partner);
            }
            if (verifyCustomer.getChildCollection().size() > partner.getChildCollection().size()) {
                partner.getChildCollection().addAll(verifyCustomer.getChildCollection());
                customerFacade.edit(partner);
            }
            if (verifyCustomer.getChildCollection().size() < partner.getChildCollection().size()) {
                verifyCustomer.setChildCollection(partner.getChildCollection());
                customerFacade.edit(verifyCustomer);
            }

        }
        addAuditData(session, "User persisted : " + value);
        return value;
    }

    /**
     * Web service operation
     *
     * @param customerID
     * @param sessionID
     * @return
     */
    @WebMethod(operationName = "getCustomerByID")
    public CustomerTO getCustomerByID(@WebParam(name = "customerID") int customerID, @WebParam(name = "sessionID") String sessionID) {
        Session session = checkSession(sessionID);
        System.out.println("getById");
        if (session == null) {
            return null;
        }
        Customer customer = customerFacade.findByID(customerID);
        if (customer != null) {
            System.out.println("getById : " + customer);
            addAuditData(session, "Found by ID : " + customerID);
            return CustomerUtility.getAsTO(customer);
        } else {
            System.out.println("getById null");
            return null;
        }
    }

    /**
     * Web service operation
     *
     * @param who
     * @param sessionID
     * @return
     */
    @WebMethod(operationName = "deleteCustomer")
    public Boolean deleteCustomer(@WebParam(name = "who") CustomerTO who, @WebParam(name = "sessionID") String sessionID) {
        Session session = checkSession(sessionID);
        if (session == null) {
            return false;
        }
        try {
            Customer whoCustomer = CustomerUtility.getAsEntity(who);
            if (who.getPartnerId() != null && who.getPartnerId() != 0) {
                Customer partner = customerFacade.findByID(who.getPartnerId());
                if (partner != null) {
                    partner.setPartnerId(null);
                    customerFacade.edit(partner);
                }
                whoCustomer.setPartnerId(null);
            }
            addAuditData(session, "Remove customer : " + whoCustomer);
            customerFacade.remove(whoCustomer);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Web service operation
     *
     * @param addressTO
     * @param sessionID
     * @return
     */
    @WebMethod(operationName = "saveAddress")
    public Integer saveAddress(@WebParam(name = "addressTO") AddressTO addressTO, @WebParam(name = "sessionID") String sessionID) {
        Session session = checkSession(sessionID);
        if (session == null) {
            return null;
        }
        //TODO write your implementation code here:
        Address address = AddressUtility.getAsEntity(addressTO);
        addAuditData(session, "Address saved : " + addressTO.getId());
        addressFacade.edit(address);
        return address.getId();
    }

    /**
     * Web service operation
     */
    /*   @WebMethod(operationName = "getEvents")
    public Collection<EventTO> getEvents() {
        List<Event> events = eventFacade.findAll();
        return EventUtility.getAsTO(events);
    }

    /**
     * Web service operation
     */
 /*   @WebMethod(operationName = "saveEvent")
    public Integer saveEvent(@WebParam(name = "anEvent") EventTO anEvent) {
        Event newEvent = EventUtility.getAsEntity(anEvent);
        // Set links from prices back to event
        if (newEvent.getEventPriceCollection() != null) {
            for (EventPrice eachPrice : newEvent.getEventPriceCollection()) {
                eachPrice.setEventId(newEvent);
            }
        }
        try {
            if (newEvent.getId() == null || newEvent.getId() == 0) {
                eventFacade.create(newEvent);
                return newEvent.getId();
            } else {
                eventFacade.edit(newEvent);
                return newEvent.getId();
            }
        } catch (Exception e) {
            System.err.println("Error saving event! " + e.getMessage());
        }
        return 0;
    }

    /**
     * Web service operation
     */
 /*    @WebMethod(operationName = "deleteEvent")
    public Boolean deleteEvent(@WebParam(name = "anEvent") EventTO anEvent) {

        try {
            Event event = EventUtility.getAsEntity(anEvent);
            for (EventPrice eachPrice : event.getEventPriceCollection()) {
                eventPriceFacade.remove(eachPrice);
                eventFacade.remove(event);
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Web service operation
     */
 /*  @WebMethod(operationName = "getCustomersForEvent")
    public java.util.Collection<CustomerTO> getCustomersForEvent(@WebParam(name = "eventId") int eventId) {
        //TODO write your implementation code here:

        Event whichEvent;
        whichEvent = eventFacade.find(eventId);
        Collection<CustomerTO> whoseGoing = new ArrayList();
        if (whichEvent != null) {
            Collection<CustomerIsAttendingEvent> who = whichEvent.getCustomerIsAttendingEventCollection();
            for (CustomerIsAttendingEvent eachCustomer : who) {
                whoseGoing.add(CustomerUtility.getAsTO(eachCustomer.getCustomer()));
            }
        }
        return whoseGoing;
    }

    /**
     * Web service operation
     */
 /*  @WebMethod(operationName = "saveCustomerIsAttendingEvent")
    public boolean saveCustomerIsAttendingEvent(@WebParam(name = "customerIsAttendingEvent") CustomerIsAttendingEventTO customerIsAttendingEvent) {
        CustomerIsAttendingEvent ticket = CustomerIsAttendingEventUtility.getAsEntity(customerIsAttendingEvent);
        Event event = eventFacade.findByID(customerIsAttendingEvent.getEventId());
        ticket.setEvent(event);
        Customer customer = customerFacade.findByID(customerIsAttendingEvent.getCustomerId());
        ticket.setCustomer(customer);
        customerIsAttendingEventFacade.create(ticket);
        event.getCustomerIsAttendingEventCollection().add(ticket);
        customer.getCustomerIsAttendingEventCollection().add(ticket);
        eventFacade.edit(event);
        customerFacade.edit(customer);

        return false;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "loginUser")
    public String loginUser(@WebParam(name = "username") String username, @WebParam(name = "password") String password) {
        User aUser = userFacade.findByUsername(username);
        System.out.println("user = " + aUser);

        if (aUser != null && aUser.getPassword().equals(password)) {
            List<Session> sessions = sessionFacade.findByUserId(aUser.getIdusers());
            if (sessions != null && sessions.size() > 0) {
                for (Session eachSession : sessions) {
                    sessionFacade.remove(eachSession);
                }
            }
            Session aSession = new Session();
            aSession.setUserId(aUser.getIdusers());
            aSession.setSessionStart(new Date());
            aSession.setSessionid(UUID.randomUUID().toString());
            //aUser.getSessionCollection().add(aSession);
            sessionFacade.create(aSession);

            return aSession.getSessionid();
        }

        return null;
    }

    private Session checkSession(String sessionId) {
        // Helper function to check if a session is valid
        return sessionFacade.findBySessionId(sessionId);
    }

    private void addAuditData(Session sessionId, String details) {
        Audit anAudit = new Audit();
        if (details.length() > 44) {
            details = details.substring(0, 44);
        }
        anAudit.setAction(details);
        anAudit.setDate(new Date());
        User aUser = userFacade.findByUserId(sessionId.getUserId());
        anAudit.setUserId(aUser);
        aUser.getAuditCollection().add(anAudit);

        auditFacade.create(anAudit);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "checkIsValidSession")
    public boolean checkIsValidSession(@WebParam(name = "sessionID") String sessionID) {
        return sessionFacade.findBySessionId(sessionID) != null;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "removePartner")
    public String removePartner(@WebParam(name = "customerID") int customerID, @WebParam(name = "sessionID") String sessionID) {
        Session session = checkSession(sessionID);
        if (session == null) {
            return "failed : no session";
        }
        Customer customer = customerFacade.findByID(customerID);
        if (customer != null) {
            addAuditData(session, "Found by ID whilst removing partner : " + customerID);
            Customer partnerId = customer.getPartnerId();
            customer.setPartnerId(null);
            customerFacade.edit(customer);
            if (partnerId != null) {
                partnerId.setPartnerId(null);
                customerFacade.edit(partnerId);
            }
            return "annulled";
        } else {
            return "failed : customer not found";
        }
    }
}
