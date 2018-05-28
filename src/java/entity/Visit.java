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
import javax.persistence.ManyToOne;
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
@Table(name = "visit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Visit.findAll", query = "SELECT v FROM Visit v"),
    @NamedQuery(name = "Visit.findByIdvisit", query = "SELECT v FROM Visit v WHERE v.idvisit = :idvisit"),
    @NamedQuery(name = "Visit.findByStartDate", query = "SELECT v FROM Visit v WHERE v.startDate = :startDate"),
    @NamedQuery(name = "Visit.findByEndDate", query = "SELECT v FROM Visit v WHERE v.endDate = :endDate")})
public class Visit implements Serializable {

    @Size(max = 45)
    @Column(name = "type")
    private String type;

    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Customer customerId;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idvisit")
    private Integer idvisit;
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "visitIdvisit")
    private Collection<VisitHasInvoice> visitHasInvoiceCollection;
    @JoinColumn(name = "unit_id", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private Unit unitId;

    public Visit() {
    }

    public Visit(Integer idvisit) {
        this.idvisit = idvisit;
    }

    public Integer getIdvisit() {
        return idvisit;
    }

    public void setIdvisit(Integer idvisit) {
        this.idvisit = idvisit;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @XmlTransient
    public Collection<VisitHasInvoice> getVisitHasInvoiceCollection() {
        return visitHasInvoiceCollection;
    }

    public void setVisitHasInvoiceCollection(Collection<VisitHasInvoice> visitHasInvoiceCollection) {
        this.visitHasInvoiceCollection = visitHasInvoiceCollection;
    }

    public Unit getUnitId() {
        return unitId;
    }

    public void setUnitId(Unit unitId) {
        this.unitId = unitId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idvisit != null ? idvisit.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Visit)) {
            return false;
        }
        Visit other = (Visit) object;
        if ((this.idvisit == null && other.idvisit != null) || (this.idvisit != null && !this.idvisit.equals(other.idvisit))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Visit[ idvisit=" + idvisit + " ]";
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}
