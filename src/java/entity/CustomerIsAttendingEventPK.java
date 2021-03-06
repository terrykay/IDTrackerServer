/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author tezk
 */
@Embeddable
public class CustomerIsAttendingEventPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "event_id")
    private int eventId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "customer_id")
    private int customerId;

    public CustomerIsAttendingEventPK() {
    }

    public CustomerIsAttendingEventPK(int eventId, int customerId) {
        this.eventId = eventId;
        this.customerId = customerId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) eventId;
        hash += (int) customerId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerIsAttendingEventPK)) {
            return false;
        }
        CustomerIsAttendingEventPK other = (CustomerIsAttendingEventPK) object;
        if (this.eventId != other.eventId) {
            return false;
        }
        if (this.customerId != other.customerId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CustomerIsAttendingEventPK[ eventId=" + eventId + ", customerId=" + customerId + " ]";
    }
    
}
