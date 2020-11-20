package com.aiocdawacs.pdf.files.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Ignore;
import org.junit.Test;

import com.aiocdawacs.files.pdf.invoice.PDFLayoutTextStripper;

@Ignore
public class TestInvoice2 {

    private static final int spaceTolerance = 5;

    @Test
    public void test() throws IOException {
        try {
        	ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("REINV.pdf").getFile());
            PDFParser pdfParser = new PDFParser(new RandomAccessFile(file, "r"));
            pdfParser.parse();
            PDDocument pdDocument = new PDDocument(pdfParser.getDocument());
            PDFTextStripper pdfTextStripper = new PDFLayoutTextStripper();
            String invoiceString = pdfTextStripper.getText(pdDocument);
            String[] lines = invoiceString.split("\n");

            String[] patternStrings = new String[]{
                "(\\d+)",
                "(\\d+\\/\\d+\\/\\d+\\s\\d+\\:\\d+)",
                "(\\d+\\/\\d+\\/\\d+\\s\\d+\\:\\d+)",
                "([a-z0-9A-Z]{5,})"
            };
            String patternString = "\\s+" + String.join("\\s+", patternStrings) + "\\s+";
            Pattern p = Pattern.compile(patternString);
            for (String line : lines) {
                Matcher matcher = p.matcher(line);
                if (matcher.find()) {
                    String orderNo = matcher.group(1);
                    String orderDate = matcher.group(2);
                    String creationDate = matcher.group(3);
                    String supplierCode = matcher.group(4);
                    System.out.print(
                        "Order No: " + orderNo + ", orderDate: " + orderDate
                        + ", creationDate: " + creationDate
                        + ", supplierCode: " + supplierCode
                    );
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        };
    }
}
