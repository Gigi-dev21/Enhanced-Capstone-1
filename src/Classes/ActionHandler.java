package Classes;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;

public class ActionHandler {
    public static void addTransaction(Scanner sc, boolean isDeposit) {
        System.out.println("\nLet's get that transaction recorded 💼");

        System.out.print("📝 Please enter a short description: ");
        String description = sc.nextLine();

        System.out.print("🏷️ Who's the vendor? ");
        String vendor = sc.nextLine();

        System.out.print("💵 How much is it? (e.g. 150.00): ");
        double amount = getValidAmount(sc);

        if (!isDeposit) amount *= -1;
        Transaction t = new Transaction(LocalDate.now(), LocalTime.now(), description, vendor, amount);
        TransactionManager.saveTransaction(t);

        System.out.println("\u001B[32m✅ Thank you! Your transaction has been saved successfully.\u001B[0m");
    }

    public static double getValidAmount(Scanner sc) {
        while (true) {
            String input = sc.nextLine();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.print("\u001B[31m❌ That doesn’t look like a number. Please try again with something like 123.45 🧮\u001B[0m\n💵 Enter amount again: ");
            }
        }
    }
}
