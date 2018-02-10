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
@Table(name = "visit_has_invoice")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VisitHasInvoice.findAll", query = "SELECT v FROM VisitHasInvoice v"),
    @NamedQuery(name = "VisitHasInvoice.findById", query = "SELECT v FROM VisitHasInvoice v WHERE v.id = :id")})
public class VisitHasInvoice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "invoice_invoicenumber", referencedColumnName = "invoicenumber")
    @ManyToOne(optional = false)
    private Invoice invoiceInvoicenumber;
    @JoinColumn(name = "visit_idvisit", referencedColumnName = "idvisit")
    @ManyToOne(optional = false)
    private Visit visitIdvisit;

    public VisitHasInvoice() {
    }

    public VisitHasInvoice(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Invoice getInvoiceInvoicenumber() {
        return invoiceInvoicenumber;
    }

    public void setInvoiceInvoicenumber(Invoice invoiceInvoicenumber) {
        this.invoiceInvoicenumber = invoiceInvoicenumber;
    }

    public Visit getVisitIdvisit() {
        return visitIdvisit;
    }

    public void setVisitIdvisit(Visit visitIdvisit) {
        this.visitIdvisit = visitIdvisit;
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
        if (!(object instanceof VisitHasInvoice)) {
            return false;
        }
        VisitHasInvoice other = (VisitHasInvoice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.VisitHasInvoice[ id=" + id + " ]";
    }
    
}
