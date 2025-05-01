
// Menu.java
public class Menu {
    public static void showMainMenu() {
        System.out.println("\nD) Add Deposit");
        System.out.println("P) Make Payment (Debit)");
        System.out.println("L) Ledger");
        System.out.println("X) Exit");
    }

    public static void showLedgerMenu() {
        System.out.println("\nA) All");
        System.out.println("D) Deposits");
        System.out.println("P) Payments");
        System.out.println("R) Reports");
        System.out.println("H) Home");
    }

    public static void showReportMenu() {
        System.out.println("\n1) Month To Date");
        System.out.println("2) Previous Month");
        System.out.println("3) Year To Date");
        System.out.println("4) Previous Year");
        System.out.println("5) Search by Vendor");
        System.out.println("6) Custom Search");
        System.out.println("0) Back");
    }
}