package service;

import data_access.account.AccountDAO;
import data_access.customer.CustomerDAO;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        CustomerDAO customerDAO = new CustomerDAO();

        // list of customers
        System.out.println("list of customers");
        List<Customer> customers = customerDAO.getAllCustomers();
        for (Customer customer : customers) {
            System.out.println(customer.getName());
        }
        // get specific customer
//        Customer customer = customerDAO.getCustomer(1);
//        System.out.println(customer.getName());

        // create customer
//        Customer customer1 = new Customer("Ali", "Ezati", "0924851879", "09158122364", "Keshavarz, Tehran", "ali.ezati@gmail.com", "1376/10/04", 1);
//        customerDAO.createCustomer(customer1);

        // update specific customer
//        Customer customer2 = customerDAO.getCustomer(7);
//        customer2.setBirthDate("1376-10-06");
//        customerDAO.updateCustomer(customer2);

        // delete specific customer
//        Customer customer3 = customerDAO.getCustomer(7);
//        System.out.println(customer3.getName());
//        customerDAO.deleteCustomer(customer3);

        AccountDAO accountDAO = new AccountDAO();

        // list of accounts
        System.out.println("\n" + "list of accounts");
        List<Account> accounts = accountDAO.getAllAccounts();
        for (Account account : accounts) {
            System.out.println(account.getAccountId() + " - " + account.getCustomerId() + " - " + account.getAccountNumber());
        }
        // get specific account
//        Account account = accountDAO.getAccount(1);
//        System.out.println(account.getAccountId() + " - " + account.getCustomerId() + " - " + account.getAccountNumber());

//        // create account
//        Account account1 = new Account(6, "6037123459965225");
//        accountDAO.createAccount(account1);

        // update specific account
//        Account account2 = accountDAO.getAccount(3);
//        account2.setAccountNumber("6037997443743324");
//        accountDAO.updateAccount(account2);

        // delete specific account
//        Account account3 = accountDAO.getAccount(4);
//        System.out.println(account3.getAccountId() + " - " + account3.getCustomerId());
//        accountDAO.deleteAccount(account3);
    }
}
