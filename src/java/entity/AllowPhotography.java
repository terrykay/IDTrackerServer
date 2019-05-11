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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tezk
 */
@Entity
@Table(name = "allow_photography")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AllowPhotography.findAll", query = "SELECT a FROM AllowPhotography a"),
    @NamedQuery(name = "AllowPhotography.findById", query = "SELECT a FROM AllowPhotography a WHERE a.id = :id"),
    @NamedQuery(name = "AllowPhotography.findByAllowPhotography", query = "SELECT a FROM AllowPhotography a WHERE a.allowPhotography = :allowPhotography")})
public class AllowPhotography implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "allow_photography")
    private Boolean allowPhotography;
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Customer customerId;

    public AllowPhotography() {
    }

    public AllowPhotography(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getAllowPhotography() {
        return allowPhotography;
    }

    public void setAllowPhotography(Boolean allowPhotography) {
        this.allowPhotography = allowPhotography;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
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
        if (!(object instanceof AllowPhotography)) {
            return false;
        }
        AllowPhotography other = (AllowPhotography) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.AllowPhotography[ id=" + id + " ]";
    }
    
}
