import java.util.Scanner;
record BankInfo(String name, String branch) {}
enum MenuOption {
    OPEN_ACCOUNT, DEPOSIT, WITHDRAW, TRANSFER, EXIT
}
public class Practical1 {
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        BankInfo bank = new BankInfo("MiniBank", "Nadiad Branch");
        System.out.println("Welcome to " + bank.name());
        System.out.println("Branch: " + bank.branch());
        int choice;
        do {
            System.out.println("\n----- MiniBank Menu -----");
            System.out.println("1. Open Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> System.out.println("Open Account - to be implemented in a later lab");
                case 2 -> System.out.println("Deposit - to be implemented in a later lab");
                case 3 -> System.out.println("Withdraw - to be implemented in a later lab");
                case 4 -> System.out.println("Transfer - to be implemented in a later lab");
                case 5 -> System.out.println("Thank you for using MiniBank. Goodbye!");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
        sc.close();
    }
}