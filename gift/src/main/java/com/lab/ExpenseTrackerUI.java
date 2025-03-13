package com.lab;
 
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
 
public class ExpenseTrackerUI {
    private JFrame frame;
    private JTextField descField, amountField;
    private JComboBox<String> typeBox;
    private JTextArea displayArea;
    private JLabel balanceLabel;
    private double balance = 0;
    private double dailyLimit = 200; // กำหนดวงเงินใช้จ่ายต่อวัน
    private HashMap<String, Double> dailyExpenses = new HashMap<>(); // เก็บยอดใช้จ่ายรายวัน
    private ArrayList<Transaction> transactions = new ArrayList<>();
 
    public ExpenseTrackerUI() {
        frame = new JFrame("💰 GuRorBorBank");
        frame.setSize(500, 600); // ปรับขนาดหน้าต่างให้ใหญ่ขึ้น
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10)); // ใช้ BorderLayout และเพิ่มระยะห่าง
 
        // สร้าง JPanel สำหรับส่วนบน (Input)
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10)); // ใช้ GridLayout
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // เพิ่มระยะห่างรอบขอบ
 
        // UI Components
        typeBox = new JComboBox<>(new String[]{"Income 🟢", "Expense 🔴"});
        descField = new JTextField();
        amountField = new JTextField();
        JButton addButton = new JButton("Add ➕");
        JButton clearButton = new JButton("Clear ❌");
 
        // ปรับแต่งปุ่ม
        addButton.setBackground(new Color(50, 150, 50)); // สีเขียว
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        clearButton.setBackground(new Color(200, 50, 50)); // สีแดง
        clearButton.setForeground(Color.WHITE);
        clearButton.setFocusPainted(false);
 
        // เพิ่มคอมโพเนนต์ลงใน inputPanel
        inputPanel.add(new JLabel("Type: 📌"));
        inputPanel.add(typeBox);
        inputPanel.add(new JLabel("Description: 📝"));
        inputPanel.add(descField);
        inputPanel.add(new JLabel("Amount (฿): "));
        inputPanel.add(amountField);
        inputPanel.add(addButton);
        inputPanel.add(clearButton);
 
        // สร้าง JPanel สำหรับส่วนแสดงผล
        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // เพิ่มระยะห่างรอบขอบ
 
        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        displayPanel.add(scrollPane, BorderLayout.CENTER);
 
        // สร้าง JPanel สำหรับแสดงยอดเงิน
        JPanel balancePanel = new JPanel();
        balancePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // เพิ่มระยะห่างรอบขอบ
        balanceLabel = new JLabel("Balance: ฿0 💵");
        balanceLabel.setFont(new Font("SansSerif", Font.BOLD, 18)); // ปรับขนาดฟอนต์
        balancePanel.add(balanceLabel);
 
        // เพิ่มส่วนต่างๆ ลงใน frame
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(displayPanel, BorderLayout.CENTER);
        frame.add(balancePanel, BorderLayout.SOUTH);
 
        // Add button action
        addButton.addActionListener(e -> addTransaction());
 
        // Clear button action
        clearButton.addActionListener(e -> clearData());
 
        frame.setVisible(true);
    }
 
    private void addTransaction() {
        String type = (String) typeBox.getSelectedItem();
        String description = descField.getText();
        double amount;
        String date = java.time.LocalDate.now().toString(); // ดึงวันที่ปัจจุบัน
 
        try {
            amount = Double.parseDouble(amountField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "⚠️ Please enter a valid amount!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
 
        Transaction transaction = new Transaction(type, description, amount);
        transactions.add(transaction);
 
        // อัปเดตยอดเงิน
        if (type.contains("Income")) {
            balance += amount;
        } else {
            balance -= amount;
            dailyExpenses.put(date, dailyExpenses.getOrDefault(date, 0.0) + amount);
 
            // ตรวจสอบว่าวันนี้ใช้จ่ายเกิน 200 บาทหรือไม่
            if (dailyExpenses.get(date) > dailyLimit) {
                JOptionPane.showMessageDialog(frame, "🚨 Warning! You have spent over ฿200 today!", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        }
 
        // อัปเดต UI
        updateDisplay();
    }
 
    private void clearData() {
        // เคลียร์ข้อมูลทั้งหมด
        transactions.clear();
        dailyExpenses.clear();
        balance = 0;
 
        // เคลียร์ช่องกรอกข้อมูล
        descField.setText("");
        amountField.setText("");
 
        // อัปเดต UI
        updateDisplay();
    }
 
    private void updateDisplay() {
        displayArea.setText("");
        for (Transaction t : transactions) {
            displayArea.append(t.getType() + " - " + t.getDescription() + " : ฿" + t.getAmount() + "\n");
        }
        balanceLabel.setText("Balance: ฿" + balance + " 💵");
    }
 
    public static void main(String[] args) {
        new ExpenseTrackerUI();
    }
 
    // Static nested class for Transaction
    public static class Transaction {
        private String type;  // "รายรับ" หรือ "รายจ่าย"
        private String description;
        private double amount;
 
        public Transaction(String type, String description, double amount) {
            this.type = type;
            this.description = description;
            this.amount = amount;
        }
 
        public String getType() { return type; }
        public String getDescription() { return description; }
        public double getAmount() { return amount; }
    }
}