package com.expertsoft.phoneshop.data;

public class PaginationData {
    private final int currentPage;
    private final int totalPages;
    private final int first;
    private final int last;

    public PaginationData(int currentPage, int totalPages, int pagesShown) {
        this.totalPages = Math.max(1, totalPages);
        this.currentPage = Math.min(Math.max(1, currentPage), this.totalPages);

        int window = Math.min(pagesShown, this.totalPages);
        int start = this.currentPage - window / 2;
        start = Math.max(1, Math.min(start, this.totalPages - window + 1));

        this.first = start;
        this.last = start + window - 1;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getFirst() {
        return first;
    }

    public int getLast() {
        return last;
    }

    public boolean isHasPrevious() {
        return currentPage > 1;
    }

    public boolean isHasNext() {
        return currentPage < totalPages;
    }

    public int getPreviousPage() {
        return currentPage - 1;
    }

    public int getNextPage() {
        return currentPage + 1;
    }
}
