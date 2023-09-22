import java.util.*;

public class Bank {
    private MockDB db = new MockDB();

    public boolean createAccount(String accountId) {
        if (!db.accountExists(accountId)) {
            Account account = new Account(accountId);
            db.addAccount(account);
            return true;
        }
        return false;
    }

    public Account getAccount(String accountId) {
        return db.getAccount(accountId);
    }

    public boolean deleteAccount(String accountId) {
        return db.removeAccount(accountId);
    }

    public double deposit(String accountId, double amount) {
        Account account = db.getAccount(accountId);
        if (account != null) {
            account.deposit(amount);
            return account.getBalance();
        }
        return -1;
    }

    public double withdraw(String accountId, double amount) {
        Account account = db.getAccount(accountId);
        if (account != null && account.withdraw(amount)) {
            return account.getBalance();
        }
        return -1;
    }

    public double transfer(String fromAccountId, String toAccountId, double amount) {
        Account fromAccount = db.getAccount(fromAccountId);
        Account toAccount = db.getAccount(toAccountId);

        if (fromAccount != null && toAccount != null && fromAccount.withdraw(amount)) {
            toAccount.deposit(amount);
            return fromAccount.getBalance();
        }
        return -1;
    }

    public double getBalance(String accountId) {
        Account account = db.getAccount(accountId);
        if (account != null) {
            return account.getBalance();
        }
        return -1;
    }

    public Map<String, Double> getAllAccounts() {
        return db.getAllAccounts();
    }
}
