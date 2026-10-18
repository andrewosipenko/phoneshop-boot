package com.expertsoft.phoneshop.service;

import com.expertsoft.phoneshop.data.SortData;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class SortService {

    public Sort createSort(SortData sortData) {
        Sort defaultSort = Sort.by(Sort.Direction.ASC, "id");
        if (sortData == null || sortData.getSort() == null) {
            return defaultSort;
        }

        Sort.Direction direction = "desc".equalsIgnoreCase(sortData.getOrder())
                ? Sort.Direction.DESC : Sort.Direction.ASC;
        return Sort.by(direction, sortData.getSort()).and(defaultSort);
    }
}
