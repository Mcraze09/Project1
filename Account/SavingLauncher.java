package Bank;

public class SavingsAccountLauncher {
    public static void main(String[] args) {
        Bank myBank = new Bank("MyBank", 10000, 0.02); // Adjust parameters as per your Bank constructor
        SavingsAccount savingsAccount = new SavingsAccount(myBank, "SA001", 500.0); // Initial balance of 500
        
        System.out.println("Initial Savings Account Balance: " + savingsAccount.getBalance());
        savingsAccount.cashDeposit(200); // Deposit
        System.out.println("After Deposit, Balance: " + savingsAccount.getBalance());
        
        savingsAccount.withdrawal(100); // Withdrawal
        System.out.println("After Withdrawal, Balance: " + savingsAccount.getBalance());
        
        // Display Transactions
        System.out.println(savingsAccount.getTransactionsInfo());
    }
}
