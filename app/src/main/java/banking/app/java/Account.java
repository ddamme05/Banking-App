package banking.app.java;


import java.util.ArrayList;
import java.util.List;

/**
 * Represents a bank account with operations like deposit, withdrawal, and more.
 * Each account has an associated unique ID, balance, and a list of transactions.
 */

public class Account {
    private String accountId;
    private double balance;
    //Initialized as a list for polymorphism, I may make it LinkedList in the future
    private List<Transaction> transactions;
    private double totalOutgoing;
    private double cashback;

    /**
     * Creates a new account with the specified ID and an initial balance of 0.
     *
     * @param accountId the unique identifier for the account
     */
    public Account(String accountId) {
        this.accountId = accountId;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
    }

    public String getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }

    /**
     * Deposits the specified amount into the account.
     *
     * @param amount the amount to deposit
     * @return the new balance after the deposit
     */
    public void deposit(double amount) {
        balance += amount;
        transactions.add(new Transaction(amount, Transaction.Type.DEPOSIT));
    }

    /**
     * Withdraws the specified amount from the account.
     *
     * @param amount the amount to withdraw
     * @return the new balance after the withdrawal
     */
    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            transactions.add(new Transaction(amount, Transaction.Type.WITHDRAW));
            return true;
        }
        return false;
    }

    /**
     * Retrieves a list of all transactions associated with the account.
     *
     * @return a list of transactions
     */
    public List<Transaction> getTransactions() {
        return transactions;
    }

    /**
     * Records an outgoing transaction for the account.
     *
     * @param amount the amount of the outgoing transaction
     */
    public void recordOutgoing(double amount) {
        totalOutgoing += amount;
    }

    /**
     * Retrieves the total amount of all outgoing transactions for the account.
     *
     * @return the total outgoing amount
     */
    public double getTotalOutgoing() {
        return totalOutgoing;
    }

    /**
     * Retrieves the total cashback received for the account.
     *
     * @return the total cashback amount
     */
    public double getCashback() {
        return cashback;
    }

    /**
     * Merges the provided list of transactions into the account's transactions.
     *
     * @param otherTransactions the transactions to merge
     */
    public void mergeTransactions(List<Transaction> otherTransactions) {
        this.transactions.addAll(otherTransactions);
    }

    /**
     * Receives a cashback amount, updates the balance and records the transaction.
     *
     * @param amount the cashback amount
     */
    public void receiveCashback(double amount) {
        balance += amount;
        cashback += amount;
        transactions.add(new Transaction(amount, Transaction.Type.CASHBACK));
    }

    /**
     * Simulates shopping with cashback. If the account has sufficient funds,
     * it deducts the shopping amount, adds the cashback, and records the transaction.
     *
     * @param amount the shopping amount
     * @return the cashback amount received from the shopping
     */
    public double shopWithCashback(double amount) {
        double cashbackAmount = 0.0;
        if (amount <= balance) {
            balance -= amount;
            cashbackAmount = amount * 0.005;
            balance += cashbackAmount;
            transactions.add(new Transaction(amount, Transaction.Type.CASHBACK));
            recordOutgoing(amount);
        }
        return cashbackAmount;
    }
}
