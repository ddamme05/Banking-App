package banking.app.java;

import java.util.*;

/**
 * Represents a bank with functionalities to perform bank operations
 * and more. Internally, it uses a mock database to store and manage account data.
 */
public class Bank {
    private MockDB db = new MockDB();

    /**
     * Creates a new account with the given account ID.
     *
     * @param accountId the unique identifier for the account
     * @return true if the account was successfully created, false if it already exists
     */
    public boolean createAccount(String accountId) {
        if (!db.accountExists(accountId)) {
            Account account = new Account(accountId);
            db.addAccount(account);
            return true;
        }
        return false;
    }

    /**
     * Retrieves an account by its ID.
     *
     * @param accountId the ID of the account to retrieve
     * @return the account if it exists, null otherwise
     */
    public Account getAccount(String accountId) {
        return db.getAccount(accountId);
    }

    /**
     * Deletes an account identified by the given account ID.
     *
     * @param accountId the ID of the account to delete
     * @return true if the account was successfully deleted, false otherwise
     */
    public boolean deleteAccount(String accountId) {
        return db.removeAccount(accountId);
    }

    /**
     * Deposits a specific amount into an account identified by its ID.
     *
     * @param accountId the ID of the account to deposit into
     * @param amount    the amount to deposit
     * @return the new balance after the deposit or -1 if the account does not exist
     */
    public double deposit(String accountId, double amount) {
        Account account = db.getAccount(accountId);
        if (account != null) {
            account.deposit(amount);
            return account.getBalance();
        }
        return -1;
    }

    /**
     * Withdraws a specific amount from an account identified by its ID.
     *
     * @param accountId the ID of the account to withdraw from
     * @param amount    the amount to withdraw
     * @return the new balance after the withdrawal or -1 if the account does not exist or has insufficient funds
     */
    public double withdraw(String accountId, double amount) {
        Account account = db.getAccount(accountId);
        if (account != null && account.withdraw(amount)) {
            account.recordOutgoing(amount);
            return account.getBalance();
        }
        return -1;
    }

    /**
     * Transfers a specific amount from one account to another.
     *
     * @param fromAccountId the ID of the account to transfer from
     * @param toAccountId   the ID of the account to transfer to
     * @param amount        the amount to transfer
     * @return the new balance of the source account after the transfer or -1 if the transfer is not possible
     */
    public double transfer(String fromAccountId, String toAccountId, double amount) {
        Account fromAccount = db.getAccount(fromAccountId);
        Account toAccount = db.getAccount(toAccountId);

        if (fromAccount != null && toAccount != null && fromAccount.withdraw(amount)) {
            toAccount.deposit(amount);
            fromAccount.recordOutgoing(amount);
            return fromAccount.getBalance();
        }
        return -1;
    }

    /**
     * Simulates a shopping transaction with cashback for a given account.
     *
     * @param accountId the ID of the account to use for the shopping transaction
     * @param shop      the name of the shop (unused in the current method but can be utilized in future extensions)
     * @param amount    the shopping amount
     * @return the cashback amount received from the shopping or -1 if the account does not exist or has insufficient funds
     */
    public double shopWithCashback(String accountId, String shop, double amount) {
        Account account = db.getAccount(accountId);
        if (account != null) {
            return account.shopWithCashback(amount);
        }
        return -1;
    }

    /**
     * Retrieves a list of the top spenders from all accounts.
     *
     * @param n the number of top spenders to retrieve
     * @return a list of accounts sorted by total outgoing amounts in descending order
     */
    public List<Account> getTopSpenders(int n) {
        List<Account> sortedAccounts = new ArrayList<>(db.getAllAccountObjects());
        sortedAccounts.sort((a, b) -> {
            if (a.getTotalOutgoing() == b.getTotalOutgoing()) {
                return a.getAccountId().compareTo(b.getAccountId());
            }
            return Double.compare(b.getTotalOutgoing(), a.getTotalOutgoing());
        });
        return sortedAccounts.subList(0, Math.min(n, sortedAccounts.size()));
    }

    /**
     * Merges the balances and transactions of two accounts and deletes the source account.
     *
     * @param sourceAccountId the ID of the account to merge from
     * @param targetAccountId the ID of the account to merge into
     * @return true if the merge was successful, false otherwise
     */
    public boolean mergeAccounts(String sourceAccountId, String targetAccountId) {
        Account sourceAccount = db.getAccount(sourceAccountId);
        Account targetAccount = db.getAccount(targetAccountId);

        if (sourceAccount == null || targetAccount == null) {
            return false;
        }

        double sourceBalance = sourceAccount.getBalance();
        targetAccount.deposit(sourceBalance);
        sourceAccount.withdraw(sourceBalance);
        targetAccount.mergeTransactions(sourceAccount.getTransactions());
        db.removeAccount(sourceAccountId);
        return true;
    }

    /**
     * Retrieves the balance of an account identified by its ID.
     *
     * @param accountId the ID of the account to retrieve the balance for
     * @return the balance of the account or -1 if the account does not exist
     */
    public double getBalance(String accountId) {
        Account account = db.getAccount(accountId);
        if (account != null) {
            return account.getBalance();
        }
        return -1;
    }

    /**
     * Retrieves a map of all accounts with their respective balances.
     *
     * @return a map where the key is the account ID and the value is its balance
     */
    public Map<String, Double> getAllAccounts() {
        return db.getAllAccounts();
    }
}
