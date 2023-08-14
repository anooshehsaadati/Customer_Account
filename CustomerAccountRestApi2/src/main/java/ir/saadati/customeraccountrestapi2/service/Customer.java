package ir.saadati.customeraccountrestapi2.service;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Customer Class with attribute
 *
 * @author write with Anushe Saadati
 */
@XmlRootElement
public class Customer {
    /**
     * customer id is unique and unique for each customer
     */
    private int customerId;

    /**
     * first name of customer
     */
    private String firstName;

    /**
     * last name of customer
     */
    private String lastName;

    /**
     * identification number of customer
     */
    private String idNumber;

    /**
     * phone number of customer
     */
    private String phoneNumber;

    /**
     * home/work address of customer
     */
    private String address;

    /**
     * email address of customer
     */
    private String email;

    /**
     * date of birth of customer
     */
    private String birthDate;

    /**
     * gender of customer
     * 0: No gender
     * 1: Female
     * 2: Male
     */
    private int Gender;

    /**
     * date and time of creation in database
     */
    private String createdDate;

    /**
     * date and time of last update in database
     */
    private String updatedDate;

    /**
     * Constructor for Customer Class and use for saving record of customer table to it
     *
     * @param customerId unique id
     * @param firstName first name of customer
     * @param lastName last name of customer
     * @param idNumber identification number of customer
     * @param phoneNumber phone number of customer
     * @param address address of customer
     * @param email email address of customer
     * @param birthDate date of birth of customer
     * @param gender gender of customer
     * @param createdDate date and time of creation
     * @param updatedDate date and time of last update
     */
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

    /**
     * default Customer Constructor
     */
    public Customer() {

    }

    /**
     * Constructor for Customer Class and use for create or update database
     * @param firstName first name of customer
     * @param lastName last name of customer
     * @param idNumber identification number of customer
     * @param phoneNumber phone number of customer
     * @param address address of customer
     * @param email email address of customer
     * @param birthDate date of birth of customer
     * @param gender gender of customer
     */
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
     * get customerId attribute
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
     * get first Name attribute
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
     * get last Name attribute
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
     * get id Number attribute
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
     * get phone Number attribute
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
     * get address attribute
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
     * get email attribute
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
     * get birth Data attribute
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
     * get Gender attribute
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
     * get created Date attribute
     *
     * @return createdDate
     */
    @XmlElement
    public String getCreatedDate() {
        return createdDate;
    }

    /**
     * get updated Date attribute
     *
     * @return updatedDate
     */
    @XmlElement
    public String getUpdatedDate() {
        return updatedDate;
    }

    /**
     * change all attribute of Customer Class to string
     *
     * @return string of all attribute of Customer class
     */
    @Override
    public String toString() {
        return "Customer{" + "customerId=" + customerId + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", idNumber='" + idNumber + '\'' + ", phoneNumber='" + phoneNumber + '\'' + ", address='" + address + '\'' + ", email='" + email + '\'' + ", birthDate='" + birthDate + '\'' + ", Gender=" + Gender + ", createdDate='" + createdDate + '\'' + ", updatedDate='" + updatedDate + '\'' + '}';
    }
}
