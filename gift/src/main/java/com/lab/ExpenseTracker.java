package com.lab;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpenseTracker extends Application {
    //สร้างตัวแปร
    private TextField descField, amountField;
    private ComboBox<String> typeBox, filterBox;
    private TextArea displayArea;
    private Label balanceLabel;
    private double balance = 0;
    private double dailyLimit = 200;
    private HashMap<String, Double> dailyExpenses = new HashMap<>();
    private ArrayList<Transaction> transactions = new ArrayList<>();

    //คลาสหลัก
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("💰 SA X AUANZA 888");
        
        // Input Panel
        GridPane inputPane = new GridPane();
        inputPane.setPadding(new Insets(15));
        inputPane.setHgap(10);
        inputPane.setVgap(10);
        
        typeBox = new ComboBox<>();
        typeBox.getItems().addAll("Income 🟢", "Expense 🔴");
        typeBox.setValue("Income 🟢");
        
        descField = new TextField();
        amountField = new TextField();
        
        Button addButton = new Button("Add ➕");
        addButton.setStyle("-fx-background-color:rgb(63, 134, 85); -fx-text-fill: white;");
        
        Button clearButton = new Button("Clear 🧹");
        clearButton.setStyle("-fx-background-color:rgb(163, 70, 70); -fx-text-fill: white;");
        
        inputPane.add(new Label("Type: 📌"), 0, 0);
        inputPane.add(typeBox, 1, 0);
        inputPane.add(new Label("Description: 📝"), 0, 1);
        inputPane.add(descField, 1, 1);
        inputPane.add(new Label("Amount (฿): 💲"), 0, 2);
        inputPane.add(amountField, 1, 2);
        inputPane.add(addButton, 0, 3);
        inputPane.add(clearButton, 1, 3);
        
        // Filter Panel
        filterBox = new ComboBox<>();
        filterBox.getItems().addAll("All", "Income 🟢", "Expense 🔴");
        filterBox.setValue("All");
        filterBox.setOnAction(e -> filterTransactions());
        
        HBox filterPane = new HBox(10, new Label("Filter: 🔍"), filterBox);
        filterPane.setPadding(new Insets(10));
        
        // Display Panel
        displayArea = new TextArea();
        displayArea.setEditable(false);
        displayArea.setPrefHeight(200);
        
        ScrollPane scrollPane = new ScrollPane(displayArea);
        scrollPane.setFitToWidth(true);
        
        // Balance Panel
        balanceLabel = new Label("Balance: ฿0 💵");
        balanceLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        
        VBox balancePane = new VBox(balanceLabel);
        balancePane.setPadding(new Insets(10));
        balancePane.setStyle("-fx-background-color:rgb(212, 169, 147); -fx-alignment: center;");
        
        // Main Layout
        VBox mainLayout = new VBox(10, inputPane, filterPane, scrollPane, balancePane);
        mainLayout.setPadding(new Insets(10));
        
        // Button Actions
        addButton.setOnAction(e -> addTransaction());
        clearButton.setOnAction(e -> clearData());
        
        primaryStage.setScene(new Scene(mainLayout, 450, 550));
        primaryStage.show();
    }

    private void addTransaction() {
        String type = typeBox.getValue();
        String description = descField.getText();
        double amount;
        String date = LocalDate.now().toString();
        
        try {
            amount = Double.parseDouble(amountField.getText());
        } catch (NumberFormatException e) {
            showAlert("Error", "⚠️ Please enter a valid amount!");
            return;
        }
        
        Transaction transaction = new Transaction(type, description, amount);
        transactions.add(transaction);
        
        if (type.contains("Income")) {
            balance += amount;
        } else {
            balance -= amount;
            dailyExpenses.put(date, dailyExpenses.getOrDefault(date, 0.0) + amount);
            if (dailyExpenses.get(date) > dailyLimit) {
                showAlert("Warning", "🚨 You have spent over ฿200 today!");
            }
        }
        updateDisplay(transactions);
    }

    private void clearData() {
        transactions.clear();
        dailyExpenses.clear();
        balance = 0;
        descField.clear();
        amountField.clear();
        updateDisplay(transactions);
    }

    private void updateDisplay(List<Transaction> list) {
        displayArea.clear();
        for (Transaction t : list) {
            displayArea.appendText(t.getType() + " - " + t.getDescription() + " : ฿" + t.getAmount() + "\n");
        }
        balanceLabel.setText("Balance: ฿" + balance + " 💵");
    }
    
    private void filterTransactions() {
        String selectedType = filterBox.getValue();
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
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static class Transaction {
        private String type;
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
    
    public static void main(String[] args) {
        launch(args);
    }
}