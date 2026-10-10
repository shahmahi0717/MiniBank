import model.Account; import model.SavingsAccount; import util.AnnotationValidator; import java.lang.reflect.Field;
public class Practical7 {
 public static void main(String[] args) throws Exception {
  Account account=new SavingsAccount("Mahi",500,100);
  // Reflection is used only to demonstrate detecting invalid data; normal constructors reject negative balances.
  Field balanceField=Account.class.getDeclaredField("balance"); balanceField.setAccessible(true); balanceField.setLong(account,-500);
  String[] errors=AnnotationValidator.validate(account); System.out.println("----- Validation Results -----");
  if(errors.length==0) System.out.println("No validation errors."); else for(String error:errors) System.out.println(error);
 }
}
