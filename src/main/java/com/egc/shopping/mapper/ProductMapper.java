package com.egc.shopping.mapper;

import com.egc.shopping.domain.Product;
import com.egc.shopping.dto.ProductDTO;
import com.egc.shopping.dto.ProductRequestDTO;
import org.mapstruct.*;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface ProductMapper {



    Product dtoToEntity(ProductDTO dto);

    Product dtoToEntity(ProductRequestDTO dto);

    default   Set<ProductDTO>  map(Set<Product> products){
        Set<ProductDTO> productDTOS = new HashSet<>();
        products.forEach(p->{
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(p.getId());
            productDTO.setName(p.getName());
            productDTO.setPrice(p.getPrice());
//            productDTO.setCategory(p.getCategory());
//            productDTO.setComments(p.getComments());
//            productDTO.setRates(p.getRates());
            productDTOS.add(productDTO);
        });

        return productDTOS;
    }

    ProductDTO toDto(Product product);

    @AfterMapping
    default void calledAfter(Product p, @MappingTarget ProductDTO dto) {

        if (!CollectionUtils.isEmpty(p.getComments())) {
            dto.setComments(p.getComments());
        }
        if (!CollectionUtils.isEmpty(p.getRates())) {
            dto.setRates(p.getRates());
        }
        if (p.getCategory() != null) {
            dto.setCategory(p.getCategory());
        }
    }
}
