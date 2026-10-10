package model;
public class SavingsAccount extends Account implements Premium {
    private final long minBalance;
    public SavingsAccount(String ownerName, long openingBalance, long minBalance) {
        super(ownerName, openingBalance);
        if (minBalance < 0 || minBalance > openingBalance) throw new IllegalArgumentException("Minimum balance must be between zero and opening balance.");
        this.minBalance = minBalance;
    }
    public long getMinBalance() { return minBalance; }
    @Override public double interestRate() { return 4.0; }
    @Override public boolean canWithdraw(long amount) { return amount > 0 && getBalance() - amount >= minBalance; }
}
