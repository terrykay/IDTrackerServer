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
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
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
@Table(name = "invoice")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Invoice.findAll", query = "SELECT i FROM Invoice i"),
    @NamedQuery(name = "Invoice.findByInvoicenumber", query = "SELECT i FROM Invoice i WHERE i.invoicenumber = :invoicenumber"),
    @NamedQuery(name = "Invoice.findByIssuedate", query = "SELECT i FROM Invoice i WHERE i.issuedate = :issuedate"),
    @NamedQuery(name = "Invoice.findByDuedate", query = "SELECT i FROM Invoice i WHERE i.duedate = :duedate"),
    @NamedQuery(name = "Invoice.findByAmount", query = "SELECT i FROM Invoice i WHERE i.amount = :amount"),
    @NamedQuery(name = "Invoice.findByNotes", query = "SELECT i FROM Invoice i WHERE i.notes = :notes")})
public class Invoice implements Serializable {

    @Size(max = 12)
    @Column(name = "invoice_type")
    private String invoiceType;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "invoiceInvoicenumber")
    private Collection<VisitHasInvoice> visitHasInvoiceCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "invoicenumber")
    private Integer invoicenumber;
    @Column(name = "issuedate")
    @Temporal(TemporalType.DATE)
    private Date issuedate;
    @Column(name = "duedate")
    @Temporal(TemporalType.DATE)
    private Date duedate;
    @Column(name = "amount")
    private Integer amount;
    @Size(max = 500)
    @Column(name = "notes")
    private String notes;
    @JoinTable(name = "membershipcharge_has_invoice", joinColumns = {
        @JoinColumn(name = "invoice_invoicenumber", referencedColumnName = "invoicenumber")}, inverseJoinColumns = {
        @JoinColumn(name = "membershipcharge_id", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Membershipcharge> membershipchargeCollection;
    @ManyToMany(mappedBy = "invoiceCollection")
    private Collection<Electricitycharge> electricitychargeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "invoiceInvoicenumber")
    private Collection<Receipt> receiptCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "invoicenumber")
    private Collection<CustomerIsAttendingEvent> customerIsAttendingEventCollection;

    public Invoice() {
    }

    public Invoice(Integer invoicenumber) {
        this.invoicenumber = invoicenumber;
    }

    public Integer getInvoicenumber() {
        return invoicenumber;
    }

    public void setInvoicenumber(Integer invoicenumber) {
        this.invoicenumber = invoicenumber;
    }

    public Date getIssuedate() {
        return issuedate;
    }

    public void setIssuedate(Date issuedate) {
        this.issuedate = issuedate;
    }

    public Date getDuedate() {
        return duedate;
    }

    public void setDuedate(Date duedate) {
        this.duedate = duedate;
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

    @XmlTransient
    public Collection<Membershipcharge> getMembershipchargeCollection() {
        return membershipchargeCollection;
    }

    public void setMembershipchargeCollection(Collection<Membershipcharge> membershipchargeCollection) {
        this.membershipchargeCollection = membershipchargeCollection;
    }

    @XmlTransient
    public Collection<Electricitycharge> getElectricitychargeCollection() {
        return electricitychargeCollection;
    }

    public void setElectricitychargeCollection(Collection<Electricitycharge> electricitychargeCollection) {
        this.electricitychargeCollection = electricitychargeCollection;
    }

    @XmlTransient
    public Collection<Receipt> getReceiptCollection() {
        return receiptCollection;
    }

    public void setReceiptCollection(Collection<Receipt> receiptCollection) {
        this.receiptCollection = receiptCollection;
    }

    @XmlTransient
    public Collection<CustomerIsAttendingEvent> getCustomerIsAttendingEventCollection() {
        return customerIsAttendingEventCollection;
    }

    public void setCustomerIsAttendingEventCollection(Collection<CustomerIsAttendingEvent> customerIsAttendingEventCollection) {
        this.customerIsAttendingEventCollection = customerIsAttendingEventCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (invoicenumber != null ? invoicenumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Invoice)) {
            return false;
        }
        Invoice other = (Invoice) object;
        if ((this.invoicenumber == null && other.invoicenumber != null) || (this.invoicenumber != null && !this.invoicenumber.equals(other.invoicenumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Invoice[ invoicenumber=" + invoicenumber + " ]";
    }

    @XmlTransient
    public Collection<VisitHasInvoice> getVisitHasInvoiceCollection() {
        return visitHasInvoiceCollection;
    }

    public void setVisitHasInvoiceCollection(Collection<VisitHasInvoice> visitHasInvoiceCollection) {
        this.visitHasInvoiceCollection = visitHasInvoiceCollection;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }
    
}
