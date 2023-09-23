package banking.app.java;


import banking.app.java.Account;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a mock database for managing accounts.
 * This class provides basic CRUD operations for accounts without
 * persisting data to an actual database, simulating database interactions.
 */
public class MockDB {
    private Map<String, Account> accounts = new HashMap<>();

    /**
     * Adds a new account to the mock database.
     *
     * @param account the account to add
     * @return true if the account was added successfully, false if an account with the same ID already exists
     */
    public boolean addAccount(Account account) {
        if (!accounts.containsKey(account.getAccountId())) {
            accounts.put(account.getAccountId(), account);
            return true;
        }
        return false;
    }

    /**
     * Retrieves an account from the mock database by its ID.
     *
     * @param accountId the ID of the account to retrieve
     * @return the account with the given ID, or null if not found
     */
    public Account getAccount(String accountId) {
        return accounts.get(accountId);
    }

    /**
     * Removes an account from the mock database.
     *
     * @param accountId the ID of the account to remove
     * @return true if the account was successfully removed, false otherwise
     */
    public boolean removeAccount(String accountId) {
        if (accounts.containsKey(accountId)) {
            accounts.remove(accountId);
            return true;
        }
        return false;
    }

    /**
     * Checks if an account with the specified ID exists in the mock database.
     *
     * @param accountId the ID of the account to check
     * @return true if the account exists, false otherwise
     */
    public boolean accountExists(String accountId) {
        return accounts.containsKey(accountId);
    }

    /**
     * Retrieves all account objects from the mock database.
     *
     * @return a collection of all account objects
     */
    public Collection<Account> getAllAccountObjects() {
        return accounts.values();
    }

    /**
     * Retrieves all accounts and their respective balances from the mock database.
     *
     * @return a map where the key is the account ID and the value is its balance
     */
    public Map<String, Double> getAllAccounts() {
        Map<String, Double> accountBalances = new HashMap<>();
        for (Map.Entry<String, Account> entry : accounts.entrySet()) {
            accountBalances.put(entry.getKey(), entry.getValue().getBalance());
        }
        return accountBalances;
    }

    /**
     * Updates the details of an account in the mock database.
     * If the account doesn't exist, no action is taken.
     *
     * @param account the account with updated details
     */
    public void updateAccount(Account account) {
        if (account != null && accountExists(account.getAccountId())) {
            accounts.put(account.getAccountId(), account);
        }
    }

}
