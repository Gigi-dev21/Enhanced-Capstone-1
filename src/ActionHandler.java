import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;

public class ActionHandler {
    public static void addTransaction(Scanner sc, boolean isDeposit) {
        System.out.print("Please enter Description: ");
        String description = sc.nextLine();
        System.out.print("Please enter Vendor: ");
        String vendor = sc.nextLine();
        double amount = getValidAmount(sc);
        if (!isDeposit) amount *= -1;
        Transaction t = new Transaction(LocalDate.now(), LocalTime.now(), description, vendor, amount);
        TransactionManager.saveTransaction(t);
    }

    public static double getValidAmount(Scanner sc) {
        double amount = 0;
        while (true) {
            System.out.print("Amount: ");
            if (sc.hasNextDouble()) {
                amount = sc.nextDouble();
                sc.nextLine();
                break;
            } else {
                System.out.println("❌ Invalid amount. Please enter a number like 150.50.");
                sc.nextLine();
            }
        }
        return amount;
    }

    public static void openLedger(Scanner sc) {
        LedgerApp.showLedger(sc);
    }
}
