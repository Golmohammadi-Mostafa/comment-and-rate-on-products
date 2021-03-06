package com.egc.shopping.repository;

import com.egc.shopping.domain.Category;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByCode(String code);

    @EntityGraph(attributePaths = {"products"}, type = EntityGraph.EntityGraphType.FETCH)
    List<Category> findAll();
}
