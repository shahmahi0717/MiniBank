import model.Account;
import model.SavingsAccount;
import model.CurrentAccount;
import model.FixedDepositAccount;
import model.Customer;
import service.WithdrawRule;
import util.StatementFormatter;
import util.AnnotationValidator;
import exception.BankException;
import exception.InsufficientFundsException;
import exception.InvalidAmountException;

import java.util.ArrayList;
import java.util.Scanner;

public class MiniBank {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Account> accounts = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println("================================");
        System.out.println("       WELCOME TO MINIBANK      ");
        System.out.println("================================");

        boolean running = true;

        while (running) {
            System.out.println("\n1. Open Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. View All Accounts");
            System.out.println("6. Account Statement");
            System.out.println("7. Validate Account");
            System.out.println("8. Exit");

            System.out.print("Enter your choice: ");

            int choice;

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1:
                    openAccount();
                    break;

                case 2:
                    depositMoney();
                    break;

                case 3:
                    withdrawMoney();
                    break;

                case 4:
                    transferMoney();
                    break;

                case 5:
                    viewAccounts();
                    break;

                case 6:
                    printStatement();
                    break;

                case 7:
                    validateAccount();
                    break;

                case 8:
                    running = false;
                    System.out.println("Thank you for using MiniBank!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }

        sc.close();
    }

    static void openAccount() {
        System.out.print("Enter customer name: ");
        String name = sc.nextLine();

        System.out.println("1. Savings Account");
        System.out.println("2. Current Account");
        System.out.println("3. Fixed Deposit Account");
        System.out.print("Choose account type: ");

        int type;

        try {
            type = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid account type.");
            return;
        }

        long balance;

        try {
            System.out.print("Enter opening balance: ");
            balance = Long.parseLong(sc.nextLine());

            if (balance < 0) {
                System.out.println("Balance cannot be negative.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid amount.");
            return;
        }

        Account account;

        switch (type) {
            case 1:
                System.out.print("Enter minimum balance: ");
                try {
                    long minimum =
                            Long.parseLong(sc.nextLine());

                    if (minimum < 0 || minimum > balance) {
                        System.out.println(
                                "Invalid minimum balance.");
                        return;
                    }

                    account = new SavingsAccount(
                            name, balance, minimum);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid amount.");
                    return;
                }
                break;

            case 2:
                System.out.print("Enter overdraft limit: ");
                try {
                    long limit = Long.parseLong(sc.nextLine());

                    if (limit < 0) {
                        System.out.println(
                                "Limit cannot be negative.");
                        return;
                    }

                    account = new CurrentAccount(
                            name, balance, limit);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid amount.");
                    return;
                }
                break;

            case 3:
                account = new FixedDepositAccount(name, balance);
                break;

            default:
                System.out.println("Invalid account type.");
                return;
        }

        accounts.add(account);

        System.out.println("Account created successfully!");
        System.out.println(account);
    }

    static Account findAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber()
                    .equals(accountNumber)) {
                return account;
            }
        }

        return null;
    }

    static void depositMoney() {
        Account account = getAccount();

        if (account == null) {
            return;
        }

        try {
            System.out.print("Enter deposit amount: ");
            long amount = Long.parseLong(sc.nextLine());

            account.deposit(amount);
            System.out.println("Current balance: Rs. "
                    + account.getBalance());

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid amount.");
        } catch (InvalidAmountException e) {
            System.out.println("Deposit failed: "
                    + e.getMessage());
        }
    }

    static void withdrawMoney() {
        Account account = getAccount();

        if (account == null) {
            return;
        }

        try {
            System.out.print("Enter withdrawal amount: ");
            long amount = Long.parseLong(sc.nextLine());

            account.withdraw(amount);

            System.out.println("Current balance: Rs. "
                    + account.getBalance());

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid amount.");
        } catch (InsufficientFundsException e) {
            System.out.println("Withdrawal failed: "
                    + e.getMessage());
            System.out.println("Shortfall: Rs. "
                    + e.getShortfall());
        } catch (InvalidAmountException e) {
            System.out.println("Withdrawal failed: "
                    + e.getMessage());
        }
    }

    static void transferMoney() {
        System.out.println("Enter source account:");
        Account source = getAccount();

        if (source == null) {
            return;
        }

        System.out.println("Enter destination account:");
        Account destination = getAccount();

        if (destination == null) {
            return;
        }

        try {
            System.out.print("Enter transfer amount: ");
            long amount = Long.parseLong(sc.nextLine());

            source.transfer(destination, amount);

            System.out.println("Transfer completed.");
            System.out.println("Source balance: Rs. "
                    + source.getBalance());
            System.out.println("Destination balance: Rs. "
                    + destination.getBalance());

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid amount.");
        } catch (BankException e) {
            System.out.println("Transfer failed: "
                    + e.getMessage());
        }
    }

    static Account getAccount() {
        System.out.print("Enter account number: ");
        String number = sc.nextLine();

        Account account = findAccount(number);

        if (account == null) {
            System.out.println("Account not found.");
        }

        return account;
    }

    static void viewAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts created yet.");
            return;
        }

        System.out.println("----- All Accounts -----");

        for (Account account : accounts) {
            System.out.println(account);
            System.out.println("Interest rate: "
                    + account.interestRate() + "%");
        }
    }

    static void printStatement() {
        Account account = getAccount();

        if (account != null) {
            System.out.println(
                    StatementFormatter.buildStatement(account));
        }
    }

    static void validateAccount() {
        Account account = getAccount();

        if (account == null) {
            return;
        }

        String[] errors = AnnotationValidator.validate(account);

        if (errors.length == 0) {
            System.out.println("No annotation validation errors.");
        } else {
            System.out.println("Validation errors:");

            for (String error : errors) {
                System.out.println("- " + error);
            }
        }
    }
}
