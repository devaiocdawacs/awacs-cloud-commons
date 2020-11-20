package com.aiocdawacs.pdf.files.test;

import org.junit.Test;

import com.aiocdawacs.files.pdf.entity.Table;
import com.aiocdawacs.files.pdf.entity.TableCell;
import com.aiocdawacs.files.pdf.entity.TableRow;

public class TestTableRow {

    @Test
    public void doTest() {
        TableRow row = new TableRow(0);

        TableCell cell0 = new TableCell(0, "");
        row.getCells().add(cell0);
        
        TableCell cell1 = new TableCell(1, "2");
        row.getCells().add(cell1);

        TableCell cell2 = new TableCell(2, "Vietnam");
        row.getCells().add(cell2);

        System.out.println("Row: " + row);

        Table table = new Table(0, 3);
        table.getRows().add(row);

        System.out.println("Table: " + table);

    }
}
