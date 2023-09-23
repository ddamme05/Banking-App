package banking.app.java;


import java.util.ArrayList;
import java.util.List;

public class Account {
    private String accountId;
    private double balance;
    //Initialized as a list for polymorphism, I may make it LinkedList in the future
    private List<Transaction> transactions;
    private double totalOutgoing;
    private double cashback;

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

    public void deposit(double amount) {
        balance += amount;
        transactions.add(new Transaction(amount, Transaction.Type.DEPOSIT));
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            transactions.add(new Transaction(amount, Transaction.Type.WITHDRAW));
            return true;
        }
        return false;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void recordOutgoing(double amount) {
        totalOutgoing += amount;
    }

    public double getTotalOutgoing() {
        return totalOutgoing;
    }

    public double getCashback() {
        return cashback;
    }

    public void mergeTransactions(List<Transaction> otherTransactions) {
        this.transactions.addAll(otherTransactions);
    }
    public void receiveCashback(double amount) {
        balance += amount;
        cashback += amount;
        transactions.add(new Transaction(amount, Transaction.Type.CASHBACK));
    }

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
