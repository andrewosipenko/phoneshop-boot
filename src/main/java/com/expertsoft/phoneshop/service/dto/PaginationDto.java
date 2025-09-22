package com.expertsoft.phoneshop.service.dto;

public class PaginationDto {
    private Integer currentPage;
    private Integer totalPages;
    private boolean hasRightArrow;
    private boolean hasLeftArrow;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isHasLeftArrow() {
        return hasLeftArrow;
    }

    public void setHasLeftArrow(boolean hasLeftArrow) {
        this.hasLeftArrow = hasLeftArrow;
    }

    public boolean isHasRightArrow() {
        return hasRightArrow;
    }

    public void setHasRightArrow(boolean hasRightArrow) {
        this.hasRightArrow = hasRightArrow;
    }
}
