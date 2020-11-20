package com.aiocdawacs.pdf.files.test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;
import org.junit.Test;

import com.aiocdawacs.files.pdf.TrapRangeBuilder;
import com.google.common.collect.Range;

public class TESTPDFBox extends PDFTextStripper {

    private final List<Range<Integer>> ranges = new ArrayList<>();

    private final TrapRangeBuilder trapRangeBuilder = new TrapRangeBuilder();

    public TESTPDFBox() throws IOException {
        super.setSortByPosition(true);
    }

    @Test
    public void test() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File pdfFile = new File(classLoader.getResource("sample-invoice.pdf").getFile());

        PDDocument pdDocument = PDDocument.load(pdfFile);
        PDPage page = pdDocument.getPage(0);

        this.processPage(page);
        //Print out all text    
        Collections.sort(ranges, new Comparator<Range>() {
            @Override
            public int compare(Range o1, Range o2) {
                return o1.lowerEndpoint().compareTo(o2.lowerEndpoint());
            }
        });
        for (Range range : ranges) {
            System.out.println("> " + range);
        }
        //Print out all ranges
        List<Range<Integer>> trapRanges = trapRangeBuilder.build();
        for (Range trapRange : trapRanges) {
            System.out.println("TrapRange: " + trapRange);
        }
    }

    @Override
    protected void processTextPosition(TextPosition text) {
        Range range = Range.closed((int) text.getY(), (int) (text.getY() + text.getHeight()));
        //System.out.println("Text: " + text.getUnicode());
        trapRangeBuilder.addRange(range);
    }
}
