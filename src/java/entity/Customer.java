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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "customer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c"),
    @NamedQuery(name = "Customer.findById", query = "SELECT c FROM Customer c WHERE c.id = :id"),
    @NamedQuery(name = "Customer.findByForename", query = "SELECT c FROM Customer c WHERE c.forename = :forename"),
    @NamedQuery(name = "Customer.findBySurname", query = "SELECT c FROM Customer c WHERE c.surname = :surname"),
    @NamedQuery(name = "Customer.findByMiddlenames", query = "SELECT c FROM Customer c WHERE c.middlenames = :middlenames"),
    @NamedQuery(name = "Customer.findByDob", query = "SELECT c FROM Customer c WHERE c.dob = :dob"),
    @NamedQuery(name = "Customer.findByTelephoneOne", query = "SELECT c FROM Customer c WHERE c.telephoneOne = :telephoneOne"),
    @NamedQuery(name = "Customer.findByTelephonetwo", query = "SELECT c FROM Customer c WHERE c.telephonetwo = :telephonetwo"),
    @NamedQuery(name = "Customer.findByEmail", query = "SELECT c FROM Customer c WHERE c.email = :email"),
    @NamedQuery(name = "Customer.findByOccupation", query = "SELECT c FROM Customer c WHERE c.occupation = :occupation"),
    @NamedQuery(name = "Customer.findByHobbies", query = "SELECT c FROM Customer c WHERE c.hobbies = :hobbies"),
    @NamedQuery(name = "Customer.findByGiftAid", query = "SELECT c FROM Customer c WHERE c.giftAid = :giftAid")})
public class Customer implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerId")
    private Collection<Visit> visitCollection;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "customer")
    private Refuse refuse;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerId")
    private Collection<Notes> notesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerId")
    private Collection<NextOfKin> nextOfKinCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerId")
    private Collection<AllowPhotography> allowPhotographyCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "forename")
    private String forename;
    @Size(max = 45)
    @Column(name = "surname")
    private String surname;
    @Size(max = 120)
    @Column(name = "middlenames")
    private String middlenames;
    @Column(name = "dob")
    @Temporal(TemporalType.DATE)
    private Date dob;
    @Size(max = 16)
    @Column(name = "telephone_one")
    private String telephoneOne;
    @Size(max = 16)
    @Column(name = "telephonetwo")
    private String telephonetwo;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "email")
    private String email;
    @Size(max = 45)
    @Column(name = "occupation")
    private String occupation;
    @Size(max = 200)
    @Column(name = "hobbies")
    private String hobbies;
    @Column(name = "gift_aid")
    private Boolean giftAid;
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "customerCollection")
    private Collection<Child> childCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerId")
    private Collection<Image> imageCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerId")
    private Collection<Car> carCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private Collection<CustomerIsAttendingEvent> customerIsAttendingEventCollection;
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Address addressId;
    @OneToMany(mappedBy = "partnerId")
    private Collection<Customer> customerCollection;
    @JoinColumn(name = "partner_id", referencedColumnName = "id")
    @ManyToOne
    private Customer partnerId;
    @JoinColumn(name = "membership_id", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Membership membershipId;

    public Customer() {
    }

    public Customer(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddlenames() {
        return middlenames;
    }

    public void setMiddlenames(String middlenames) {
        this.middlenames = middlenames;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getTelephoneOne() {
        return telephoneOne;
    }

    public void setTelephoneOne(String telephoneOne) {
        this.telephoneOne = telephoneOne;
    }

    public String getTelephonetwo() {
        return telephonetwo;
    }

    public void setTelephonetwo(String telephonetwo) {
        this.telephonetwo = telephonetwo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public Boolean getGiftAid() {
        return giftAid;
    }

    public void setGiftAid(Boolean giftAid) {
        this.giftAid = giftAid;
    }

    @XmlTransient
    public Collection<Child> getChildCollection() {
        return childCollection;
    }

    public void setChildCollection(Collection<Child> childCollection) {
        this.childCollection = childCollection;
    }

    @XmlTransient
    public Collection<Image> getImageCollection() {
        return imageCollection;
    }

    public void setImageCollection(Collection<Image> imageCollection) {
        this.imageCollection = imageCollection;
    }

    @XmlTransient
    public Collection<Car> getCarCollection() {
        return carCollection;
    }

    public void setCarCollection(Collection<Car> carCollection) {
        this.carCollection = carCollection;
    }

    @XmlTransient
    public Collection<CustomerIsAttendingEvent> getCustomerIsAttendingEventCollection() {
        return customerIsAttendingEventCollection;
    }

    public void setCustomerIsAttendingEventCollection(Collection<CustomerIsAttendingEvent> customerIsAttendingEventCollection) {
        this.customerIsAttendingEventCollection = customerIsAttendingEventCollection;
    }

    public Address getAddressId() {
        return addressId;
    }

    public void setAddressId(Address addressId) {
        this.addressId = addressId;
    }

    @XmlTransient
    public Collection<Customer> getCustomerCollection() {
        return customerCollection;
    }

    public void setCustomerCollection(Collection<Customer> customerCollection) {
        this.customerCollection = customerCollection;
    }

    public Customer getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Customer partnerId) {
        this.partnerId = partnerId;
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
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Customer[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<Notes> getNotesCollection() {
        return notesCollection;
    }

    public void setNotesCollection(Collection<Notes> notesCollection) {
        this.notesCollection = notesCollection;
    }

    @XmlTransient
    public Collection<NextOfKin> getNextOfKinCollection() {
        return nextOfKinCollection;
    }

    public void setNextOfKinCollection(Collection<NextOfKin> nextOfKinCollection) {
        this.nextOfKinCollection = nextOfKinCollection;
    }

    @XmlTransient
    public Collection<AllowPhotography> getAllowPhotographyCollection() {
        return allowPhotographyCollection;
    }

    public void setAllowPhotographyCollection(Collection<AllowPhotography> allowPhotographyCollection) {
        this.allowPhotographyCollection = allowPhotographyCollection;
    }

    public Refuse getRefuse() {
        return refuse;
    }

    public void setRefuse(Refuse refuse) {
        this.refuse = refuse;
    }

    @XmlTransient
    public Collection<Visit> getVisitCollection() {
        return visitCollection;
    }

    public void setVisitCollection(Collection<Visit> visitCollection) {
        this.visitCollection = visitCollection;
    }
    
}
