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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tezk
 */
@Entity
@Table(name = "next_of_kin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NextOfKin.findAll", query = "SELECT n FROM NextOfKin n"),
    @NamedQuery(name = "NextOfKin.findById", query = "SELECT n FROM NextOfKin n WHERE n.id = :id"),
    @NamedQuery(name = "NextOfKin.findByName", query = "SELECT n FROM NextOfKin n WHERE n.name = :name"),
    @NamedQuery(name = "NextOfKin.findByContactNo", query = "SELECT n FROM NextOfKin n WHERE n.contactNo = :contactNo"),
    @NamedQuery(name = "NextOfKin.findByAwareNaturist", query = "SELECT n FROM NextOfKin n WHERE n.awareNaturist = :awareNaturist")})
public class NextOfKin implements Serializable {

    @Size(max = 45)
    @Column(name = "relationship")
    private String relationship;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 90)
    @Column(name = "name")
    private String name;
    @Size(max = 45)
    @Column(name = "contact_no")
    private String contactNo;
    @Column(name = "aware_naturist")
    private Boolean awareNaturist;
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Customer customerId;

    public NextOfKin() {
    }

    public NextOfKin(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public Boolean getAwareNaturist() {
        return awareNaturist;
    }

    public void setAwareNaturist(Boolean awareNaturist) {
        this.awareNaturist = awareNaturist;
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
        if (!(object instanceof NextOfKin)) {
            return false;
        }
        NextOfKin other = (NextOfKin) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.NextOfKin[ id=" + id + " ]";
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
    
}
