package banking.app.java;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for handling and processing queries related to banking operations.
 * This class provides a mechanism to process a list of string-based queries and
 * return the results of each query using the provided Bank instance.
 */
public class QueryHandler {
    private Bank bank;

    /**
     * Constructs a new QueryHandler with the specified Bank instance.
     *
     * @param bank the Bank instance to be used for processing queries
     */
    public QueryHandler(Bank bank) {
        this.bank = bank;
    }

    /**
     * Handles and processes a list of banking-related queries.
     *
     * Supported query operations are:
     * - CREATE_ACCOUNT
     * - DEPOSIT
     * - WITHDRAW
     * - TRANSFER
     * - TOP_SPENDERS
     * - SHOP_CASHBACK
     * - MERGE_ACCOUNTS
     *
     * Each query is an array of strings where the first element is the operation
     * and the subsequent elements are the parameters for the operation.
     *
     * @param queries a list of string arrays representing banking queries
     * @return a list of string results corresponding to each processed query
     */
    public List<String> handleQueries(List<String[]> queries) {
        List<String> results = new ArrayList<>();

        for (String[] query : queries) {
            switch (query[0]) {
                case "CREATE_ACCOUNT":
                    if (query.length < 3) {
                        results.add("Invalid CREATE_ACCOUNT query");
                        break;
                    }
                    results.add(String.valueOf(bank.createAccount(query[2])));
                    break;
                case "DEPOSIT":
                    if (query.length < 4) {
                        results.add("Invalid DEPOSIT query");
                        break;
                    }
                    double depositResult = bank.deposit(query[2], Double.parseDouble(query[3]));
                    results.add(depositResult == -1 ? "Error depositing!" : String.valueOf(depositResult));
                    break;
                case "WITHDRAW":
                    if (query.length < 4) {
                        results.add("Invalid WITHDRAW query");
                        break;
                    }
                    double withdrawResult = bank.withdraw(query[2], Double.parseDouble(query[3]));
                    results.add(withdrawResult == -1 ? "Error withdrawing!" : String.valueOf(withdrawResult));
                    break;
                case "TRANSFER":
                    if (query.length < 5) {
                        results.add("Invalid TRANSFER query");
                        break;
                    }
                    double transferResult = bank.transfer(query[2], query[3], Double.parseDouble(query[4]));
                    results.add(transferResult == -1 ? "Error transferring!" : String.valueOf(transferResult));
                    break;
                case "TOP_SPENDERS":
                    if (query.length < 2) {
                        results.add("Invalid TOP_SPENDERS query");
                        break;
                    }
                    int n = Integer.parseInt(query[1]);
                    List<Account> topAccounts = bank.getTopSpenders(n);
                    StringBuilder sb = new StringBuilder();
                    for (Account acc : topAccounts) {
                        if (sb.length() > 0) { sb.append(", "); }
                        sb.append(acc.getAccountId()).append("(").append(acc.getTotalOutgoing()).append(")");
                    }
                    results.add(sb.toString());
                    break;
                case "SHOP_CASHBACK":
                    if (query.length < 4) {
                        results.add("Invalid CASHBACK query");
                        break;
                    }
                    double cashbackAmount = bank.shopWithCashback(query[1], query[2], Double.parseDouble(query[3]));
                    results.add(String.valueOf(cashbackAmount));
                    break;
                case "MERGE_ACCOUNTS":
                    if (query.length < 3) {
                        results.add("Invalid MERGE_ACCOUNTS query");
                        break;
                    }
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
