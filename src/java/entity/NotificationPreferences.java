/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tezk
 */
@Entity
@Table(name = "notification_preferences")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NotificationPreferences.findAll", query = "SELECT n FROM NotificationPreferences n"),
    @NamedQuery(name = "NotificationPreferences.findByCustomerId", query = "SELECT n FROM NotificationPreferences n WHERE n.customerId = :customerId"),
    @NamedQuery(name = "NotificationPreferences.findBySms", query = "SELECT n FROM NotificationPreferences n WHERE n.sms = :sms"),
    @NamedQuery(name = "NotificationPreferences.findByPost", query = "SELECT n FROM NotificationPreferences n WHERE n.post = :post"),
    @NamedQuery(name = "NotificationPreferences.findByEmail", query = "SELECT n FROM NotificationPreferences n WHERE n.email = :email")})
public class NotificationPreferences implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "customer_id")
    private Integer customerId;
    @Column(name = "sms")
    private Boolean sms;
    @Column(name = "post")
    private Boolean post;
    @Column(name = "email")
    private Boolean email;
    @JoinColumn(name = "customer_id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Customer customer;

    public NotificationPreferences() {
    }

    public NotificationPreferences(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Boolean getSms() {
        return sms;
    }

    public void setSms(Boolean sms) {
        this.sms = sms;
    }

    public Boolean getPost() {
        return post;
    }

    public void setPost(Boolean post) {
        this.post = post;
    }

    public Boolean getEmail() {
        return email;
    }

    public void setEmail(Boolean email) {
        this.email = email;
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
        if (!(object instanceof NotificationPreferences)) {
            return false;
        }
        NotificationPreferences other = (NotificationPreferences) object;
        if ((this.customerId == null && other.customerId != null) || (this.customerId != null && !this.customerId.equals(other.customerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.NotificationPreferences[ customerId=" + customerId + " ]";
    }
    
}
