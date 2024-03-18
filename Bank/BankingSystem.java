import java.util.Scanner;
import Main.Field;
import Main.FieldValidator.*;

// Assuming the package structure is as follows. Adjust if necessary.
import Bank.Account;
import Bank.Bank;
import Bank.SavingsAccount;
import Bank.CreditAccount;
import Bank.IllegalAccountType;

public class BankingSystem {
    private Bank bank;
    private Scanner scanner;

    public BankingSystem(Bank bank) {
        this.bank = bank;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("\nWelcome to " + bank.getName() + " Banking System!");
            System.out.println("1. Login");
            System.out.println("2. Create Account");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    createAccount();
                    break;
                case 3:
                    System.out.println("Thank you for using " + bank.getName() + " Banking System. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void login() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        Account account = bank.findAccountByNumber(accountNumber);

        if (account != null) {
            System.out.println("Logged in successfully.");
            // Optionally, further logic to handle account interactions after login
            accountOperations(account);
        } else {
            System.out.println("Account not found.");
        }
    }

    private void createAccount() {
        System.out.println("\nCreating a new account.");
        System.out.println("Select account type: \n1. Savings Account \n2. Credit Account");

        int accountType = getIntInput("Enter choice (1 or 2): ");

        String accountNumber = getStringInput("Enter a new account number: ");

        switch (accountType) {
            case 1:
                createSavingsAccount(accountNumber);
                break;
            case 2:
                createCreditAccount(accountNumber);
                break;
            default:
                System.out.println("Invalid account type selected.");
        }
    }

    private void createSavingsAccount(String accountNumber) {
        double initialDeposit = getDoubleInput("Enter initial deposit amount: ");
        SavingsAccount newAccount = new SavingsAccount(bank, accountNumber, initialDeposit);
        bank.addAccount(newAccount);
        System.out.println("Savings account created successfully.");
    }

    private void createCreditAccount(String accountNumber) {
        double creditLimit = getDoubleInput("Enter credit limit: ");
        CreditAccount newAccount = new CreditAccount(bank, accountNumber, creditLimit);
        bank.addAccount(newAccount);
        System.out.println("Credit account created successfully.");
    }

    private void accountOperations(Account account) {
        // Example placeholder for operations after login, such as deposit, withdraw, etc.
        // You can extend this to include actual operations based on user input
        System.out.println("Account operations placeholder. Account Number: " + account.getAccountNumber());
        // Implement operations like deposit, withdraw, transfer here
    }

    // Helper methods for input
    private String getStringInput(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    private int getIntInput(String message) {
        System.out.print(message);
        int input = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return input;
    }

    private double getDoubleInput(String message) {
        System.out.print(message);
        double input = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        return input;
    }

    public static void main(String[] args) {
        // Initialization and start the banking system
        Bank myBank = new Bank("MyBank", 50000, 50000, 10);
        BankingSystem system = new BankingSystem(myBank);
        system.start();
    }
}
