import java.util.*;
import java.time.LocalDate;

public class LedgerApp {
    public static void showLedger(Scanner sc) {
        List<Transaction> transactions = TransactionManager.loadTransactions();
        while (true) {
            Menu.showLedgerMenu();
            String choice = sc.nextLine().toUpperCase();

            switch (choice) {
                case "A":
                    TransactionManager.displayTransactions(transactions);
                    displayStats(transactions);
                    break;
                case "D":
                    var deposits = TransactionManager.filterByAmount(transactions, true);
                    TransactionManager.displayTransactions(deposits);
                    displayStats(deposits);
                    break;
                case "P":
                    var payments = TransactionManager.filterByAmount(transactions, false);
                    TransactionManager.displayTransactions(payments);
                    displayStats(payments);
                    break;
                case "R":
                    runReports(sc, transactions);
                    break;
                case "H":
                    return;
            }
        }
    }

    private static void displayStats(List<Transaction> transactions) {
        double income = 0, expenses = 0;
        for (Transaction t : transactions) {
            if (t.getAmount() > 0) income += t.getAmount();
            else expenses += t.getAmount();
        }
        double net = income + expenses;
        System.out.println("\n📈 \u001B[32mTotal Income:\u001B[0m $" + income);
        System.out.println("📉 \u001B[31mTotal Expenses:\u001B[0m $" + Math.abs(expenses));
        System.out.println("💼 \u001B[36mNet Balance:\u001B[0m $" + net);
    }

    public static void runReports(Scanner sc, List<Transaction> transactions) {
        Menu.showReportMenu();
        String choice = sc.nextLine();

        switch (choice) {
            case "5":
                System.out.print("🏷️ Vendor: ");
                String vendor = sc.nextLine();
                var vendorFiltered = TransactionManager.filterByVendor(transactions, vendor);
                System.out.println("\u001B[34m✨ Here are the results that match your filters:\u001B[0m");
                TransactionManager.displayTransactions(vendorFiltered);
                break;
        }
    }
}
