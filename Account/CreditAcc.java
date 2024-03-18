package Bank;

import Accounts.*;

public class CreditAccount extends Account implements Payment, Recompense {
    private double creditLimit;
    private double loanAmount;

    public CreditAccount(Bank bank, String accountNumber, double creditLimit) {
        super(bank, accountNumber);
        this.creditLimit = creditLimit;
        this.loanAmount = 0.0; // Assume loan starts at 0, indicating no money is owed initially
    }

    @Override
    public boolean pay(Account account, double amount) throws IllegalAccountType {
        if (account instanceof CreditAccount) {
            throw new IllegalAccountType("Cannot make payments to another credit account.");
        }
        if (amount <= 0) {
            System.out.println("Payment amount must be positive.");
            return false;
        }
        if (loanAmount < amount) {
            System.out.println("Payment amount exceeds the loan amount.");
            return false;
        }
        loanAmount -= amount; // Payment should reduce the loan amount
        Transaction transaction = new Transaction(accountNumber, Transaction.Transactions.Payment, "Paid " + amount + " to account " + account.getAccountNumber());
        addNewTransaction(transaction);
        System.out.println("Payment successful. Remaining loan amount: " + loanAmount);
        return true;
    }

    @Override
    public boolean recompense(double amount) {
        if (amount <= 0) {
            System.out.println("Recompense amount must be positive.");
            return false;
        }
        if (loanAmount < amount) {
            System.out.println("Recompense amount exceeds the loan amount.");
            return false;
        }
        loanAmount -= amount; // Recompense should reduce the loan amount
        Transaction transaction = new Transaction(accountNumber, Transaction.Transactions.Recompense, "Loan decreased by: " + amount);
        addNewTransaction(transaction);
        System.out.println("Recompense successful. Remaining loan amount: " + loanAmount);
        return true;
    }

    // Getters and Setters for creditLimit and loanAmount
    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        if (loanAmount <= creditLimit) {
            this.loanAmount = loanAmount;
        } else {
            System.out.println("Specified loan amount exceeds credit limit.");
        }
    }
}
    