package com.expertsoft.phoneshop.persistence.repository;

import com.expertsoft.phoneshop.persistence.model.Phone;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
    Optional<Phone> findOneById(Long id);

    @Query("FROM Phone p WHERE (p.brand LIKE %?1% OR p.model LIKE %?1%) AND p.price BETWEEN ?2 and ?3")
    Page<Phone> findAllByBrandContainingAndModelContainingAndPriceBetween(String query, BigDecimal priceFrom,
            BigDecimal priceTo, Pageable pageable);

    Page<Phone> findAllByPriceBetween(BigDecimal priceFrom, BigDecimal priceTo, Pageable pageable);

}
