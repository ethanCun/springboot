package com.example.demo.domain;

public class Page {

    private int rows;
    private int page;

    @Override
    public String toString() {
        return "Page{" +
                "rows=" + rows +
                ", page=" + page +
                '}';
    }

    public Page() {
    }

    public Page(int rows, int page) {
        this.rows = rows;
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
