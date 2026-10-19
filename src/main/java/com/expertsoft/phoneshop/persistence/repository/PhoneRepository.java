package com.expertsoft.phoneshop.persistence.repository;

import com.expertsoft.phoneshop.persistence.model.Phone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
    @Query("select p from Phone p where " +
            "(:query is null or lower(p.brand) like lower(concat('%', :query, '%')) " +
            "or lower(p.model) like lower(concat('%', :query, '%'))) " +
            "and (:priceFrom is null or p.price >= :priceFrom) " +
            "and (:priceTo is null or p.price <= :priceTo)")
    Page<Phone> search(@Param("query") String query,
                       @Param("priceFrom") BigDecimal priceFrom,
                       @Param("priceTo") BigDecimal priceTo,
                       Pageable pageable);
}
