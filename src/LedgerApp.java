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
            case "1": // Month To Date
                LocalDate now = LocalDate.now();
                List<Transaction> mtd = transactions.stream()
                        .filter(t -> t.getDate().getMonth().equals(now.getMonth()) && t.getDate().getYear() == now.getYear())
                        .toList();
                if (mtd.isEmpty()) {
                    System.out.println("🛑 No transactions found for Month To Date.");
                } else {
                    TransactionManager.displayTransactions(mtd);
                    displayStats(mtd);
                }
                break;

            case "2": // Previous Month
                LocalDate lastMonth = LocalDate.now().minusMonths(1);
                List<Transaction> prevMonth = transactions.stream()
                        .filter(t -> t.getDate().getMonth().equals(lastMonth.getMonth()) && t.getDate().getYear() == lastMonth.getYear())
                        .toList();
                if (prevMonth.isEmpty()) {
                    System.out.println("🛑 No transactions found for Previous Month.");
                } else {
                    TransactionManager.displayTransactions(prevMonth);
                    displayStats(prevMonth);
                }
                break;

            case "3": // Year To Date
                int thisYear = LocalDate.now().getYear();
                List<Transaction> ytd = transactions.stream()
                        .filter(t -> t.getDate().getYear() == thisYear)
                        .toList();
                if (ytd.isEmpty()) {
                    System.out.println("🛑 No transactions found for Year To Date.");
                } else {
                    TransactionManager.displayTransactions(ytd);
                    displayStats(ytd);
                }
                break;

            case "4": // Previous Year
                int lastYear = LocalDate.now().getYear() - 1;
                List<Transaction> prevYear = transactions.stream()
                        .filter(t -> t.getDate().getYear() == lastYear)
                        .toList();
                if (prevYear.isEmpty()) {
                    System.out.println("🛑 No transactions found for Previous Year.");
                } else {
                    TransactionManager.displayTransactions(prevYear);
                    displayStats(prevYear);
                }
                break;

            case "5": // Search by Vendor
                System.out.print("🏷️ Vendor: ");
                String vendor = sc.nextLine().toLowerCase();
                List<Transaction> vendorResults = transactions.stream()
                        .filter(t -> t.getVendor().toLowerCase().contains(vendor))
                        .toList();
                if (vendorResults.isEmpty()) {
                    System.out.println("🛑 No transactions found for that vendor.");
                } else {
                    TransactionManager.displayTransactions(vendorResults);
                    displayStats(vendorResults);
                }
                break;

            case "6": // Custom Search
                System.out.println("🔍 Let’s customize your search! You can leave any field blank.");
                System.out.print("📅 Start Date (YYYY-MM-DD): ");
                String startDateStr = sc.nextLine();
                System.out.print("📅 End Date (YYYY-MM-DD): ");
                String endDateStr = sc.nextLine();
                System.out.print("📝 Description: ");
                String desc = sc.nextLine().toLowerCase();
                System.out.print("🏷️ Vendor: ");
                String vend = sc.nextLine().toLowerCase();
                System.out.print("💰 Amount: ");
                String amountStr = sc.nextLine();

                List<Transaction> customResults = new ArrayList<>();
                for (Transaction t : transactions) {
                    boolean match = true;
                    if (!startDateStr.isEmpty() && t.getDate().isBefore(LocalDate.parse(startDateStr))) match = false;
                    if (!endDateStr.isEmpty() && t.getDate().isAfter(LocalDate.parse(endDateStr))) match = false;
                    if (!desc.isEmpty() && !t.getDescription().toLowerCase().contains(desc)) match = false;
                    if (!vend.isEmpty() && !t.getVendor().toLowerCase().contains(vend)) match = false;
                    if (!amountStr.isEmpty()) {
                        try {
                            double amt = Double.parseDouble(amountStr);
                            if (t.getAmount() != amt) match = false;
                        } catch (NumberFormatException e) {
                            System.out.println("❌ Invalid amount entered.");
                            match = false;
                        }
                    }
                    if (match) customResults.add(t);
                }

                if (customResults.isEmpty()) {
                    System.out.println("🛑 No transactions found matching your custom search.");
                } else {
                    TransactionManager.displayTransactions(customResults);
                    displayStats(customResults);
                }
                break;

            case "0": // Back
                return;
        }
    }
}