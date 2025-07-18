# 📘 Accounting Ledger App (Java CLI)

Welcome to the **Accounting Ledger App** — a Java-based Command Line Interface (CLI) that helps users track financial transactions like deposits, payments, and generate custom reports. Data is stored persistently in a `transactions.csv` file.

This project was built as my **Capstone 1** for the *Java Development Fundamentals* module at **YearUp**. It demonstrates clean OOP design, real-world financial logic, and a user-friendly CLI experience.

---

## 🚀 New Features We Added

1. ✨ Enhanced UI with colors and emojis  
2. 📊 Total income, expenses, and net balance display  
3. 🛡️ Safe error handling using try-catch blocks  
4. 🔍 Custom search by fields  
5. 📁 Organized code using a proper package structure  
6. 🗃️ Created and connected a database for persistent storage  

---

## 🎯 Project Goals (per Capstone Spec)

- Add deposits & payments  
- Display ledger: All / Deposits / Payments  
- Run reports:  
  - Month-to-Date  
  - Previous Month  
  - Year-to-Date  
  - Previous Year  
  - Search by Vendor  
  - Custom search by date, description, vendor, amount  
- Input validation (no crashing on bad input)  
- Stores data in `transactions.csv`  
- Uses `Scanner`, `BufferedReader/Writer`, `ArrayList`, `LocalDate/Time`  

---

## 🌟 Features & Highlights

- 🎛️ CLI menu with emojis and ANSI-colored text  
- 💰 Add deposits & make payments — cleanly saved  
- 📄 View ledger, sorted with most recent first  
- 📉 Built-in reports with real-time filters  
- 🔍 Custom search (mix and match fields)  
- 📊 Summary stats: income, expenses, and net balance  
- 🚫 Smart error handling — app doesn’t crash on bad input  

---

## 🖼️ Screenshots

### 🏠 Main Menu  
![Main Menu](https://github.com/user-attachments/assets/50e1ada7-0810-4e06-810e-6af444351d98)

### 💰 Deposit Form  
![Deposit Form](https://github.com/user-attachments/assets/b6cb1675-db66-4f93-8b64-e85e08bb061e)

### 📉 Reports Screen  
![Reports Screen](https://github.com/user-attachments/assets/5c8b7473-72d6-4cee-8cae-05c46315067f)

### 🔍 Custom Search Prompt  
![Custom Search Prompt](https://github.com/user-attachments/assets/70784b23-fa33-4af0-bf46-4bbc44ef90d4)

### 📊 Ledger Output + Net Balance Summary  
![Ledger Output](https://github.com/user-attachments/assets/1c0d073b-40ae-4162-bce4-b2170690de7d)

### 📊 My sql Database  
![Ledger Output](src/DbImage.png)

### 📊 My sql Database Query for Displaying Transactions
![Ledger Output](src/DbCode.png)
---

## 💡 Interesting Code Snippet

Safe numeric input with a retry prompt:
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
