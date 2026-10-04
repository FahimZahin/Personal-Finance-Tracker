package com.finance.service;


import com.finance.view.TransactionRow;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import java.util.List;



public class CsvExportService {


    public void exportTransactions(
            List<TransactionRow> transactions,
            File file
    ) throws IOException {


        try (
                BufferedWriter writer =
                        Files.newBufferedWriter(
                                file.toPath(),
                                StandardCharsets.UTF_8
                        )
        ) {


            writer.write(
                    "Date,Type,Details,Amount"
            );

            writer.newLine();



            for (TransactionRow transaction :
                    transactions) {


                writer.write(
                        escape(transaction.getDate())
                );


                writer.write(",");


                writer.write(
                        escape(transaction.getType())
                );


                writer.write(",");


                writer.write(
                        escape(transaction.getDetail())
                );


                writer.write(",");


                writer.write(
                        String.valueOf(
                                transaction.getAmount()
                        )
                );


                writer.newLine();

            }

        }

    }




    private String escape(String value) {


        if (value == null) {

            return "";

        }


        String escaped =
                value.replace(
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


}