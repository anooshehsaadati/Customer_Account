package ir.saadati.customeraccountrestapi2.service;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Account {
    private int accountId;
    private int customerId;
    private String accountNumber;
    private String createdDate;
    private String updatedDate;

    public Account(int accountId, int customerId, String accountNumber, String createdDate, String updatedDate) {
        this.accountId = accountId;
        this.setCustomerId(customerId);
        this.setAccountNumber(accountNumber);
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public Account(int customerId, String accountNumber) {
        this.setCustomerId(customerId);
        this.setAccountNumber(accountNumber);
    }

    /**
     * return accountId attribute
     *
     * @return accountId
     */
    public int getAccountId() {
        return accountId;
    }

    /**
     * return customerId attribute
     *
     * @return customerId
     */
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
     * return accountNumber attribute
     *
     * @return accountNumber
     */
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
     * return createdDate attribute
     *
     * @return createdDate
     */
    public String getCreatedDate() {
        return createdDate;
    }

    /**
     * return updatedDate attribute
     *
     * @return updatedDate
     */
    public String getUpdatedDate() {
        return updatedDate;
    }
}
