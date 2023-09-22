import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QueryHandler {
    private Bank bank;

    public QueryHandler(Bank bank) {
        this.bank = bank;
    }

    public Bank getBank() {
        return bank;
    }


    public List<String> handleQueries(List<String[]> queries) {
        List<String> results = new ArrayList<>();

        /*
        query[0] = operation
        query[2] = which account we are withdrawing from / depositing to / account to create
        query[3] = amt to be withdrawn / deposited OR target account for transfer
        query[4] = amt to be transferred (only for TRANSFER)
        Queries return the current balance of an account or a boolean.
        */

        for (String[] query : queries) {
            switch (query[0]) { // Represents operation in the query
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

                default:
                    results.add("Unsupported query!");
                    break;
            }
        }
        return results;
    }
}
