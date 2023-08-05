package ir.saadati.customeraccountrestapi2.service;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Customer {
    private int customerId;
    private String firstName;
    private String lastName;
    private String idNumber;
    private String phoneNumber;
    private String address;
    private String email;
    private String birthDate;
    private int Gender;
    private String createdDate;
    private String updatedDate;

    public Customer(int customerId, String firstName, String lastName, String idNumber, String phoneNumber, String address, String email, String birthDate, int gender, String createdDate, String updatedDate) {
        this.customerId = customerId;
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setIdNumber(idNumber);
        this.setPhoneNumber(phoneNumber);
        this.setAddress(address);
        this.setEmail(email);
        this.setBirthDate(birthDate);
        this.setGender(gender);
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public Customer() {

    }

    public Customer(String firstName, String lastName, String idNumber, String phoneNumber, String address, String email, String birthDate, int gender) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setIdNumber(idNumber);
        this.setPhoneNumber(phoneNumber);
        this.setAddress(address);
        this.setEmail(email);
        this.setBirthDate(birthDate);
        this.setGender(gender);
    }

    /**
     * return customerId attribute
     *
     * @return customerId
     */
    @XmlElement
    public int getCustomerId() {
        return customerId;
    }

    /**
     * set value to customerId
     *
     * @param customerId set it
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * return first Name attribute
     *
     * @return first Name
     */
    @XmlElement
    public String getFirstName() {
        return firstName;
    }

    /**
     * set value to first Name
     *
     * @param firstName set it
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * return last Name attribute
     *
     * @return lastName
     */
    @XmlElement
    public String getLastName() {
        return lastName;
    }

    /**
     * set value to last Name
     *
     * @param lastName set it
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * return Full Name attribute
     *
     * @return Name
     */
    @XmlElement
    public String getName() {
        return this.getFirstName() + " " + this.getLastName();
    }

    /**
     * return id Number attribute
     *
     * @return idNumber
     */
    @XmlElement
    public String getIdNumber() {
        return idNumber;
    }

    /**
     * set value to id Number
     *
     * @param idNumber set it
     */
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    /**
     * return phone Number attribute
     *
     * @return phoneNumber
     */
    @XmlElement
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * set value to phone Number
     *
     * @param phoneNumber set it
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * return address attribute
     *
     * @return address
     */
    @XmlElement
    public String getAddress() {
        return address;
    }

    /**
     * set value to address
     *
     * @param address set it
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * return email attribute
     *
     * @return email
     */
    @XmlElement
    public String getEmail() {
        return email;
    }

    /**
     * set value to email
     *
     * @param email set it
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * return birth Data attribute
     *
     * @return birthDate
     */
    @XmlElement
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * set value to birth Data
     *
     * @param birthDate set it
     */
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * return Gender attribute
     *
     * @return Gender
     */
    @XmlElement
    public int getGender() {
        return Gender;
    }

    /**
     * set value to gender
     *
     * @param gender set it
     */
    public void setGender(int gender) {
        Gender = gender;
    }

    /**
     * return created Date attribute
     *
     * @return createdDate
     */
    @XmlElement
    public String getCreatedDate() {
        return createdDate;
    }

    /**
     * return updated Date attribute
     *
     * @return updatedDate
     */
    @XmlElement
    public String getUpdatedDate() {
        return updatedDate;
    }

    @Override
    public String toString() {
        return "Customer{" + "customerId=" + customerId + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", idNumber='" + idNumber + '\'' + ", phoneNumber='" + phoneNumber + '\'' + ", address='" + address + '\'' + ", email='" + email + '\'' + ", birthDate='" + birthDate + '\'' + ", Gender=" + Gender + ", createdDate='" + createdDate + '\'' + ", updatedDate='" + updatedDate + '\'' + '}';
    }
}
