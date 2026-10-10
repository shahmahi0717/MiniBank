package model;

import model.annotation.Id;
import model.annotation.Positive;
import model.annotation.MaxLength;
import exception.BankException;
import exception.AccountNotFoundException;
import exception.InsufficientFundsException;
import exception.InvalidAmountException;
import java.util.Objects;

public abstract class Account implements Transactable, InterestBearing {
    private static int counter = 1000;
    @Id private final String accountNumber;
    @MaxLength(30) private final String ownerName;
    @Positive private long balance;
    private boolean active = true;

    public Account(String ownerName, long openingBalance) {
        if (ownerName == null || ownerName.isBlank() || ownerName.length() > 30)
            throw new IllegalArgumentException("Owner name must contain 1 to 30 characters.");
        if (openingBalance < 0) throw new IllegalArgumentException("Balance cannot be negative.");
        this.accountNumber = String.format("AC%04d", ++counter);
        this.ownerName = ownerName;
        this.balance = openingBalance;
    }
    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName() { return ownerName; }
    @Override public long getBalance() { return balance; }
    public boolean isActive() { return active; }
    public void closeAccount() { active = false; }

    @Override public void deposit(long amount) throws InvalidAmountException {
        if (amount <= 0) throw new InvalidAmountException("Deposit amount must be greater than zero.");
        if (!active) throw new InvalidAmountException("Cannot deposit into an inactive account.");
        balance = Math.addExact(balance, amount);
    }
    @Override public boolean withdraw(long amount) throws InsufficientFundsException, InvalidAmountException {
        if (amount <= 0) throw new InvalidAmountException("Withdrawal amount must be greater than zero.");
        if (!active) throw new InvalidAmountException("Cannot withdraw from an inactive account.");
        if (!canWithdraw(amount)) throw new InsufficientFundsException(Math.max(0, amount - balance));
        balance -= amount;
        return true;
    }
    public void transfer(Account to, long amount) throws BankException {
        try {
            if (to == null) {
                throw new AccountNotFoundException("Destination account was not found.");
            }
            withdraw(amount);
            to.deposit(amount);
            System.out.println("Transfer successful.");
        } finally {
            System.out.println("Transfer operation completed.");
        }
    }

    public abstract boolean canWithdraw(long amount);
    @Override public abstract double interestRate();
    @Override public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Account account)) return false;
        return accountNumber.equals(account.accountNumber);
    }
    @Override public int hashCode() { return Objects.hash(accountNumber); }
    @Override public String toString() {
        return "Account Number: " + accountNumber + ", Owner: " + ownerName + ", Balance: Rs. " + balance + ", Active: " + active;
    }
}
