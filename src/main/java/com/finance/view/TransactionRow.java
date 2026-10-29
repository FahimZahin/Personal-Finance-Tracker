package com.finance.view;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TransactionRow {

    private final SimpleIntegerProperty id;
    private final SimpleStringProperty date;
    private final SimpleStringProperty type;
    private final SimpleStringProperty detail;
    private final SimpleDoubleProperty amount;

    public TransactionRow(
            int id,
            String date,
            String type,
            String detail,
            double amount
    ) {
        this.id = new SimpleIntegerProperty(id);
        this.date = new SimpleStringProperty(date);
        this.type = new SimpleStringProperty(type);
        this.detail = new SimpleStringProperty(detail);
        this.amount = new SimpleDoubleProperty(amount);
    }

    // ID Properties
    public int getId() { return id.get(); }
    public SimpleIntegerProperty idProperty() { return id; }
    public void setId(int id) { this.id.set(id); }

    // Date Properties
    public String getDate() { return date.get(); }
    public SimpleStringProperty dateProperty() { return date; }
    public void setDate(String date) { this.date.set(date); }

    // Type Properties
    public String getType() { return type.get(); }
    public SimpleStringProperty typeProperty() { return type; }
    public void setType(String type) { this.type.set(type); }

    // Detail Properties
    public String getDetail() { return detail.get(); }
    public SimpleStringProperty detailProperty() { return detail; }
    public void setDetail(String detail) { this.detail.set(detail); }

    // Amount Properties
    public double getAmount() { return amount.get(); }
    public SimpleDoubleProperty amountProperty() { return amount; }
    public void setAmount(double amount) { this.amount.set(amount); }

    public String getFormattedAmount() {
        return String.format("%.2f", amount.get());
    }
}