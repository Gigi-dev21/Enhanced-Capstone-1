
// TransactionManager.java
import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class TransactionManager {
    private static final String FILE_NAME = "transactions.csv";

    public static List<Transaction> loadTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                transactions.add(Transaction.fromCSV(line));
            }
        } catch (IOException e) {}
        Collections.reverse(transactions);
        return transactions;
    }

    public static void saveTransaction(Transaction transaction) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            bw.write(transaction.toCSV());
            bw.newLine();
        } catch (IOException e) {}
    }

    public static List<Transaction> filterByAmount(List<Transaction> transactions, boolean isPositive) {
        List<Transaction> result = new ArrayList<>();
        for (Transaction t : transactions) {
            if (isPositive && t.getAmount() > 0) result.add(t);
            else if (!isPositive && t.getAmount() < 0) result.add(t);
        }
        return result;
    }
    public static void displayTransactions(List<Transaction> transactions) {
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }


    public static List<Transaction> filterByVendor(List<Transaction> transactions, String vendor) {
        List<Transaction> result = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getVendor().equalsIgnoreCase(vendor)) result.add(t);
        }
        return result;
    }

    public static List<Transaction> filterByDateRange(List<Transaction> transactions, LocalDate start, LocalDate end) {
        List<Transaction> result = new ArrayList<>();
        for (Transaction t : transactions) {
            if (!t.getDate().isBefore(start) && !t.getDate().isAfter(end)) result.add(t);
        }
        return result;
    }
}