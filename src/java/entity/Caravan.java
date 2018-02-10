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
@Table(name = "caravan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Caravan.findAll", query = "SELECT c FROM Caravan c"),
    @NamedQuery(name = "Caravan.findByIdcaravan", query = "SELECT c FROM Caravan c WHERE c.idcaravan = :idcaravan"),
    @NamedQuery(name = "Caravan.findByMake", query = "SELECT c FROM Caravan c WHERE c.make = :make"),
    @NamedQuery(name = "Caravan.findByModel", query = "SELECT c FROM Caravan c WHERE c.model = :model"),
    @NamedQuery(name = "Caravan.findByLength", query = "SELECT c FROM Caravan c WHERE c.length = :length"),
    @NamedQuery(name = "Caravan.findByCris", query = "SELECT c FROM Caravan c WHERE c.cris = :cris")})
public class Caravan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcaravan")
    private Integer idcaravan;
    @Size(max = 45)
    @Column(name = "make")
    private String make;
    @Size(max = 45)
    @Column(name = "model")
    private String model;
    @Column(name = "length")
    private Integer length;
    @Size(max = 45)
    @Column(name = "cris")
    private String cris;
    @JoinColumn(name = "membership_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Membership membershipId;

    public Caravan() {
    }

    public Caravan(Integer idcaravan) {
        this.idcaravan = idcaravan;
    }

    public Integer getIdcaravan() {
        return idcaravan;
    }

    public void setIdcaravan(Integer idcaravan) {
        this.idcaravan = idcaravan;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getCris() {
        return cris;
    }

    public void setCris(String cris) {
        this.cris = cris;
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
        hash += (idcaravan != null ? idcaravan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Caravan)) {
            return false;
        }
        Caravan other = (Caravan) object;
        if ((this.idcaravan == null && other.idcaravan != null) || (this.idcaravan != null && !this.idcaravan.equals(other.idcaravan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Caravan[ idcaravan=" + idcaravan + " ]";
    }
    
}
