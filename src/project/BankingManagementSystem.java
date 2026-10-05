package project;
import java.util.*;
import java.util.Scanner;
class BankAccount {
    String name;
    int accountNumber;
    double balance = 0;
    Scanner sc = new Scanner(System.in);
    void createAccount() {
        System.out.print("Enter Account Holder Name: ");
        name = sc.nextLine();
        System.out.println("Enter your account number: ");
        accountNumber=sc.nextInt();
        System.out.println("Your account number is: "+accountNumber);
        System.out.println("Account Created Successfully!");
    }
    void deposit() {
        if (accountNumber == 0) {
            System.out.println("INVALID ACC NO.");
            return;
        }
        System.out.print("Enter Deposit Amount: ");
        double amount = sc.nextDouble();
        balance += amount;
        System.out.println("Deposited Successfully!");
    }
    void withdraw() {
    	if (accountNumber == 0) {
            System.out.println("Invalid account no.");
            return;
        }
        System.out.print("Enter withdrawl Amount: ");
        double amount = sc.nextDouble();
        if(balance>=amount) {
        balance -= amount;
       System.out.println("Withdraw successful!");
   }
      else {
      	System.out.println("Insufficient balance");
        }
    }
    void display() {
        if (accountNumber == 0) {
            System.out.println("Account not found");
            return;
        }
        System.out.println("\n----- Account Details -----");
        System.out.println("Name           : "+name);
        System.out.println("Account Number : "+accountNumber);
        System.out.println("Balance        : "+balance);
    }
    void otp() {
    	int num=(int)
    	(Math.random()*10000)+1000;
    	System.out.println(num+" is your account verification OTP ");
    	System.out.println("Enter the OTP: ");
        int otp=sc.nextInt();
        if(num==otp) {
        	System.out.println("OTP verified!");
        }
        else {
        	System.out.println("Invalid OTP");
        }
    }
    
}
public class BankingManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankAccount account = new BankAccount();
        int choice;
        do {
            System.out.println("\n===== BANK MENU =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit money");
            System.out.println("3. Withdraw money");
            System.out.println("4. Display Account");
            System.out.println("5. Request OTP");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                 account.createAccount();
                    break;
                case 2:
                 account.deposit();
                    break;
                case 3:
                account.withdraw();
                break;
                case 4:
                account.display();
                break;
                case 5:
                    account.otp();
                       break;
                case 6:
                 System.out.println("Thank you!");
                    break;
                    
                default:
                    System.out.println("Invalid Choice!");
          }
   } while (choice != 4);
 }
}
