package banking.app.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Bank capital_one = new Bank();
        QueryHandler capitalOneHandler = new QueryHandler(capital_one);

        List<String[]> capitalOneQueries = new ArrayList<>();
        capitalOneQueries.add(new String[]{"CREATE_ACCOUNT", "1", "account3"});
        capitalOneQueries.add(new String[]{"CREATE_ACCOUNT", "2", "account2"});
        capitalOneQueries.add(new String[]{"CREATE_ACCOUNT", "3", "account1"});
        capitalOneQueries.add(new String[]{"DEPOSIT", "4", "account1", "2000"});
        capitalOneQueries.add(new String[]{"DEPOSIT", "5", "account2", "3000"});
        capitalOneQueries.add(new String[]{"DEPOSIT", "6", "account3", "4000"});
        capitalOneQueries.add(new String[]{"TOP_SPENDERS", "4"});
        capitalOneQueries.add(new String[]{"TRANSFER", "7", "account3", "account2", "500"});
        capitalOneQueries.add(new String[]{"TRANSFER", "8", "account3", "account1", "1000"});
        capitalOneQueries.add(new String[]{"TRANSFER", "9", "account1", "account2", "2500"});
        capitalOneQueries.add(new String[]{"TOP_SPENDERS", "3"});
        capitalOneQueries.add(new String[]{"TRANSFER", "10", "account1", "account2", "500"});
        capitalOneQueries.add(new String[]{"CASHBACK", "account1"});
        capitalOneQueries.add(new String[]{"MERGE_ACCOUNTS", "account1", "account2"});


        List<String> capitalOneResults = capitalOneHandler.handleQueries(capitalOneQueries);

        for (String result : capitalOneResults) {
            System.out.println(result);
        }

        System.out.println("\nStatus of all accounts at Capital One:");
        printAccountStatus(capital_one);

        Bank td_bank = new Bank();
        QueryHandler tdBankHandler = new QueryHandler(td_bank);

        List<String[]> tdBankQueries = new ArrayList<>();
        tdBankQueries.add(new String[]{"CREATE_ACCOUNT", "1", "account3"});
        tdBankQueries.add(new String[]{"CREATE_ACCOUNT", "2", "account2"});
        tdBankQueries.add(new String[]{"CREATE_ACCOUNT", "3", "account1"});
        tdBankQueries.add(new String[]{"DEPOSIT", "4", "account1", "2000"});
        tdBankQueries.add(new String[]{"DEPOSIT", "5", "account2", "3000"});
        tdBankQueries.add(new String[]{"DEPOSIT", "6", "account3", "4000"});
        tdBankQueries.add(new String[]{"TRANSFER", "8", "account3", "account2", "500"});
        tdBankQueries.add(new String[]{"TRANSFER", "9", "account3", "account1", "1000"});
        tdBankQueries.add(new String[]{"TRANSFER", "10", "account1", "account2", "2500"});


        List<String> tdBankResults = tdBankHandler.handleQueries(tdBankQueries);

        for (String result : tdBankResults) {
            System.out.println(result);
        }

        System.out.println("\nStatus of all accounts at TD Bank:");
        printAccountStatus(td_bank);
    }

    private static void printAccountStatus(Bank bank) {
        Map<String, Double> accountStatus = bank.getAllAccounts();
        for (Map.Entry<String, Double> entry : accountStatus.entrySet()) {
            System.out.println("Account ID: " + entry.getKey() + ", Balance: " + entry.getValue());
        }
        System.out.println();
    }
}
