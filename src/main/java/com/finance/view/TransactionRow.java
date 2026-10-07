package com.finance.view;

public class TransactionRow {

    private final int id;
    private final String date;
    private final String type;
    private final String detail;
    private final double amount;

    public TransactionRow(
            int id,
            String date,
            String type,
            String detail,
            double amount
    ) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.detail = detail;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public String getDetail() {
        return detail;
    }

    public double getAmount() {
        return amount;
    }

    public String getFormattedAmount(){

        return String.format(
                "%.2f",
                amount
        );

    }
}