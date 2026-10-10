import model.*; import service.WithdrawRule; import util.StatementFormatter; import exception.BankException; import static java.lang.System.out;
public class Practical6 {
 public static void main(String[] args) {
  Account savings=new SavingsAccount("Mahi",10000,2000), current=new CurrentAccount("Rahul",5000,2000), fixed=new FixedDepositAccount("Priya",20000); Account[] accounts={savings,current,fixed};
  out.println("===== ACCOUNT DETAILS ====="); for(Account a:accounts) { out.println(a); out.println("Interest Rate: "+a.interestRate()+"%"); out.println("Yearly Interest: "+a.yearlyInterest()); }
  out.println("===== DEPOSIT AND WITHDRAW ====="); try { savings.deposit(1000); savings.withdraw(2000); out.println("Savings Balance: "+savings.getBalance()); fixed.withdraw(1000); } catch(BankException e) { out.println(e.getMessage()); }
  out.println("===== MARKER INTERFACE ====="); out.println("Savings account is Premium: "+(savings instanceof Premium));
  WithdrawRule rule1=new WithdrawRule(){ public boolean allow(Account a,long amount){return amount>0 && a.canWithdraw(amount);} }; out.println("Anonymous class allows withdrawal: "+rule1.allow(savings,1000));
  WithdrawRule rule2=(a,amount)->amount>0 && a.canWithdraw(amount); out.println("Lambda allows withdrawal: "+rule2.allow(current,1000));
  out.println(StatementFormatter.buildStatement(savings));
 }
}
