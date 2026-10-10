import model.Account; import model.SavingsAccount; import exception.BankException; import exception.InsufficientFundsException; import exception.InvalidAmountException;
public class Practical8 {
 static class BankSession implements AutoCloseable { public BankSession(){System.out.println("Bank session started.");} public void close(){System.out.println("Bank session closed.");} }
 public static void main(String[] args) {
  Account account=new SavingsAccount("Mahi",5000,1000);
  try { account.withdraw(6000); } catch(InsufficientFundsException e){System.out.println(e.getMessage()+"; shortfall Rs. "+e.getShortfall());} catch(InvalidAmountException e){System.out.println(e.getMessage());} finally {System.out.println("Withdrawal attempt finished.");}
  try { account.deposit(-500); } catch(InvalidAmountException e){System.out.println("Deposit failed: "+e.getMessage());} finally {System.out.println("Deposit attempt finished.");}
  Account destination=new SavingsAccount("Riya",2000,500); try { account.transfer(destination,1000); System.out.println("Transfer successful."); } catch(BankException e){System.out.println("Transfer failed: "+e.getMessage());}
  try(BankSession session=new BankSession()){System.out.println("Performing banking operations.");}
 }
}
