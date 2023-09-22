import java.util.HashMap;
import java.util.Map;

public class MockDB {
    private Map<String, Account> accounts = new HashMap<>();

    public boolean addAccount(Account account) {
        if (!accounts.containsKey(account.getAccountId())) {
            accounts.put(account.getAccountId(), account);
            return true;
        }
        return false;
    }
    public Account getAccount(String accountId) {
        return accounts.get(accountId);
    }
    public boolean removeAccount(String accountId) {
        if (accounts.containsKey(accountId)) {
            accounts.remove(accountId);
            return true;
        }
        return false;
    }

    public boolean accountExists(String accountId) {
        return accounts.containsKey(accountId);
    }
}
