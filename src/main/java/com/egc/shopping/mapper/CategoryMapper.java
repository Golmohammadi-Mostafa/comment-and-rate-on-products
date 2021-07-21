package com.egc.shopping.mapper;

import com.egc.shopping.domain.Category;
import com.egc.shopping.dto.CategoryDTO;
import com.egc.shopping.dto.CategoryRequestDTO;
import org.mapstruct.*;
import org.springframework.util.CollectionUtils;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mappings({
            @Mapping(target = "code", source = "code"),
            @Mapping(target = "title", source = "title")
    })
    CategoryDTO categoryToCategoryDTO(Category entity);

    @Mappings({
            @Mapping(target = "code", source = "code"),
            @Mapping(target = "title", source = "title")
    })
    Category categoryDtoToCategory(CategoryDTO dto);

    Category dtoToCategory(CategoryRequestDTO dto);

    Set<CategoryDTO> map(Set<Category> category);

    @AfterMapping
    default void calledAfter(Category category, @MappingTarget CategoryDTO dto) {

        if (!CollectionUtils.isEmpty(category.getProducts())) {
            dto.setProducts(category.getProducts());
        }
    }
}
