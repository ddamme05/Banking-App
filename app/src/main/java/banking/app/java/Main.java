package banking.app.java;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final InputStream input = System.in;
    private static final Scanner scanner = new Scanner(input);
    private static final Bank bank = new Bank();
    private static final QueryHandler queryHandler = new QueryHandler(bank);
    private static int queryId = 1;

    public static void main(String[] args) {
        while (true) {
            displayMenu();

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    transfer();
                    break;
                case 5:
                    getTopSpenders();
                    break;
                case 6:
                    shopWithCashback();
                    break;
                case 7:
                    mergeAccounts();
                    break;
                case 8:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("Banking System:");
        System.out.println("1. Create Account");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Transfer");
        System.out.println("5. Get Top Spenders");
        System.out.println("6. Shop with Cashback");
        System.out.println("7. Merge Accounts");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void createAccount() {
        System.out.print("Enter account name: ");
        String accountId = scanner.nextLine();

        List<String[]> queries = new ArrayList<>();
        queries.add(new String[]{"CREATE_ACCOUNT", String.valueOf(queryId++), accountId});
        List<String> results = queryHandler.handleQueries(queries);

        System.out.println("true".equals(results.get(0)) ? "Account created successfully." : "Error creating account.");
    }

    private static void deposit() {
        System.out.print("Enter account name: ");
        String accountId = scanner.nextLine();
        System.out.print("Enter amount to deposit: ");
        String amount = scanner.nextLine();

        List<String[]> queries = new ArrayList<>();
        queries.add(new String[]{"DEPOSIT", String.valueOf(queryId++), accountId, amount});
        List<String> results = queryHandler.handleQueries(queries);

        if ("Error depositing!".equals(results.get(0))) {
            System.out.println("Error depositing amount.");
        } else {
            System.out.println("Deposited successfully. New balance: " + results.get(0));
        }
    }

    private static void withdraw() {
        System.out.print("Enter account name: ");
        String accountId = scanner.nextLine();
        System.out.print("Enter amount to withdraw: ");
        String amount = scanner.nextLine();

        List<String[]> queries = new ArrayList<>();
        queries.add(new String[]{"WITHDRAW", String.valueOf(queryId++), accountId, amount});
        List<String> results = queryHandler.handleQueries(queries);

        if ("Error withdrawing!".equals(results.get(0))) {
            System.out.println("Error withdrawing amount.");
        } else {
            System.out.println("Withdrawn successfully. New balance: " + results.get(0));
        }
    }

    private static void transfer() {
        System.out.print("Enter source account name: ");
        String sourceId = scanner.nextLine();
        System.out.print("Enter destination account name: ");
        String destId = scanner.nextLine();
        System.out.print("Enter amount to transfer: ");
        String amount = scanner.nextLine();

        List<String[]> queries = new ArrayList<>();
        queries.add(new String[]{"TRANSFER", String.valueOf(queryId++), sourceId, destId, amount});
        List<String> results = queryHandler.handleQueries(queries);

        if ("Error transferring!".equals(results.get(0))) {
            System.out.println("Error transferring amount.");
        } else {
            System.out.println("Transferred successfully. New balance of source: " + results.get(0));
        }
    }

    private static void getTopSpenders() {
        System.out.print("Enter number of top spenders you want: ");
        String n = scanner.nextLine();

        List<String[]> queries = new ArrayList<>();
        queries.add(new String[]{"TOP_SPENDERS", n});
        List<String> results = queryHandler.handleQueries(queries);

        System.out.println("Top spenders: " + results.get(0));
    }

    private static void shopWithCashback() {
        System.out.print("Enter account name: ");
        String accountId = scanner.nextLine();
        System.out.print("Enter shop name: ");
        String shopName = scanner.nextLine();
        System.out.print("Enter amount to shop with: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        List<String[]> queries = new ArrayList<>();
        queries.add(new String[]{"SHOP_CASHBACK", accountId, shopName, String.valueOf(amount)});
        List<String> results = queryHandler.handleQueries(queries);

        if ("Error shopping with cashback!".equals(results.get(0))) {
            System.out.println("Error shopping.");
        } else {
            System.out.println("Shopped successfully with cashback. Amount after cashback: " + results.get(0));
        }
    }



    private static void mergeAccounts() {
        System.out.print("Enter the first account name to merge: ");
        String firstAccount = scanner.nextLine();
        System.out.print("Enter the second account name to merge into: ");
        String secondAccount = scanner.nextLine();

        List<String[]> queries = new ArrayList<>();
        queries.add(new String[]{"MERGE_ACCOUNTS", firstAccount, secondAccount});
        List<String> results = queryHandler.handleQueries(queries);

        System.out.println(results.get(0));
    }
}
