package com.egc.shopping.service.impl;

import com.egc.shopping.domain.Product;
import com.egc.shopping.dto.ProductDTO;
import com.egc.shopping.dto.ProductRequestDTO;
import com.egc.shopping.exception.CustomException;
import com.egc.shopping.mapper.ProductMapper;
import com.egc.shopping.repository.ProductRepository;
import com.egc.shopping.service.ProductService;
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
public class ProductServiceImpl implements ProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }


    @Override
    public Set<ProductDTO> findAll() {
        LOGGER.info("Request to get all products....");
        List<Product> all = productRepository.findAll();
        if (CollectionUtils.isEmpty(all)) {
            throw new CustomException("products is empty...", HttpStatus.NOT_FOUND);
        }
        return productMapper.map(new HashSet<>(all));
    }

    @Override
    public ProductDTO getByCode(String name) {
        Optional<Product> byName = productRepository.findByName(name);
        if (!byName.isPresent()) {
            throw new CustomException("product doesn't exist", HttpStatus.NOT_FOUND);
        }
        return productMapper.toDto(byName.get());
    }

    @Override
    public ProductDTO getById(Long id) {
        LOGGER.info("Request to get product by id:{}", id);
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()) {
            throw new CustomException("product doesn't exist", HttpStatus.NOT_FOUND);
        }
        return productMapper.toDto(product.get());
    }

    @Override
    public Set<ProductDTO> getByPrice(Double price) {
        LOGGER.info("Request to get product by price:{}", price);
        List<Product> allByPrice = productRepository.findAllByPrice(price);
        if (CollectionUtils.isEmpty(allByPrice)) {
            throw new CustomException("product doesn't exist", HttpStatus.NOT_FOUND);
        }
        return productMapper.map(new HashSet<>(allByPrice));
    }

    @Override
    public ProductDTO create(ProductRequestDTO productDTO) {
        if (productDTO.getId() != null) {
            Optional<Product> product = productRepository.findById(productDTO.getId());
            if (product.isPresent()) {
                throw new CustomException("product already exist...", HttpStatus.BAD_REQUEST);
            }
        }
        Product product = productRepository.save(productMapper.dtoToEntity(productDTO));
        return productMapper.toDto(product);
    }

    @Override
    public ProductDTO update(ProductRequestDTO productDTO, Long id) {
        LOGGER.info("Request to update product by id:{}, dto:{}", id, productDTO);
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            Product newProduct = product.get();
            newProduct.setPrice(productDTO.getPrice());
            newProduct.setName(productDTO.getName());
            productRepository.save(newProduct);
            return productMapper.toDto(newProduct);
        } else {
            Product p = productRepository.save(productMapper.dtoToEntity(productDTO));
            return productMapper.toDto(p);
        }


    }

    @Override
    public void deleteById(Long id) {
        LOGGER.info("Request to delete product by id:{}", id);
        productRepository.deleteById(id);
    }

    @Override
    public Set<ProductDTO> getAllByCategory(Long cateGoryId) {
        LOGGER.info("Request to get all products of a category by id:{}", cateGoryId);
        List<Product> products = productRepository.findAllByCategory_Id(cateGoryId);
        if (CollectionUtils.isEmpty(products)){
            throw new CustomException("products by category-id " + cateGoryId + " doesn't exist", HttpStatus.NOT_FOUND);
        }
       return productMapper.map(new HashSet<>(products));
    }

    @Override
    public void updateAllProductsOfCategory(Double price, String categoryCode) {
        LOGGER.info("Request to update price of all products with category code :{}", categoryCode);
        List<Product> products = productRepository.findAllByCategory_Code(categoryCode);
        if (CollectionUtils.isEmpty(products)){
            throw new CustomException("products by category-code " + categoryCode + " doesn't exist", HttpStatus.NOT_FOUND);
        }
        products.forEach(p -> p.setPrice(price));
        productRepository.saveAll(products);
    }


}
