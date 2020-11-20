package com.aiocdawacs.files.pdf.entity;

public class TableCell {
    private final String content;
    private final int idx;

    public TableCell(int idx, String content) {
        this.idx = idx;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public int getIdx() {
        return idx;
    }
}
