

// Main.java
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
                case "L": ActionHandler.openLedger(sc); break;
                case "X": return;
            }
        }
    }
}
