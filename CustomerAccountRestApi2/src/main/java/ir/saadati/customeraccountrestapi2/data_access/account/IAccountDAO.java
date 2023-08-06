package ir.saadati.customeraccountrestapi2.data_access.account;

import ir.saadati.customeraccountrestapi2.service.Account;

import java.util.List;

/**
 * Interface of Account data access for CRUD operation with database
 *
 * @author write with Anushe Saadati
 */
public interface IAccountDAO {
    /**
     * Select all accounts from database and return as a list of objects
     *
     * @return List<Account> list of accounts
     * @throws Exception connecting to database
     */
    List<Account> getAllAccounts() throws Exception;

    /**
     * Select specific account from database and return as an object
     *
     * @param accountId specific id for searching specific account
     * @return Account object of specific account
     * @throws Exception connecting to database
     */
    Account getAccount(int accountId) throws Exception;

    /**
     * Create specific account and save it to database
     *
     * @param account specific account object
     * @return Account object of specific account
     * @throws Exception connecting to database
     */
    Account createAccount(Account account) throws Exception;

    /**
     * Update specific account with specific id and save it to database
     *
     * @param account specific account object
     * @return Account object of specific account
     * @throws Exception connecting to database
     */
    Account updateAccount(Account account) throws Exception;

    /**
     * Delete specific account with specific id from database
     *
     * @param account specific account object
     * @return int count of row effected
     * @throws Exception connecting to database
     */
    int deleteAccount(Account account) throws Exception;
}