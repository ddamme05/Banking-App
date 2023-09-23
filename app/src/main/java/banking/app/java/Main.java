package banking.app.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        executeCapitalOneFlow();
        executeTDBankFlow();
    }

    public static void executeCapitalOneFlow() {
        Bank capital_one = new Bank();
        QueryHandler capitalOneHandler = new QueryHandler(capital_one);
        List<String> capitalOneResults = executeCapitalOneQueries(capitalOneHandler);

        for (String result : capitalOneResults) {
            System.out.println(result);
        }

        System.out.println("\nStatus of all accounts at Capital One:");
        printAccountStatus(capital_one);
    }

    public static List<String> executeCapitalOneQueries(QueryHandler handler) {
        List<String[]> queries = new ArrayList<>();
        queries.add(new String[]{"CREATE_ACCOUNT", "1", "account3"});
        queries.add(new String[]{"CREATE_ACCOUNT", "2", "account2"});
        queries.add(new String[]{"CREATE_ACCOUNT", "3", "account1"});
        queries.add(new String[]{"DEPOSIT", "4", "account1", "2000"});
        queries.add(new String[]{"DEPOSIT", "5", "account2", "3000"});
        queries.add(new String[]{"DEPOSIT", "6", "account3", "4000"});
        queries.add(new String[]{"TOP_SPENDERS", "4"});
        queries.add(new String[]{"TRANSFER", "7", "account3", "account2", "500"});
        queries.add(new String[]{"TRANSFER", "8", "account3", "account1", "1000"});
        queries.add(new String[]{"TRANSFER", "9", "account1", "account2", "2500"});
        queries.add(new String[]{"TOP_SPENDERS", "3"});
        queries.add(new String[]{"TRANSFER", "10", "account1", "account2", "500"});
        queries.add(new String[]{"CASHBACK", "account1"});
        queries.add(new String[]{"MERGE_ACCOUNTS", "account1", "account2"});

        return handler.handleQueries(queries);
    }

    public static void executeTDBankFlow() {
        Bank td_bank = new Bank();
        QueryHandler tdBankHandler = new QueryHandler(td_bank);
        List<String> tdBankResults = executeTDBankQueries(tdBankHandler);

        for (String result : tdBankResults) {
            System.out.println(result);
        }

        System.out.println("\nStatus of all accounts at TD Bank:");
        printAccountStatus(td_bank);
    }

    public static List<String> executeTDBankQueries(QueryHandler handler) {
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

        return handler.handleQueries(queries);
    }

    private static void printAccountStatus(Bank bank) {
        Map<String, Double> accountStatus = bank.getAllAccounts();
        for (Map.Entry<String, Double> entry : accountStatus.entrySet()) {
            System.out.println("Account ID: " + entry.getKey() + ", Balance: " + entry.getValue());
        }
        System.out.println();
    }
}
