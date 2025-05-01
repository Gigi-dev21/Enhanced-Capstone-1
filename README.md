#  Accounting Ledger App (Java CLI)

Welcome to the Accounting Ledger App, a  Java-based Command Line Interface (CLI)  that helps users track financial transactions — deposits, payments, and custom reports — stored in a persistent `transactions.csv` file.

This project was built as my Capstone 1 for the *Java Development Fundamentals* module in YearUp. It demonstrates clean OOP design, real-world logic, and user-focused CLI interaction.



##  Project Goals (per Capstone Spec )

- [x] Add Deposits & Payments  
- [x] Display Ledger (All / Deposits / Payments)  
- [x] Run Reports:  
  - Month-to-Date  
  - Previous Month  
  - Year-to-Date  
  - Previous Year  
  - Search by Vendor  
  - **Custom Search** by date, description, vendor, amount  
- [x] Input validation (no crashing on bad input)  
- [x] Stores data in `transactions.csv`  
- [x] Uses `Scanner`, `BufferedReader/Writer`, `ArrayList`, `LocalDate/Time`



##  Features & Highlights

CLI Menu with emojis and ANSI-colored text  
Add deposits &  make payments — cleanly saved  
View ledger, sorted newest first  
Built-in Reports with live filters  
Custom Search (mix & match fields)  
Summary Stats: Income, Expenses, Net Balance  
Smart Error Handling — app doesn't crash on bad input



##  Screenshots 

Take and upload these to your GitHub repo

- 🏠 Main Menu  
- 💰 Deposit Form  
- 📉 Reports Screen  
- 🔍 Custom Search Prompt  
- 📊 Ledger Output + Net Balance Summary



##  Interesting Code Snippet

Safe numeric input handling with retry prompt:

```java
public static double getValidAmount(Scanner sc) {
    while (true) {
        String input = sc.nextLine();
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.print("\u001B[31m❌ Invalid number. Try something like 125.50 💡\u001B[0m\n💵 Enter amount again: ");
        }
    }
}
