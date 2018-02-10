/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tezk
 */
@Entity
@Table(name = "child")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Child.findAll", query = "SELECT c FROM Child c"),
    @NamedQuery(name = "Child.findById", query = "SELECT c FROM Child c WHERE c.id = :id"),
    @NamedQuery(name = "Child.findByForename", query = "SELECT c FROM Child c WHERE c.forename = :forename"),
    @NamedQuery(name = "Child.findBySurname", query = "SELECT c FROM Child c WHERE c.surname = :surname"),
    @NamedQuery(name = "Child.findByDob", query = "SELECT c FROM Child c WHERE c.dob = :dob")})
public class Child implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "forename")
    private String forename;
    @Size(max = 45)
    @Column(name = "surname")
    private String surname;
    @Column(name = "dob")
    @Temporal(TemporalType.DATE)
    private Date dob;
    @JoinTable(name = "child_has_customer", joinColumns = {
        @JoinColumn(name = "child_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "customer_id", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Customer> customerCollection;

    public Child() {
    }

    public Child(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @XmlTransient
    public Collection<Customer> getCustomerCollection() {
        return customerCollection;
    }

    public void setCustomerCollection(Collection<Customer> customerCollection) {
        this.customerCollection = customerCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Child)) {
            return false;
        }
        Child other = (Child) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Child[ id=" + id + " ]";
    }
    
}
