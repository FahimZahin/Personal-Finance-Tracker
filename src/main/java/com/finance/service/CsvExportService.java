package com.finance.service;

import com.finance.view.TransactionRow;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class CsvExportService {

    /**
     * Exports all transactions to a CSV file.
     *
     * The date is exported as text so Excel/WPS does not
     * automatically convert it into a date value and display
     * ####### when the column is narrow.
     */
    public void exportTransactions(
            List<TransactionRow> transactions,
            File file
    ) throws IOException {

        if (transactions == null) {
            throw new IllegalArgumentException(
                    "Transaction list cannot be null."
            );
        }

        if (file == null) {
            throw new IllegalArgumentException(
                    "File cannot be null."
            );
        }

        try (
                BufferedWriter writer = Files.newBufferedWriter(
                        file.toPath(),
                        StandardCharsets.UTF_8
                )
        ) {

            /*
             * UTF-8 BOM.
             *
             * This helps Excel/WPS correctly recognize UTF-8 CSV files.
             */
            writer.write('\uFEFF');

            // CSV header
            writer.write(
                    "Date,Type,Details,Amount"
            );

            writer.newLine();

            // Write transactions
            for (TransactionRow transaction : transactions) {

                /*
                 * Write date as an Excel text formula.
                 *
                 * Example:
                 *
                 * ="2026-10-03"
                 *
                 * Excel displays:
                 *
                 * 2026-10-03
                 *
                 * instead of converting it into a date number.
                 */
                writer.write(
                        excelText(
                                transaction.getDate()
                        )
                );

                writer.write(",");

                writer.write(
                        escape(
                                transaction.getType()
                        )
                );

                writer.write(",");

                writer.write(
                        escape(
                                transaction.getDetail()
                        )
                );

                writer.write(",");

                writer.write(
                        formatAmount(
                                transaction.getAmount()
                        )
                );

                writer.newLine();
            }
        }
    }

    /**
     * Converts a string into an Excel text formula.
     *
     * Example:
     *
     * 2026-10-03
     *
     * becomes:
     *
     * ="2026-10-03"
     */
    private String excelText(String value) {

        if (value == null || value.isBlank()) {
            return "";
        }

        String escaped = value.replace(
                "\"",
                "\"\""
        );

        return "=\"" + escaped + "\"";
    }

    /**
     * Escapes normal CSV text.
     */
    private String escape(String value) {

        if (value == null) {
            return "";
        }

        String escaped = value.replace(
                "\"",
                "\"\""
        );

        if (
                escaped.contains(",")
                        ||
                        escaped.contains("\"")
                        ||
                        escaped.contains("\n")
                        ||
                        escaped.contains("\r")
        ) {

            return "\"" + escaped + "\"";
        }

        return escaped;
    }

    /**
     * Formats money values consistently.
     *
     * Example:
     *
     * 5000.0 -> 5000.00
     * 550.0  -> 550.00
     * 50000.0 -> 50000.00
     */
    private String formatAmount(double amount) {

        return String.format(
                java.util.Locale.US,
                "%.2f",
                amount
        );
    }
}