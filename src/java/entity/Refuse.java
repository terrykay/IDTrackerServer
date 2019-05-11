/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tezk
 */
@Entity
@Table(name = "refuse")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Refuse.findAll", query = "SELECT r FROM Refuse r"),
    @NamedQuery(name = "Refuse.findByCustomerId", query = "SELECT r FROM Refuse r WHERE r.customerId = :customerId"),
    @NamedQuery(name = "Refuse.findByDate", query = "SELECT r FROM Refuse r WHERE r.date = :date")})
public class Refuse implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "customer_id")
    private Integer customerId;
    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date date;
    @JoinColumn(name = "customer_id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Customer customer;

    public Refuse() {
    }

    public Refuse(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (customerId != null ? customerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Refuse)) {
            return false;
        }
        Refuse other = (Refuse) object;
        if ((this.customerId == null && other.customerId != null) || (this.customerId != null && !this.customerId.equals(other.customerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Refuse[ customerId=" + customerId + " ]";
    }
    
}
