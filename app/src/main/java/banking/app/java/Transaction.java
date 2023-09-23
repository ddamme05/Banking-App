package banking.app.java;

/**
 * Represents a transaction that takes place in a bank account.
 * A transaction has an associated amount and a type (DEPOSIT, WITHDRAW, or CASHBACK).
 */
public class Transaction {
    /**
     * Enum representing the different types of transactions.
     * - DEPOSIT: Represents a deposit into an account.
     * - WITHDRAW: Represents a withdrawal from an account.
     * - CASHBACK: Represents a cashback received after a shopping transaction.
     */
    public enum Type {DEPOSIT, WITHDRAW, CASHBACK}
    private double amount;
    private Type type;

    /**
     * Constructs a new Transaction with the specified amount and type.
     *
     * @param amount the amount involved in the transaction
     * @param type the type of transaction (DEPOSIT, WITHDRAW, or CASHBACK)
     */
    public Transaction(double amount, Type type) {
        this.amount = amount;
        this.type = type;
    }

    /**
     * Returns the amount involved in the transaction.
     *
     * @return the transaction amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Returns the type of the transaction.
     *
     * @return the transaction type (DEPOSIT, WITHDRAW, or CASHBACK)
     */
    public Type getType() {
        return type;
    }
}
