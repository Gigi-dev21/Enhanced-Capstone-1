import Classes.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            Menu.showMainMenu();
            String choice = sc.nextLine().toUpperCase();
            switch (choice) {
                case "D": ActionHandler.addTransaction(sc, true); break;
                case "P": ActionHandler.addTransaction(sc, false); break;
                case "L": LedgerApp.showLedger(sc); break;
                case "X": {
                    System.out.println("\u001B[36m👋 Thanks for using the Accounting Ledger App. Have a productive and financially savvy day!\u001B[0m");
                    return;
                }
            }
        }
    }
}
