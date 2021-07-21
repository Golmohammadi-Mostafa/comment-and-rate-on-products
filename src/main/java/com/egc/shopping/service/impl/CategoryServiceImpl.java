package com.egc.shopping.service.impl;

import com.egc.shopping.domain.Category;
import com.egc.shopping.dto.CategoryDTO;
import com.egc.shopping.dto.CategoryRequestDTO;
import com.egc.shopping.exception.CustomException;
import com.egc.shopping.mapper.CategoryMapper;
import com.egc.shopping.repository.CategoryRepository;
import com.egc.shopping.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public Set<CategoryDTO> findAll() {
        LOGGER.info("Request to get all categories....");
        List<Category> categories = categoryRepository.findAll();
        if (!CollectionUtils.isEmpty(categories)) {
            return categoryMapper.map(new HashSet<>(categories));
        } else {
            throw new CustomException("The category doesn't exist", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public CategoryDTO getByCode(String code) {
        LOGGER.info("Request to get category by code:{}", code);
        Optional<Category> byCode = categoryRepository.findByCode(code);
        if (byCode.isPresent()) {
            return categoryMapper.categoryToCategoryDTO(byCode.get());
        } else {
            throw new CustomException("The category doesn't exist", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public CategoryDTO getById(Long id) {
        LOGGER.info("Request to get category by id:{}", id);
        Category category = categoryRepository.getById(id);
        return categoryMapper.categoryToCategoryDTO(category);
    }

    @Override
    public CategoryDTO update(CategoryRequestDTO categoryDTO, Long id) {
        return categoryRepository.findById(id)
                .map(category -> {
                    category.setCode(categoryDTO.getCode());
                    category.setTitle(categoryDTO.getTitle());
                    Category c = categoryRepository.save(category);
                    LOGGER.info("updated category by id:{} successfully", id);
                    return categoryMapper.categoryToCategoryDTO(c);
                })
                .orElseGet(() -> {
                    Category category = categoryRepository.save(categoryMapper.dtoToCategory(categoryDTO));
                    LOGGER.info("created category by code:{} successfully", categoryDTO.getCode());
                    return categoryMapper.categoryToCategoryDTO(category);
                });
    }

    @Override
    public void deleteById(Long id) {
        LOGGER.info("Request to delete category by id:{}", id);
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryDTO create(CategoryRequestDTO categoryDTO) {
        Category category = categoryRepository.save(categoryMapper.dtoToCategory(categoryDTO));
        LOGGER.info("created new category successfully");
        return categoryMapper.categoryToCategoryDTO(category);
    }
}
