import model.Account; import model.Customer; import model.SavingsAccount; import exception.BankException;
public class Practical2 {
 public static void main(String[] args) {
  Customer c1 = new Customer("Mahi", "mahi@gmail.com", "9876543210");
  System.out.println("Customer ID: " + c1.getCustomerId()); System.out.println("Customer Name: " + c1.getName());
  Account[] accounts = {new SavingsAccount("Mahi",5000,1000), new SavingsAccount("Riya",3000,500), new SavingsAccount("Amit",1000,0)};
  try { accounts[0].deposit(1000); accounts[1].deposit(500); accounts[0].withdraw(2000); System.out.println("First withdrawal successful."); accounts[2].withdraw(500); }
  catch (BankException e) { System.out.println("Transaction failed: " + e.getMessage()); }
  System.out.println("\n----- Account Details -----"); for (Account a : accounts) System.out.println(a + "\n");
 }
}
