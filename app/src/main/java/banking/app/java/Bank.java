package banking.app.java;


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
            account.recordOutgoing(amount); // Add this line
            return account.getBalance();
        }
        return -1;
    }

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


    public List<Account> getTopSpenders(int n) {
        //Creating list with all accounts in db
        List<Account> sortedAccounts = new ArrayList<>(db.getAllAccountObjects());
        //Sorting accounts is total outgoing amt. unless they have same outgoing then we sort alphabetically
        sortedAccounts.sort((a, b) -> {
            if (a.getTotalOutgoing() == b.getTotalOutgoing()) {
                return a.getAccountId().compareTo(b.getAccountId());
            }
            //sorting by outgoing amt. desc
            return Double.compare(b.getTotalOutgoing(), a.getTotalOutgoing());
        });
        //We get the n accounts ranked
        return sortedAccounts.subList(0, Math.min(n, sortedAccounts.size()));
    }

    public double addCashback(String accountId, double amount) {
        Account account = db.getAccount(accountId);
        if (account != null) {
            double cashbackAmount = amount * 0.005; // 0.5% cashback
            account.receiveCashback(cashbackAmount);
            return account.getBalance();
        }
        return -1;
    }

    public boolean mergeAccounts(String sourceAccountId, String targetAccountId) {
        Account sourceAccount = db.getAccount(sourceAccountId);
        Account targetAccount = db.getAccount(targetAccountId);

        if (sourceAccount == null || targetAccount == null) {
            return false; // One or both accounts do not exist
        }

        double sourceBalance = sourceAccount.getBalance();
        targetAccount.deposit(sourceBalance);
        sourceAccount.withdraw(sourceBalance);

        //Combine transaction history of both accounts
        targetAccount.mergeTransactions(sourceAccount.getTransactions());

        db.removeAccount(sourceAccountId);
        return true;
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
