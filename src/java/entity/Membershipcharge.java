/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tezk
 */
@Entity
@Table(name = "membershipcharge")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Membershipcharge.findAll", query = "SELECT m FROM Membershipcharge m"),
    @NamedQuery(name = "Membershipcharge.findById", query = "SELECT m FROM Membershipcharge m WHERE m.id = :id"),
    @NamedQuery(name = "Membershipcharge.findByYear", query = "SELECT m FROM Membershipcharge m WHERE m.year = :year"),
    @NamedQuery(name = "Membershipcharge.findByAmount", query = "SELECT m FROM Membershipcharge m WHERE m.amount = :amount"),
    @NamedQuery(name = "Membershipcharge.findByNotes", query = "SELECT m FROM Membershipcharge m WHERE m.notes = :notes"),
    @NamedQuery(name = "Membershipcharge.findByType", query = "SELECT m FROM Membershipcharge m WHERE m.type = :type")})
public class Membershipcharge implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "year")
    private String year;
    @Column(name = "amount")
    private Integer amount;
    @Size(max = 500)
    @Column(name = "notes")
    private String notes;
    @Size(max = 4)
    @Column(name = "type")
    private String type;
    @ManyToMany(mappedBy = "membershipchargeCollection")
    private Collection<Invoice> invoiceCollection;
    @JoinColumn(name = "membership_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Membership membershipId;

    public Membershipcharge() {
    }

    public Membershipcharge(Integer id) {
        this.id = id;
    }

    public Membershipcharge(Integer id, String year) {
        this.id = id;
        this.year = year;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlTransient
    public Collection<Invoice> getInvoiceCollection() {
        return invoiceCollection;
    }

    public void setInvoiceCollection(Collection<Invoice> invoiceCollection) {
        this.invoiceCollection = invoiceCollection;
    }

    public Membership getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(Membership membershipId) {
        this.membershipId = membershipId;
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
        if (!(object instanceof Membershipcharge)) {
            return false;
        }
        Membershipcharge other = (Membershipcharge) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Membershipcharge[ id=" + id + " ]";
    }
    
}
