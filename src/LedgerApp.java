// LedgerApp.java
import java.time.LocalDate;
import java.util.*;

public class LedgerApp {
    public static void showLedger(Scanner sc) {
        List<Transaction> transactions = TransactionManager.loadTransactions();
        while (true) {
            Menu.showLedgerMenu();
            String choice = sc.nextLine().toUpperCase();
            switch (choice) {
                case "A": display(transactions); break;
                case "D": display(TransactionManager.filterByAmount(transactions, true)); break;
                case "P": display(TransactionManager.filterByAmount(transactions, false)); break;
                case "R": runReports(sc, transactions); break;
                case "H": return;
                default: System.out.println("❌ Invalid option. Please choose from the menu.");
            }
        }
    }

    public static void runReports(Scanner sc, List<Transaction> transactions) {
        while (true) {
            Menu.showReportMenu();
            String choice = sc.nextLine();
            LocalDate now = LocalDate.now();
            switch (choice) {
                case "1": display(TransactionManager.filterByDateRange(transactions, now.withDayOfMonth(1), now)); break;
                case "2": LocalDate startLastMonth = now.minusMonths(1).withDayOfMonth(1);
                    LocalDate endLastMonth = startLastMonth.withDayOfMonth(startLastMonth.lengthOfMonth());
                    display(TransactionManager.filterByDateRange(transactions, startLastMonth, endLastMonth)); break;
                case "3": display(TransactionManager.filterByDateRange(transactions, now.withDayOfYear(1), now)); break;
                case "4": LocalDate startLastYear = now.minusYears(1).withDayOfYear(1);
                    LocalDate endLastYear = startLastYear.withDayOfYear(startLastYear.lengthOfYear());
                    display(TransactionManager.filterByDateRange(transactions, startLastYear, endLastYear)); break;
                case "5": System.out.print("Vendor: ");
                    String vendor = sc.nextLine();
                    display(TransactionManager.filterByVendor(transactions, vendor)); break;
                case "6": runCustomSearch(sc, transactions); break;
                case "0": return;
                default: System.out.println("❌ Invalid report option. Please choose a valid number.");
            }
        }
    }

    public static void runCustomSearch(Scanner sc, List<Transaction> transactions) {
        System.out.print("Start Date (YYYY-MM-DD): ");
        String startDateStr = sc.nextLine();
        System.out.print("End Date (YYYY-MM-DD): ");
        String endDateStr = sc.nextLine();
        System.out.print("Description: ");
        String desc = sc.nextLine().toLowerCase();
        System.out.print("Vendor: ");
        String vendor = sc.nextLine().toLowerCase();
        System.out.print("Amount: ");
        String amountStr = sc.nextLine();

        LocalDate start, end;
        try {
            start = startDateStr.isEmpty() ? LocalDate.MIN : LocalDate.parse(startDateStr);
            end = endDateStr.isEmpty() ? LocalDate.MAX : LocalDate.parse(endDateStr);
        } catch (Exception e) {
            System.out.println("❌ Invalid date format. Use YYYY-MM-DD.");
            return;
        }

        Double amountFilter = null;
        if (!amountStr.isEmpty()) {
            try {
                amountFilter = Double.parseDouble(amountStr);
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid amount. Please enter a valid number.");
                return;
            }
        }

        List<Transaction> filtered = new ArrayList<>();
        for (Transaction t : transactions) {
            boolean matches = true;
            if (!t.getDate().isBefore(start) && !t.getDate().isAfter(end)) {
                if (!desc.isEmpty() && !t.getDescription().toLowerCase().contains(desc)) matches = false;
                if (!vendor.isEmpty() && !t.getVendor().toLowerCase().contains(vendor)) matches = false;
                if (amountFilter != null && t.getAmount() != amountFilter) matches = false;
                if (matches) filtered.add(t);
            }
        }
        display(filtered);
    }

    public static void display(List<Transaction> transactions) {
        if (transactions.isEmpty()) {
            System.out.println("No matching transactions found.");
        } else {
            for (Transaction t : transactions) {
                System.out.println(t);
            }
        }
    }
}
