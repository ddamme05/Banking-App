public class Transaction {
    //Using enum for readability and security
    public enum Type {DEPOSIT, WITHDRAW, CASHBACK}
    private double amount;
    private Type type;

    public Transaction(double amount, Type type) {
        this.amount = amount;
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public Type getType() {
        return type;
    }
}
