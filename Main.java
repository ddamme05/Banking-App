import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        QueryHandler handler = new QueryHandler();

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

        // Process the queries and get the results.
        List<String> results = handler.handleQueries(queries);

        // Print the results to the console with a for in.
        for (String result : results) {
            System.out.println(result);
        }
    }
}
