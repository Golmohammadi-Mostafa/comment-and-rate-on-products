package com.egc.shopping.dto;

import com.egc.shopping.domain.Product;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDTO implements Serializable {

    private Long id;
    private String code;
    private String title;
    private Set<Product> products = new HashSet<>();

    public CategoryDTO(Long id, String code, String title, Set<Product> products) {
        this.id = id;
        this.code = code;
        this.title = title;
        this.products = products;
    }

    public CategoryDTO() {
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }


    @Override
    public String toString() {
        return "CategoryDTO{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", title='" + title + '\'' +
                ", products=" + products +
                '}';
    }
}
