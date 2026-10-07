package com.finance.service;


import java.io.File;


import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;



public class PdfReportService {



    public void createReport(
            File file,
            int month,
            int year,
            double income,
            double expense,
            double savings
    ){



        try{


            Document document =
                    new Document();



            PdfWriter.getInstance(
                    document,
                    new java.io.FileOutputStream(file)
            );



            document.open();



            document.add(
                    new Paragraph(
                            "Personal Finance Tracker"
                    )
            );



            document.add(
                    new Paragraph(
                            "Monthly Financial Report"
                    )
            );



            document.add(
                    new Paragraph(
                            "Month: "
                                    + month
                                    + "/"
                                    + year
                    )
            );



            document.add(
                    new Paragraph(
                            " "
                    )
            );



            document.add(
                    new Paragraph(
                            "Total Income: "
                                    + income
                    )
            );



            document.add(
                    new Paragraph(
                            "Total Expense: "
                                    + expense
                    )
            );



            document.add(
                    new Paragraph(
                            "Savings: "
                                    + savings
                    )
            );



            document.close();



        }
        catch(Exception e){


            e.printStackTrace();


        }



    }


}