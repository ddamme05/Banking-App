import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Bank bank = new Bank();
        QueryHandler handler = new QueryHandler(bank);

        List<String[]> queries = new ArrayList<>();
        queries.add(new String[]{"CREATE_ACCOUNT", "1", "account3"});
        queries.add(new String[]{"CREATE_ACCOUNT", "2", "account2"});
        queries.add(new String[]{"CREATE_ACCOUNT", "3", "account1"});
        queries.add(new String[]{"DEPOSIT", "4", "account1", "2000"});
        queries.add(new String[]{"DEPOSIT", "5", "account2", "3000"});
        queries.add(new String[]{"DEPOSIT", "6", "account3", "4000"});
        queries.add(new String[]{"TRANSFER", "8", "account3", "account2", "500"});
        queries.add(new String[]{"TRANSFER", "9", "account3", "account1", "1000"});
        queries.add(new String[]{"TRANSFER", "10", "account1", "account2", "2500"});


        List<String> results = handler.handleQueries(queries);

        for (String result : results) {
            System.out.println(result);
        }

        System.out.println("\nStatus of all accounts:");

        Map<String, Double> accountStatus = bank.getAllAccounts();
        for (Map.Entry<String, Double> entry : accountStatus.entrySet()) {
            System.out.println("Account ID: " + entry.getKey() + ", Balance: " + entry.getValue());
        }
    }
}
