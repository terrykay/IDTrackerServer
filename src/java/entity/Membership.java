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
@Table(name = "membership")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Membership.findAll", query = "SELECT m FROM Membership m"),
    @NamedQuery(name = "Membership.findById", query = "SELECT m FROM Membership m WHERE m.id = :id"),
    @NamedQuery(name = "Membership.findByMembershipNo", query = "SELECT m FROM Membership m WHERE m.membershipNo = :membershipNo"),
    @NamedQuery(name = "Membership.findByType", query = "SELECT m FROM Membership m WHERE m.type = :type"),
    @NamedQuery(name = "Membership.findBySociety", query = "SELECT m FROM Membership m WHERE m.society = :society"),
    @NamedQuery(name = "Membership.findByDistrict", query = "SELECT m FROM Membership m WHERE m.district = :district"),
    @NamedQuery(name = "Membership.findByJoinedDate", query = "SELECT m FROM Membership m WHERE m.joinedDate = :joinedDate"),
    @NamedQuery(name = "Membership.findByPlotId", query = "SELECT m FROM Membership m WHERE m.plotId = :plotId"),
    @NamedQuery(name = "Membership.findByElectricityHookup", query = "SELECT m FROM Membership m WHERE m.electricityHookup = :electricityHookup"),
    @NamedQuery(name = "Membership.findByParkingSpace", query = "SELECT m FROM Membership m WHERE m.parkingSpace = :parkingSpace"),
    @NamedQuery(name = "Membership.findBySwipeCardId", query = "SELECT m FROM Membership m WHERE m.swipeCardId = :swipeCardId"),
    @NamedQuery(name = "Membership.findByLockerId", query = "SELECT m FROM Membership m WHERE m.lockerId = :lockerId"),
    @NamedQuery(name = "Membership.findByWinterStorage", query = "SELECT m FROM Membership m WHERE m.winterStorage = :winterStorage")})
public class Membership implements Serializable {

    @Column(name = "insurance_expiry")
    @Temporal(TemporalType.DATE)
    private Date insuranceExpiry;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "membershipId")
    private Collection<Caravan> caravanCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 20)
    @Column(name = "membership_no")
    private String membershipNo;
    @Size(max = 10)
    @Column(name = "type")
    private String type;
    @Size(max = 45)
    @Column(name = "society")
    private String society;
    @Size(max = 45)
    @Column(name = "district")
    private String district;
    @Column(name = "joined_date")
    @Temporal(TemporalType.DATE)
    private Date joinedDate;
    @Size(max = 45)
    @Column(name = "plot_id")
    private String plotId;
    @Column(name = "electricity_hookup")
    private Boolean electricityHookup;
    @Column(name = "parking_space")
    private Boolean parkingSpace;
    @Size(max = 45)
    @Column(name = "swipe_card_id")
    private String swipeCardId;
    @Size(max = 45)
    @Column(name = "locker_id")
    private String lockerId;
    @Column(name = "winter_storage")
    private Boolean winterStorage;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "membershipId")
    private Collection<Membershipcharge> membershipchargeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "membershipId")
    private Collection<Electricitycharge> electricitychargeCollection;
    @OneToMany(mappedBy = "membershipId")
    private Collection<Customer> customerCollection;

    public Membership() {
    }

    public Membership(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMembershipNo() {
        return membershipNo;
    }

    public void setMembershipNo(String membershipNo) {
        this.membershipNo = membershipNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSociety() {
        return society;
    }

    public void setSociety(String society) {
        this.society = society;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Date getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(Date joinedDate) {
        this.joinedDate = joinedDate;
    }

    public String getPlotId() {
        return plotId;
    }

    public void setPlotId(String plotId) {
        this.plotId = plotId;
    }

    public Boolean getElectricityHookup() {
        return electricityHookup;
    }

    public void setElectricityHookup(Boolean electricityHookup) {
        this.electricityHookup = electricityHookup;
    }

    public Boolean getParkingSpace() {
        return parkingSpace;
    }

    public void setParkingSpace(Boolean parkingSpace) {
        this.parkingSpace = parkingSpace;
    }

    public String getSwipeCardId() {
        return swipeCardId;
    }

    public void setSwipeCardId(String swipeCardId) {
        this.swipeCardId = swipeCardId;
    }

    public String getLockerId() {
        return lockerId;
    }

    public void setLockerId(String lockerId) {
        this.lockerId = lockerId;
    }

    public Boolean getWinterStorage() {
        return winterStorage;
    }

    public void setWinterStorage(Boolean winterStorage) {
        this.winterStorage = winterStorage;
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
    public Collection<Customer> getCustomerCollection() {
        return customerCollection;
    }

    public void setCustomerCollection(Collection<Customer> customerCollection) {
        this.customerCollection = customerCollection;
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
        if (!(object instanceof Membership)) {
            return false;
        }
        Membership other = (Membership) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Membership[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<Caravan> getCaravanCollection() {
        return caravanCollection;
    }

    public void setCaravanCollection(Collection<Caravan> caravanCollection) {
        this.caravanCollection = caravanCollection;
    }

    public Date getInsuranceExpiry() {
        return insuranceExpiry;
    }

    public void setInsuranceExpiry(Date insuranceExpiry) {
        this.insuranceExpiry = insuranceExpiry;
    }
    
}
