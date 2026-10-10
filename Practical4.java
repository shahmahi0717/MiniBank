import model.Account; import model.SavingsAccount; import service.Command; import service.CommandParser; import util.Validator; import util.StatementFormatter;
public class Practical4 {
 public static void main(String[] args) {
  System.out.println("Mobile valid: "+Validator.isValidMobile("9876543210")); System.out.println("Email valid: "+Validator.isValidEmail("mahi@gmail.com")); System.out.println("PAN valid: "+Validator.isValidPan("ABCDE1234F")); System.out.println("IFSC valid: "+Validator.isValidIfsc("SBIN0001234"));
  try { Command cmd=CommandParser.parse("DEPOSIT AC0001 500"); System.out.println("Type: "+cmd.type()+", Account: "+cmd.accountNumber()+", Amount: "+cmd.amount()); } catch(IllegalArgumentException e) { System.out.println("Invalid command: "+e.getMessage()); }
  Account account=new SavingsAccount("Mahi",5000,1000); System.out.println(StatementFormatter.buildStatement(account));
 }
}
