import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\nD) Add Deposit\nP) Make Payment (Debit)\nL) Ledger\nX) Exit");
            String choice = sc.nextLine().toUpperCase();
            switch (choice) {
                case "D": addTransaction(sc, true); break;
                case "P": addTransaction(sc, false); break;
                case "L": LedgerApp.showLedger(sc); break;
                case "X": return;
            }
        }
    }

    public static void addTransaction(Scanner sc, boolean isDeposit) {
        System.out.print("Description: ");
        String description = sc.nextLine();
        System.out.print("Vendor: ");
        String vendor = sc.nextLine();
        System.out.print("Amount: ");
        double amount = Double.parseDouble(sc.nextLine());
        if (!isDeposit) amount *= -1;
        Transaction t = new Transaction(java.time.LocalDate.now(), java.time.LocalTime.now(), description, vendor, amount);
        TransactionManager.saveTransaction(t);
    }
}
