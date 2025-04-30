// LedgerApp.java
import java.time.LocalDate;
import java.util.*;

public class LedgerApp {
    public static void showLedger(Scanner sc) {
        List<Transaction> transactions = TransactionManager.loadTransactions();
        while (true) {
            System.out.println("\nA) All\nD) Deposits\nP) Payments\nR) Reports\nH) Home");
            String choice = sc.nextLine().toUpperCase();
            switch (choice) {
                case "A": display(transactions); break;
                case "D": display(TransactionManager.filterByAmount(transactions, true)); break;
                case "P": display(TransactionManager.filterByAmount(transactions, false)); break;
                case "R": runReports(sc, transactions); break;
                case "H": return;
            }
        }
    }

    public static void runReports(Scanner sc, List<Transaction> transactions) {
        while (true) {
            System.out.println("\n1) Month To Date\n2) Previous Month\n3) Year To Date\n4) Previous Year\n5) Search by Vendor\n6) Custom Search\n0) Back");
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

        LocalDate start = startDateStr.isEmpty() ? LocalDate.MIN : LocalDate.parse(startDateStr);
        LocalDate end = endDateStr.isEmpty() ? LocalDate.MAX : LocalDate.parse(endDateStr);

        List<Transaction> filtered = new ArrayList<>();
        for (Transaction t : transactions) {
            boolean matches = true;
            if (!t.getDate().isBefore(start) && !t.getDate().isAfter(end)) {
                if (!desc.isEmpty() && !t.getDescription().toLowerCase().contains(desc)) matches = false;
                if (!vendor.isEmpty() && !t.getVendor().toLowerCase().contains(vendor)) matches = false;
                if (!amountStr.isEmpty() && t.getAmount() != Double.parseDouble(amountStr)) matches = false;
                if (matches) filtered.add(t);
            }
        }
        display(filtered);
    }

    public static void display(List<Transaction> transactions) {
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }
}