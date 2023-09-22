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

    public void receiveCashback(double amount) {
        this.balance += amount;
        this.cashback += amount;
    }

    public double getCashback() {
        return cashback;
    }

}
