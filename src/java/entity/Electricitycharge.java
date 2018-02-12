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
import javax.persistence.ManyToOne;
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
@Table(name = "electricitycharge")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Electricitycharge.findAll", query = "SELECT e FROM Electricitycharge e"),
    @NamedQuery(name = "Electricitycharge.findById", query = "SELECT e FROM Electricitycharge e WHERE e.id = :id"),
    @NamedQuery(name = "Electricitycharge.findByYear", query = "SELECT e FROM Electricitycharge e WHERE e.year = :year"),
    @NamedQuery(name = "Electricitycharge.findByStartUnit", query = "SELECT e FROM Electricitycharge e WHERE e.startUnit = :startUnit"),
    @NamedQuery(name = "Electricitycharge.findByEndUnit", query = "SELECT e FROM Electricitycharge e WHERE e.endUnit = :endUnit"),
    @NamedQuery(name = "Electricitycharge.findByCharge", query = "SELECT e FROM Electricitycharge e WHERE e.charge = :charge"),
    @NamedQuery(name = "Electricitycharge.findByDatePaid", query = "SELECT e FROM Electricitycharge e WHERE e.datePaid = :datePaid")})
public class Electricitycharge implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 4)
    @Column(name = "year")
    private String year;
    @Column(name = "start_unit")
    private Integer startUnit;
    @Column(name = "end_unit")
    private Integer endUnit;
    @Column(name = "charge")
    private Integer charge;
    @Column(name = "date_paid")
    @Temporal(TemporalType.DATE)
    private Date datePaid;
    @JoinTable(name = "electricitycharge_has_invoice", joinColumns = {
        @JoinColumn(name = "electricitycharge_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "invoice_invoicenumber", referencedColumnName = "invoicenumber")})
    @ManyToMany
    private Collection<Invoice> invoiceCollection;
    @JoinColumn(name = "membership_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Membership membershipId;

    public Electricitycharge() {
    }

    public Electricitycharge(Integer id) {
        this.id = id;
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

    public Integer getStartUnit() {
        return startUnit;
    }

    public void setStartUnit(Integer startUnit) {
        this.startUnit = startUnit;
    }

    public Integer getEndUnit() {
        return endUnit;
    }

    public void setEndUnit(Integer endUnit) {
        this.endUnit = endUnit;
    }

    public Integer getCharge() {
        return charge;
    }

    public void setCharge(Integer charge) {
        this.charge = charge;
    }

    public Date getDatePaid() {
        return datePaid;
    }

    public void setDatePaid(Date datePaid) {
        this.datePaid = datePaid;
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
        System.out.println("ElectricitCharge.setMemId : "+membershipId);
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
        if (!(object instanceof Electricitycharge)) {
            return false;
        }
        Electricitycharge other = (Electricitycharge) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Electricitycharge[ id=" + id + " ]";
    }
    
}
