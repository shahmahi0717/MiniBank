import model.Account; import model.Customer; import model.SavingsAccount;
public class Practical3 {
 public static void main(String[] args) {
  Account a1=new SavingsAccount("Mahi",5000,1000), a2=new SavingsAccount("Riya",3000,500);
  System.out.println("----- Account Details -----\n"+a1+"\n"+a2);
  System.out.println("Are accounts equal? "+a1.equals(a2)); System.out.println("Hash code of a1: "+a1.hashCode()); System.out.println("Hash code of a2: "+a2.hashCode());
  Customer.Address address=new Customer.Address("Main Road","Nadiad","387001");
  Customer c1=new Customer("Mahi","mahi@gmail.com","9876543210",address);
  System.out.println("\n"+c1); Customer c2=c1.clone(); System.out.println("Cloned Customer: "+c2); System.out.println("Same object? "+(c1==c2)); System.out.println("Same customer ID? "+c1.getCustomerId().equals(c2.getCustomerId()));
  Object obj=a1; if(obj instanceof Account) System.out.println("Object is an Account."); if(c1 instanceof Customer) System.out.println("Object is a Customer.");
 }
}
