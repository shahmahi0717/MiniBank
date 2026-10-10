import model.*; import exception.BankException;
public class Practical5 {
 public static void main(String[] args) {
  Account[] accounts={new SavingsAccount("Mahi",5000,1000),new CurrentAccount("Riya",3000,2000),new FixedDepositAccount("Amit",10000)};
  System.out.println("----- Account Interest Rates -----"); for(Account a:accounts) System.out.println(a.getOwnerName()+" - Interest Rate: "+a.interestRate()+"%");
  System.out.println("\n----- Withdrawal Test -----");
  for(Account a:accounts) { try { a.withdraw(1000); System.out.println(a.getOwnerName()+" withdrawal successful."); } catch(BankException e) { System.out.println(a.getOwnerName()+" withdrawal failed: "+e.getMessage()); } }
  System.out.println("\n----- Final Balances -----"); for(Account a:accounts) System.out.println(a);
  for(Account a:accounts) if(a instanceof SavingsAccount s) System.out.println(s.getOwnerName()+" minimum balance: Rs. "+s.getMinBalance());
 }
}
