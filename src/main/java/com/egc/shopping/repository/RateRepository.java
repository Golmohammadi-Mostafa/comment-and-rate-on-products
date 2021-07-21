package com.egc.shopping.repository;

import com.egc.shopping.domain.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RateRepository extends JpaRepository<Rate, Long> {
    List<Rate> findAllByProduct_Name(String productName);

}
