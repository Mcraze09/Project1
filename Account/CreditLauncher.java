package Bank;

public class CreditAccountLauncher {
    public static void main(String[] args) {
        Bank myBank = new Bank("MyBank", 10000, 0.02); // Assuming Bank's constructor is compatible
        CreditAccount creditAccount = new CreditAccount(myBank, "CA001", 1000.0); // Credit limit of 1000
        
        System.out.println("Initial Loan Amount: " + creditAccount.getLoanAmount());

        // Borrowing against the credit (Recompense)
        creditAccount.recompense(200);
        System.out.println("After Borrowing $200, Loan Amount: " + creditAccount.getLoanAmount());

        // Making a payment to reduce the loan
        creditAccount.cashDeposit(100); // Assuming cashDeposit acts as paying off the loan in this context
        System.out.println("After Paying off $100, Loan Amount: " + creditAccount.getLoanAmount());

        // Trying to pay more than the loan amount
        creditAccount.cashDeposit(150);
        System.out.println("After Attempting to Pay off $150, Loan Amount: " + creditAccount.getLoanAmount());

        // Display Transactions
        System.out.println(creditAccount.getTransactionsInfo());
    }
}
