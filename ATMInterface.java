import java.util.Scanner;

class BankAccount {
  private double balance;

  public BankAccount(double initialBalance) {
    balance = initialBalance;
  }

  public double getBalance() {
    return balance;
  }

  public void deposit(double amount) {
    balance += amount;
  }

  public boolean withdraw(double amount) {
    if (balance >= amount) {
      balance -= amount;
      return true;
    } else {
      return false;
    }
  }
}

class ATM {
  private BankAccount account;

  public ATM(BankAccount account) {
    this.account = account;
  }

  public void displayMenu() {
    System.out.println("ATM Menu:");
    System.out.println("1. Check Balance");
    System.out.println("2. Deposit");
    System.out.println("3. Withdraw");
    System.out.println("4. Exit");
  }

  public void checkBalance() {
    System.out.println("Your current balance is: $" + account.getBalance());
  }

  public void deposit() {
    System.out.print("Enter amount to deposit: $");
    try (Scanner scanner = new Scanner(System.in)) {
      double amount = scanner.nextDouble();
      account.deposit(amount);
    }
    System.out.println("Deposit successful. Your new balance is: $" + account.getBalance());
  }

  public void withdraw() {
    System.out.print("Enter amount to withdraw: $");
    try (Scanner scanner = new Scanner(System.in)) {
      double amount = scanner.nextDouble();
      if (account.withdraw(amount)) {
        System.out.println("Withdrawal successful. Your new balance is: $" + account.getBalance());
      } else {
        System.out.println("Insufficient balance. Withdrawal failed.");
      }
    }
  }

  public void run() {
    while (true) {
      displayMenu();
      System.out.print("Enter your choice: ");
      try (Scanner scanner = new Scanner(System.in)) {
        int choice = scanner.nextInt();

          switch (choice) {
            case 1:
              checkBalance();
              break;
            case 2:
              deposit();
              break;
            case 3:
              withdraw();
              break;
            case 4:
              System.out.println("Exiting ATM. Goodbye!");
              return;
            default:
              System.out.println("Invalid choice. Please try again.");
          }
    }
    }
  }
}

public class ATMInterface {
  public static void main(String[] args) {
    BankAccount account = new BankAccount(1000.0);
    ATM atm = new ATM(account);
    atm.run();
  }
}