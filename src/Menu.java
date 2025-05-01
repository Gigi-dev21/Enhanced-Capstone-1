public class Menu {
    public static void showMainMenu() {
        System.out.println("\n🏠 \u001B[34mMain Menu\u001B[0m");
        System.out.println("💰 D) Add Deposit");
        System.out.println("💸 P) Make Payment (Debit)");
        System.out.println("📒 L) Ledger");
        System.out.println("❌ X) Exit");
    }

    public static void showLedgerMenu() {
        System.out.println("\n📒 \u001B[34mLedger Menu\u001B[0m");
        System.out.println("🧾 A) All");
        System.out.println("💰 D) Deposits");
        System.out.println("💸 P) Payments");
        System.out.println("📊 R) Reports");
        System.out.println("🏠 H) Home");
    }

    public static void showReportMenu() {
        System.out.println("\n📊 \u001B[34mReport Menu\u001B[0m");
        System.out.println("1️⃣ 1) Month To Date");
        System.out.println("2️⃣ 2) Previous Month");
        System.out.println("3️⃣ 3) Year To Date");
        System.out.println("4️⃣ 4) Previous Year");
        System.out.println("🔍 5) Search by Vendor");
        System.out.println("🛠️ 6) Custom Search");
        System.out.println("0️⃣ 0) Back");
    }
}
