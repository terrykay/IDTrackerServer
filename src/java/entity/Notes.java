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
@Table(name = "notes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notes.findAll", query = "SELECT n FROM Notes n"),
    @NamedQuery(name = "Notes.findByIdnotes", query = "SELECT n FROM Notes n WHERE n.idnotes = :idnotes"),
    @NamedQuery(name = "Notes.findByNotes", query = "SELECT n FROM Notes n WHERE n.notes = :notes")})
public class Notes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idnotes")
    private Integer idnotes;
    @Size(max = 6000)
    @Column(name = "notes")
    private String notes;
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Customer customerId;

    public Notes() {
    }

    public Notes(Integer idnotes) {
        this.idnotes = idnotes;
    }

    public Integer getIdnotes() {
        return idnotes;
    }

    public void setIdnotes(Integer idnotes) {
        this.idnotes = idnotes;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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
        hash += (idnotes != null ? idnotes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notes)) {
            return false;
        }
        Notes other = (Notes) object;
        if ((this.idnotes == null && other.idnotes != null) || (this.idnotes != null && !this.idnotes.equals(other.idnotes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Notes[ idnotes=" + idnotes + " ]";
    }
    
}
