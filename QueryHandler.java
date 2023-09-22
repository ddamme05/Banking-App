import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QueryHandler {
    private Bank bank;

    public QueryHandler(Bank bank) {
        this.bank = bank;
    }

    public List<String> handleQueries(List<String[]> queries) {
        List<String> results = new ArrayList<>();

        for (String[] query : queries) {
            switch (query[0]) {
                case "CREATE_ACCOUNT":
                    results.add(String.valueOf(bank.createAccount(query[2])));
                    break;
                case "DEPOSIT":
                    double depositResult = bank.deposit(query[2], Double.parseDouble(query[3]));
                    results.add(depositResult == -1 ? "Error depositing!" : String.valueOf(depositResult));
                    break;
                case "WITHDRAW":
                    double withdrawResult = bank.withdraw(query[2], Double.parseDouble(query[3]));
                    results.add(withdrawResult == -1 ? "Error withdrawing!" : String.valueOf(withdrawResult));
                    break;
                case "TRANSFER":
                    double transferResult = bank.transfer(query[2], query[3], Double.parseDouble(query[4]));
                    results.add(transferResult == -1 ? "Error transferring!" : String.valueOf(transferResult));
                    break;
                case "TOP_SPENDERS":
                    int n = Integer.parseInt(query[1]);
                    List<Account> topAccounts = bank.getTopSpenders(n);
                    StringBuilder sb = new StringBuilder();
                    for (Account acc : topAccounts) {
                        if (sb.length() > 0) { sb.append(", "); }
                        sb.append(acc.getAccountId()).append("(").append(acc.getTotalOutgoing()).append(")");
                    }
                    results.add(sb.toString());
                    break;
                case "CASHBACK":
                    String accountId = query[1];
                    Account acc = bank.getAccount(accountId);
                    if (acc != null) {
                        double totalOutgoing = acc.getTotalOutgoing();
                        double cashbackAmount = totalOutgoing * 0.005; // 0.5% cashback
                        bank.addCashback(accountId, cashbackAmount);
                        results.add(String.valueOf(cashbackAmount));
                    } else {
                        results.add("Account not found!");
                    }
                    break;

                case "MERGE_ACCOUNTS":
                    boolean mergeSuccess = bank.mergeAccounts(query[1], query[2]);
                    results.add(mergeSuccess ? "Merge Successful!" : "Error merging!");
                    break;
                default:
                    results.add("Unsupported query!");
                    break;
            }
        }
        return results;
    }
}
