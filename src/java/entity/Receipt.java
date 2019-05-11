/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tezk
 */
@Entity
@Table(name = "receipt")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Receipt.findAll", query = "SELECT r FROM Receipt r"),
    @NamedQuery(name = "Receipt.findByReceiptnumber", query = "SELECT r FROM Receipt r WHERE r.receiptnumber = :receiptnumber"),
    @NamedQuery(name = "Receipt.findByDate", query = "SELECT r FROM Receipt r WHERE r.date = :date"),
    @NamedQuery(name = "Receipt.findByAmount", query = "SELECT r FROM Receipt r WHERE r.amount = :amount"),
    @NamedQuery(name = "Receipt.findByNotes", query = "SELECT r FROM Receipt r WHERE r.notes = :notes"),
    @NamedQuery(name = "Receipt.findByReceivedby", query = "SELECT r FROM Receipt r WHERE r.receivedby = :receivedby")})
public class Receipt implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "receiptnumber")
    private Integer receiptnumber;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "amount")
    private Integer amount;
    @Size(max = 500)
    @Column(name = "notes")
    private String notes;
    @Size(max = 45)
    @Column(name = "receivedby")
    private String receivedby;
    @JoinColumn(name = "invoice_invoicenumber", referencedColumnName = "invoicenumber")
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private Invoice invoiceInvoicenumber;

    public Receipt() {
    }

    public Receipt(Integer receiptnumber) {
        this.receiptnumber = receiptnumber;
    }

    public Integer getReceiptnumber() {
        return receiptnumber;
    }

    public void setReceiptnumber(Integer receiptnumber) {
        this.receiptnumber = receiptnumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public String getReceivedby() {
        return receivedby;
    }

    public void setReceivedby(String receivedby) {
        this.receivedby = receivedby;
    }

    public Invoice getInvoiceInvoicenumber() {
        return invoiceInvoicenumber;
    }

    public void setInvoiceInvoicenumber(Invoice invoiceInvoicenumber) {
        this.invoiceInvoicenumber = invoiceInvoicenumber;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (receiptnumber != null ? receiptnumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Receipt)) {
            return false;
        }
        Receipt other = (Receipt) object;
        if ((this.receiptnumber == null && other.receiptnumber != null) || (this.receiptnumber != null && !this.receiptnumber.equals(other.receiptnumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Receipt[ receiptnumber=" + receiptnumber + " ]";
    }
    
}
