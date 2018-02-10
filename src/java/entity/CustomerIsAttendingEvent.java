/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tezk
 */
@Entity
@Table(name = "customer_is_attending_event")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomerIsAttendingEvent.findAll", query = "SELECT c FROM CustomerIsAttendingEvent c"),
    @NamedQuery(name = "CustomerIsAttendingEvent.findByEventId", query = "SELECT c FROM CustomerIsAttendingEvent c WHERE c.customerIsAttendingEventPK.eventId = :eventId"),
    @NamedQuery(name = "CustomerIsAttendingEvent.findByCustomerId", query = "SELECT c FROM CustomerIsAttendingEvent c WHERE c.customerIsAttendingEventPK.customerId = :customerId"),
    @NamedQuery(name = "CustomerIsAttendingEvent.findByCancelled", query = "SELECT c FROM CustomerIsAttendingEvent c WHERE c.cancelled = :cancelled"),
    @NamedQuery(name = "CustomerIsAttendingEvent.findByBookedIn", query = "SELECT c FROM CustomerIsAttendingEvent c WHERE c.bookedIn = :bookedIn"),
    @NamedQuery(name = "CustomerIsAttendingEvent.findByBookedOut", query = "SELECT c FROM CustomerIsAttendingEvent c WHERE c.bookedOut = :bookedOut"),
    @NamedQuery(name = "CustomerIsAttendingEvent.findByOrderDate", query = "SELECT c FROM CustomerIsAttendingEvent c WHERE c.orderDate = :orderDate")})
public class CustomerIsAttendingEvent implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CustomerIsAttendingEventPK customerIsAttendingEventPK;
    @Column(name = "cancelled")
    private Boolean cancelled;
    @Column(name = "booked_in")
    @Temporal(TemporalType.DATE)
    private Date bookedIn;
    @Column(name = "booked_out")
    @Temporal(TemporalType.DATE)
    private Date bookedOut;
    @Column(name = "order_date")
    @Temporal(TemporalType.DATE)
    private Date orderDate;
    @JoinColumn(name = "event_price_agreed", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EventPrice eventPriceAgreed;
    @JoinColumn(name = "invoicenumber", referencedColumnName = "invoicenumber")
    @ManyToOne(cascade = CascadeType.ALL)
    private Invoice invoicenumber;
    @JoinColumn(name = "customer_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Customer customer;
    @JoinColumn(name = "event_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Event event;

    public CustomerIsAttendingEvent() {
    }

    public CustomerIsAttendingEvent(CustomerIsAttendingEventPK customerIsAttendingEventPK) {
        this.customerIsAttendingEventPK = customerIsAttendingEventPK;
    }

    public CustomerIsAttendingEvent(int eventId, int customerId) {
        this.customerIsAttendingEventPK = new CustomerIsAttendingEventPK(eventId, customerId);
    }

    public CustomerIsAttendingEventPK getCustomerIsAttendingEventPK() {
        return customerIsAttendingEventPK;
    }

    public void setCustomerIsAttendingEventPK(CustomerIsAttendingEventPK customerIsAttendingEventPK) {
        this.customerIsAttendingEventPK = customerIsAttendingEventPK;
    }

    public Boolean getCancelled() {
        return cancelled;
    }

    public void setCancelled(Boolean cancelled) {
        this.cancelled = cancelled;
    }

    public Date getBookedIn() {
        return bookedIn;
    }

    public void setBookedIn(Date bookedIn) {
        this.bookedIn = bookedIn;
    }

    public Date getBookedOut() {
        return bookedOut;
    }

    public void setBookedOut(Date bookedOut) {
        this.bookedOut = bookedOut;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public EventPrice getEventPriceAgreed() {
        return eventPriceAgreed;
    }

    public void setEventPriceAgreed(EventPrice eventPriceAgreed) {
        this.eventPriceAgreed = eventPriceAgreed;
    }

    public Invoice getInvoicenumber() {
        return invoicenumber;
    }

    public void setInvoicenumber(Invoice invoicenumber) {
        this.invoicenumber = invoicenumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (customerIsAttendingEventPK != null ? customerIsAttendingEventPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerIsAttendingEvent)) {
            return false;
        }
        CustomerIsAttendingEvent other = (CustomerIsAttendingEvent) object;
        if ((this.customerIsAttendingEventPK == null && other.customerIsAttendingEventPK != null) || (this.customerIsAttendingEventPK != null && !this.customerIsAttendingEventPK.equals(other.customerIsAttendingEventPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CustomerIsAttendingEvent[ customerIsAttendingEventPK=" + customerIsAttendingEventPK + " ]";
    }
    
}
