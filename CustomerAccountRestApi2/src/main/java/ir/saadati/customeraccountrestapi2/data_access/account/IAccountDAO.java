package ir.saadati.customeraccountrestapi2.data_access.account;

import ir.saadati.customeraccountrestapi2.service.Account;

import java.util.List;

public interface IAccountDAO {
    List<Account> getAllAccounts() throws Exception;

    Account getAccount(int accountId) throws Exception;

    Account createAccount(Account account) throws Exception;

    Account updateAccount(Account account) throws Exception;

    int deleteAccount(Account account) throws Exception;
}