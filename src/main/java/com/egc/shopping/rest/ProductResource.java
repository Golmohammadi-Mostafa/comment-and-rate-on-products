package com.egc.shopping.rest;


import com.egc.shopping.dto.ProductDTO;
import com.egc.shopping.dto.ProductRequestDTO;
import com.egc.shopping.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/products")
public class ProductResource {
    private final ProductService productService;

    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @GetMapping
    public ResponseEntity<Set<ProductDTO>> getAll() {
        return ResponseEntity.ok(productService.findAll());
    }


    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @GetMapping("/category/{id}")
    public ResponseEntity<Set<ProductDTO>> getAllByCategory(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getAllByCategory(id));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/category/{code}")
    public ResponseEntity<HttpStatus> updateAllProductsOfCategory(@RequestParam Double price, @PathVariable String code) {
        productService.updateAllProductsOfCategory(price, code);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{name}")
    public ResponseEntity<ProductDTO> getByCode(@PathVariable String name) {
        return ResponseEntity.ok(productService.getByCode(name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getById(id));
    }

    @GetMapping("/{price}")
    public ResponseEntity<Set<ProductDTO>> getById(@PathVariable Double price) {
        return ResponseEntity.ok(productService.getByPrice(price));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestBody ProductRequestDTO productDTO) {
        return ResponseEntity.ok(productService.create(productDTO));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@RequestBody ProductRequestDTO productDTO, @PathVariable Long id) {
        return ResponseEntity.ok(productService.update(productDTO, id));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
