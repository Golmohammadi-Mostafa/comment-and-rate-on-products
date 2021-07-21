package com.egc.shopping.service;

import com.egc.shopping.dto.CategoryDTO;
import com.egc.shopping.dto.CategoryRequestDTO;

import java.util.Set;

public interface CategoryService {
    Set<CategoryDTO> findAll();

    CategoryDTO getByCode(String code);

    CategoryDTO getById(Long id);

    CategoryDTO update(CategoryRequestDTO categoryDTO, Long id);

    void deleteById(Long id);

    CategoryDTO create(CategoryRequestDTO categoryDTO);
}
