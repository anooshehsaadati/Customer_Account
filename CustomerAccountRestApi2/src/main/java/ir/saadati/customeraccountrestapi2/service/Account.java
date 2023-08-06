package ir.saadati.customeraccountrestapi2.service;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Account Class with attribute and related to Customer Class
 * each customer can more than one account but unique account number
 *
 * @author write with Anushe Saadati
 */
@XmlRootElement
public class Account {
    /**
     * account id is unique and unique for each account
     */
    private int accountId;

    /**
     * customer id is key for relation between account and customer
     */
    private int customerId;

    /**
     * account number is unique for each customer
     */
    private String accountNumber;

    /**
     * date and time of creation in database
     */
    private String createdDate;

    /**
     * date and time of last update in database
     */
    private String updatedDate;

    /**
     * Constructor for Account Class and use for saving record of account table to it
     *
     * @param accountId     unique id
     * @param customerId    relation between account and customer
     * @param accountNumber account number is unique for each customer
     * @param createdDate   date and time of creation
     * @param updatedDate   date and time of last update
     */
    public Account(int accountId, int customerId, String accountNumber, String createdDate, String updatedDate) {
        this.accountId = accountId;
        this.setCustomerId(customerId);
        this.setAccountNumber(accountNumber);
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    /**
     * default Account Constructor
     */
    public Account() {

    }

    /**
     * Constructor for Account Class and use for create or update database
     *
     * @param customerId    relation between account and customer
     * @param accountNumber account number is unique for each customer
     */
    public Account(int customerId, String accountNumber) {
        this.setCustomerId(customerId);
        this.setAccountNumber(accountNumber);
    }

    /**
     * get accountId attribute
     *
     * @return accountId
     */
    @XmlElement
    public int getAccountId() {
        return accountId;
    }

    /**
     * set value to accountId
     *
     * @param accountId set it
     */
    public void setAccountId(int accountId) {
        this.accountId = accountId;
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
     * set value to customer id
     *
     * @param customerId set it
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * get accountNumber attribute
     *
     * @return accountNumber
     */
    @XmlElement
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * set value to account Number
     *
     * @param accountNumber set it
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * get createdDate attribute
     *
     * @return createdDate
     */
    @XmlElement
    public String getCreatedDate() {
        return createdDate;
    }

    /**
     * get updatedDate attribute
     *
     * @return updatedDate
     */
    @XmlElement
    public String getUpdatedDate() {
        return updatedDate;
    }

    /**
     * change all attribute of Account Class to string
     *
     * @return string of all attribute of Account class
     */
    @Override
    public String toString() {
        return "Account{" + "accountId=" + accountId + ", customerId=" + customerId + ", accountNumber='" + accountNumber + '\'' + ", createdDate='" + createdDate + '\'' + ", updatedDate='" + updatedDate + '\'' + '}';
    }
}
