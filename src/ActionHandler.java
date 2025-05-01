// ActionHandler.java

import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;

public class ActionHandler {
    public static void addTransaction(Scanner sc, boolean isDeposit) {
        System.out.print("Description: ");
        String description = sc.nextLine();

        System.out.print("Vendor: ");
        String vendor = sc.nextLine();

        double amount = 0;
        boolean validAmount = false;
        while (!validAmount) {
            System.out.print("Amount: ");
            String input = sc.nextLine();
            try {
                amount = Double.parseDouble(input);
                validAmount = true;
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid amount. Please enter a valid number (e.g., 125.50). Try again.");
            }
        }

        if (!isDeposit) amount *= -1;
        Transaction t = new Transaction(LocalDate.now(), LocalTime.now(), description, vendor, amount);
        TransactionManager.saveTransaction(t);
    }

    public static void openLedger(Scanner sc) {
        LedgerApp.showLedger(sc);
    }
}
