package com.egc.shopping.repository;

import com.egc.shopping.domain.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @EntityGraph(attributePaths = {"comments", "rates"}, type = EntityGraph.EntityGraphType.FETCH)
    Optional<Product> findByName(String name);

    @EntityGraph(attributePaths = {"comments", "rates"}, type = EntityGraph.EntityGraphType.FETCH)
    List<Product> findAllByPrice(Double price);

    List<Product> findAllByPriceBetween(Double from, Double to);

//    @EntityGraph(attributePaths = {"comments", "rates"}, type = EntityGraph.EntityGraphType.FETCH)
//    List<Product> findAll();

    @EntityGraph(attributePaths = {"comments", "rates"}, type = EntityGraph.EntityGraphType.FETCH)
    Optional<Product> findById(Long id);

    @EntityGraph(attributePaths = {"comments", "rates"}, type = EntityGraph.EntityGraphType.FETCH)
    List<Product> findAllByCategory_Id(Long categoryId);

    List<Product> findAllByCategory_Code(String categoryCode);

}
