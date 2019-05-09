package com.example.demo.domain;

public class Page {

    private int rows;
    private int page;
    private String sort; //排序名称
    private String sortOrder; //升序或者降序

    @Override
    public String toString() {
        return "Page{" +
                "rows=" + rows +
                ", page=" + page +
                ", sort=" + sort +
                ", sortOrder=" + sortOrder +
                '}';
    }

    public Page() {
    }

    public Page(int rows, int page, String sort, String sortOrder) {
        this.rows = rows;
        this.page = page;
        this.sort = sort;
        this.sortOrder = sortOrder;
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

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }
}
