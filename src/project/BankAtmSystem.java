package project;
import java.text.SimpleDateFormat;
import java.util.*;
class Transaction {
    private String transactionId;
    private String type;
    private double amount;
    private double remainingBalance;
    private String timestamp;
    public Transaction(String transactionId, String type, double amount, double remainingBalance, String timestamp) {
        this.transactionId = transactionId;
        this.type = type;
        this.amount = amount;
        this.remainingBalance = remainingBalance;
        this.timestamp = timestamp;
    }
    
    public String getTransactionId() {
    	return transactionId; }
    public String getType() { 
    	return type; }
    public double getAmount() {
    	return amount; }
    public double getRemainingBalance() { 
    	return remainingBalance; }

    public static void displayHeading() {
        System.out.println("--------------------------------------------------------------------------------------------------");
        System.out.println("Transaction ID \t\t Type \t\t Amount  \t Balance \t Date & Time");
        System.out.println("--------------------------------------------------------------------------------------------------");
    }

    public void display() {
        System.out.println(transactionId + " \t\t " + type + " \t " + amount + " \t\t " + remainingBalance + " \t\t " + timestamp);
    }
}

public class BankAtmSystem {
    private static double balance = 0;
    private static List<Transaction> transactionHistory = new ArrayList<Transaction>();
    private static Stack<Transaction> undoStack = new Stack<Transaction>();
    private static int transactionCounter = 101;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("\t\t-------------------- WELCOME TO AKS BANK --------------------");
        System.out.print("Enter Account Holder Name: ");
        String customerName = scan.nextLine();

        while (true) {
            System.out.println("\n-------------------- Menu --------------------"); 
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            String[] days = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
            System.out.println("Date: " + formatter.format(date) + "  " + days[calendar.get(Calendar.DAY_OF_WEEK) - 1]);
            System.out.println("1. Deposit Money");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Check Balance");
            System.out.println("4. Undo Last Transaction");
            System.out.println("5. Print Bank Statement");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 1:
                    deposit(scan, formatter.format(date));
                    break;
                case 2:
                    withdraw(scan, formatter.format(date));
                    break;
                case 3:
                    System.out.println("Current Balance: " + balance);
                    break;
                case 4:
                    undoLastTransaction();
                    break;
                case 5:
                    printStatement(customerName);
                    break;
                case 6:
                    System.out.println("Exiting. Thank you!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    private static void deposit(Scanner scan, String timestamp) {
        System.out.print("Enter deposit amount: ");
        double amount = scan.nextDouble();
        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }
        balance = balance+amount;
        String txnId = "TRANSACTION" + transactionCounter;
        transactionCounter++;
        Transaction txn = new Transaction(txnId, "DEPOSIT", amount, balance, timestamp);
        transactionHistory.add(txn);
        undoStack.push(txn);
        System.out.println("Successfully deposited Rupees " + amount);
        System.out.println("New Balance: " + balance);
    }

    private static void withdraw(Scanner scan, String timestamp) {
        System.out.print("Enter withdrawal amount: ");
        double amount = scan.nextDouble();
        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }
        if (amount > balance) {
            System.out.println("Insufficient Balance");
            return;
        }
        balance = balance-amount;
        String txnId = "T" + transactionCounter;
        transactionCounter++;
        Transaction txn = new Transaction(txnId, "WITHDRAW", amount, balance, timestamp);
        transactionHistory.add(txn);
        undoStack.push(txn);
        System.out.println("Successfully withdrew Rupees " + amount);
        System.out.println("Remaining Balance: " + balance);
    }

    private static void undoLastTransaction() {
        if (undoStack.isEmpty()) {
            System.out.println("No actions to undo.");
            return;
        }
        Transaction lastTxn = undoStack.pop();
        if (lastTxn.getType().equals("DEPOSIT")) {
            balance = balance - lastTxn.getAmount();
            System.out.println("Undone Deposit: " + lastTxn.getAmount());
            System.out.println("Adjusted Balance: " + balance);
        } else if (lastTxn.getType().equals("WITHDRAW")) {
            balance = balance + lastTxn.getAmount();
            System.out.println("Undone Withdrawal: " + lastTxn.getAmount());
            System.out.println("Adjusted Balance: " + balance);
        }
        transactionHistory.remove(lastTxn);
    }

    private static void printStatement(String customerName) {
        System.out.println("\n\t\t-------------------- Account Statement --------------------");
        System.out.println("Customer Name: " + customerName);
        
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions available.");
            return;
        }
        Transaction.displayHeading();
        for (Transaction txn : transactionHistory) {
            txn.display();
        }
        System.out.println("--------------------------------------------------------------------------------------------------");
        System.out.println("\n\t\t\t\t\t\t\t\t Total Balance: " + balance);
    }
}