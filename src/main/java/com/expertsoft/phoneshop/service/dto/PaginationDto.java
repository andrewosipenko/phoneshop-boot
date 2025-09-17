package com.expertsoft.phoneshop.service.dto;

public class PaginationDto {
    private Integer currentPage;
    private Integer totalPages;
    private Integer pageSize;
    private boolean hasRightArrow;
    private boolean hasLeftArrow;
    private Integer startPage;
    private Integer endPage;

    public Integer getStartPage() {
        return startPage;
    }

    public void setStartPage(Integer startPage) {
        this.startPage = startPage;
    }

    public Integer getEndPage() {
        return endPage;
    }

    public void setEndPage(Integer endPage) {
        this.endPage = endPage;
    }

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

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
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
