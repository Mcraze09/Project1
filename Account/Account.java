package Bank;

import Accounts.*;

import java.util.ArrayList;
import java.util.List;

public abstract class Account {
    protected Bank bank;
    protected String accountNumber;
    protected String ownerfname;    
    protected String ownerlname;
    protected String owneremail; // Owner of the account
    protected int pin; // PIN for the account security
    protected List<Transaction> transactions;

    // Modified Constructor to include owner and pin
    public Account(Bank bank, String accountNumber, String ownerfname, String ownerlname, String owneremail, String pin) {
        this.bank = bank;
        this.accountNumber = accountNumber;
        this.ownerfname = ownerfname;
        this.ownerlname = ownerlname;
        this.owneremail = owneremail;
        this.pin = pin;
        this.transactions = new ArrayList<>();
    }

    public String getfname () {
        return ownerfname;
    }

    public String getlname () {
        return ownerlname;
    }
    
    public String getOwnerFullName(){
        return ownerfname + " " + ownerlname;
    }

    public String getBank() {
        return bank;
    }

    // Add a new transaction to the account's history
    public void addNewTransaction(Transaction transaction) {
        transactions.add(transaction);
        System.out.println("New transaction recorded for account " + accountNumber + ": " + transaction.description);
    }

    // Retrieve a string representation of the account's transactions
    public String getTransactionsInfo() {
        StringBuilder info = new StringBuilder("Transaction history for account " + accountNumber + ":\n");
        for (Transaction transaction : transactions) {
            info.append(transaction.toString()).append("\n");
        }
        return info.toString();
    }

    // Abstract methods for account-specific behaviors which must be implemented in subclasses
    public abstract boolean cashDeposit(double amount);
    public abstract boolean withdrawal(double amount);

    @Override
    public String toString() {
        return "Account{" +
                "bank='" + getBank() + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", owner='" + getOwnerFullName() + '\'' +
                ", pin=" + pin +
                '}';
    }
}
