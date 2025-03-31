package com.lab;
 
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
 
public class ExpenseTrackerUI {
    private JFrame frame;
    private JTextField descField, amountField;
    private JComboBox<String> typeBox, filterBox;
    private JTextArea displayArea;
    private JLabel balanceLabel;
    private double balance = 0;
    private double dailyLimit = 200;
    private HashMap<String, Double> dailyExpenses = new HashMap<>();
    private ArrayList<Transaction> transactions = new ArrayList<>();
 
    public ExpenseTrackerUI() {
        frame = new JFrame("💰 GuRorBorBank");
        frame.setSize(600, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));
 
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
 
        typeBox = new JComboBox<>(new String[]{"Income 🟢", "Expense 🔴"});
        descField = new JTextField();
        amountField = new JTextField();
        JButton addButton = new JButton("Add ➕");
        JButton clearButton = new JButton("Clear ❌");
        filterBox = new JComboBox<>(new String[]{"All", "Income 🟢", "Expense 🔴"});
        JButton filterButton = new JButton("Filter 🔍");
 
        inputPanel.add(new JLabel("Type: 📌"));
        inputPanel.add(typeBox);
        inputPanel.add(new JLabel("Description: 📝"));
        inputPanel.add(descField);
        inputPanel.add(new JLabel("Amount (฿):"));
        inputPanel.add(amountField);
        inputPanel.add(addButton);
        inputPanel.add(clearButton);
        inputPanel.add(filterBox);
        inputPanel.add(filterButton);
 
        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        displayPanel.add(scrollPane, BorderLayout.CENTER);
 
        JPanel balancePanel = new JPanel();
        balanceLabel = new JLabel("Balance: ฿0");
        balanceLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        balancePanel.add(balanceLabel);
 
        JPanel chartPanel = new JPanel();
        chartPanel.setLayout(new BorderLayout());
        frame.add(chartPanel, BorderLayout.EAST);
 
        addButton.addActionListener(e -> {
            addTransaction();
            updateChart(chartPanel);
        });
        clearButton.addActionListener(e -> clearData());
        filterButton.addActionListener(e -> filterTransactions());
 
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(displayPanel, BorderLayout.CENTER);
        frame.add(balancePanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
 
    private void addTransaction() {
        String type = (String) typeBox.getSelectedItem();
        String description = descField.getText();
        double amount;
        String date = LocalDate.now().toString();
 
        try {
            amount = Double.parseDouble(amountField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "⚠️ Please enter a valid amount!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
 
        Transaction transaction = new Transaction(type, description, amount, date);
        transactions.add(transaction);
 
        if (type.contains("Income")) {
            balance += amount;
        } else {
            balance -= amount;
            dailyExpenses.put(date, dailyExpenses.getOrDefault(date, 0.0) + amount);
            if (dailyExpenses.get(date) > dailyLimit) {
                JOptionPane.showMessageDialog(frame, "🚨 Warning! You have spent over ฿200 today!", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        }
        updateDisplay(transactions);
    }
 
    private void clearData() {
        transactions.clear();
        dailyExpenses.clear();
        balance = 0;
        descField.setText("");
        amountField.setText("");
        updateDisplay(transactions);
    }
 
    private void updateDisplay(List<Transaction> list) {
        displayArea.setText("");
        for (Transaction t : list) {
            displayArea.append(t.getDate() + " | " + t.getType() + " - " + t.getDescription() + " : ฿" + t.getAmount() + "\n");
        }
        balanceLabel.setText("Balance: ฿" + balance + " 💵");
    }
 
    private void filterTransactions() {
        String selectedType = (String) filterBox.getSelectedItem();
        if (selectedType.equals("All")) {
            updateDisplay(transactions);
            return;
        }
        List<Transaction> filteredList = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getType().equals(selectedType)) {
                filteredList.add(t);
            }
        }
        updateDisplay(filteredList);
    }
 
    private void updateChart(JPanel chartPanel) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (String date : dailyExpenses.keySet()) {
            dataset.addValue(dailyExpenses.get(date), "Expenses", date);
        }
        JFreeChart barChart = ChartFactory.createBarChart("Daily Expenses", "Date", "Amount (฿)", dataset);
        chartPanel.removeAll();
        chartPanel.add(new ChartPanel(barChart), BorderLayout.CENTER);
        chartPanel.validate();
    }
 
    public static class Transaction {
        private String type;
        private String description;
        private double amount;
        private String date;
 
        public Transaction(String type, String description, double amount, String date) {
            this.type = type;
            this.description = description;
            this.amount = amount;
            this.date = date;
        }
 
        public String getType() { return type; }
        public String getDescription() { return description; }
        public double getAmount() { return amount; }
        public String getDate() { return date; }
    }
 
    public static void main(String[] args) {
        new ExpenseTrackerUI();
    }
}
 
 