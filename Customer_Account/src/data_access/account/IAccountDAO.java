package data_access.account;

import service.Account;

import java.util.List;

public interface IAccountDAO {
    List<Account> getAllAccounts() throws Exception;

    Account getAccount(int accountId) throws Exception;

    void createAccount(Account account) throws Exception;

    void updateAccount(Account account) throws Exception;

    void deleteAccount(Account account) throws Exception;
}