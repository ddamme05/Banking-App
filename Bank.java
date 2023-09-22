import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<String, Account> accounts;

    public Bank() {
        this.accounts = new HashMap<>();
    }

    public boolean createAccount(String accountId) {
        if (accounts.containsKey(accountId)) {
            System.out.println("Duplicate account!");
            return false;  // Account already exists.
        }
        accounts.put(accountId, new Account(accountId));
        return true;
    }

    public double deposit(String accountId, double amount) {
        Account account = accounts.get(accountId);
        if (account == null) {
            System.out.println("Account not found!");
            return -1;  // Account not found.
        }
        account.deposit(amount);
        return account.getBalance();
    }

    public double withdraw(String accountId, double amount) {
        Account account = accounts.get(accountId);
        if (account == null) {
            System.out.println("Account not found!");
            return -1;  // Account not found.
        }
        if (!account.withdraw(amount)) {
            System.out.println("Insufficient funds!");
            return -1;  // Insufficient funds.
        }
        return account.getBalance();
    }

    public boolean transfer(String fromAccountId, String toAccountId, double amount) {
        Account fromAccount = accounts.get(fromAccountId);
        Account toAccount = accounts.get(toAccountId);

        if (fromAccount == null || toAccount == null) {
            System.out.println("Transfer involves non-existing account!");
            return false;  // AT least one of the accounts doesn't exist.
        }

        if (!fromAccount.withdraw(amount)) {
            System.out.println("Insufficient balance being transferred!");
            return false;  // Insufficient funds in the 'from' account.
        }

        toAccount.deposit(amount);
        return true;
    }

    public Account getAccount(String accountId) {
        return accounts.get(accountId);
    }
}
