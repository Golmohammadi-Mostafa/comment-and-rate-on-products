package com.egc.shopping.service;

import com.egc.shopping.dto.ProductDTO;
import com.egc.shopping.dto.ProductRequestDTO;

import java.util.Set;

public interface ProductService {


    Set<ProductDTO> findAll();

    ProductDTO getByCode(String name);

    ProductDTO getById(Long id);

    Set<ProductDTO> getByPrice(Double price);

    ProductDTO create(ProductRequestDTO categoryDTO);

    ProductDTO update(ProductRequestDTO productDTO, Long id);

    void deleteById(Long id);

    Set<ProductDTO> getAllByCategory(Long categoryId);

    void updateAllProductsOfCategory(Double price, String categoryCode);

}

