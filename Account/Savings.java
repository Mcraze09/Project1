package Bank;

import Accounts.*;

public class SavingsAccount extends Account implements Deposit, Withdrawal, FundTransfer {
    private double balance;

    public SavingsAccount(Bank bank, String accountNumber, double initialBalance) {
        super(bank, accountNumber);
        this.balance = initialBalance;
    }

    @Override
    public boolean cashDeposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            return false;
        }
        if (amount > bank.getDepositLimit()) {
            System.out.println("Deposit amount exceeds the bank's deposit limit.");
            return false;
        }
        balance += amount;
        // Recording the transaction
        Transaction depositTransaction = new Transaction(accountNumber, Transaction.Transactions.Deposit, "Deposited: " + amount);
        addNewTransaction(depositTransaction);
        System.out.println("Deposit successful. Current balance: " + balance);
        return true;
    }

    @Override
    public boolean withdrawal(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return false;
        }
        if (amount > balance) {
            System.out.println("Insufficient balance for the withdrawal.");
            return false;
        }
        balance -= amount;
        // Recording the transaction
        Transaction withdrawalTransaction = new Transaction(accountNumber, Transaction.Transactions.Withdraw, "Withdrawn: " + amount);
        addNewTransaction(withdrawalTransaction);
        System.out.println("Withdrawal successful. Current balance: " + balance);
        return true;
    }

    @Override
    public boolean transfer(Account account, double amount) throws IllegalAccountType {
        if (amount <= 0) {
            System.out.println("Transfer amount must be positive.");
            return false;
        }
        if (amount > balance) {
            System.out.println("Insufficient balance for the transfer.");
            return false;
        }
        if (account instanceof CreditAccount) {
            throw new IllegalAccountType("Cannot transfer funds to a credit account.");
        }
        balance -= amount;
        account.cashDeposit(amount);
        // Recording the transaction
        Transaction internalTransferTransaction = new Transaction(accountNumber, Transaction.Transactions.FundTransfer, "Transferred: " + amount + " to " + account.getAccountNumber());
        addNewTransaction(internalTransferTransaction);
        System.out.println("Transfer successful. Current balance: " + balance);
        return true;
    }

    @Override
    public boolean transfer(Bank otherBank, Account account, double amount) throws IllegalAccountType {
        double totalAmount = amount + bank.getProcessingFee();
        if (totalAmount > balance) {
            System.out.println("Insufficient balance for the transfer, including processing fee.");
            return false;
        }
        if (account instanceof CreditAccount) {
            throw new IllegalAccountType("Cannot transfer funds to a credit account.");
        }
        balance -= totalAmount;
        account.cashDeposit(amount);
        // Recording the transaction
        Transaction externalTransferTransaction = new Transaction(accountNumber, Transaction.Transactions.FundTransfer, "Transferred: " + amount + " with fee " + bank.getProcessingFee() + " to " + account.getAccountNumber() + " at " + otherBank.getName());
        addNewTransaction(externalTransferTransaction);
        System.out.println("Transfer to another bank successful. Current balance: " + balance);
        return true;
    }

    // Getters and setters
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        if (balance >= 0) {
            this.balance = balance;
        } else {
            System.out.println("Balance cannot be negative.");
        }
    }

    @Override
    public String toString() {
        return "SavingsAccount{" +
                "bank='" + bank.getName() + '\'' + // Assuming there is a getName() method in Bank class
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                '}';
    }
}
