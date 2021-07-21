package com.egc.shopping.dto;

import com.egc.shopping.domain.Category;
import com.egc.shopping.domain.Comment;
import com.egc.shopping.domain.Rate;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO implements Serializable {

    private Long id;

    private String name;

    private Double price;

    private Category category;

    private List<Comment> comments = new ArrayList<>();

    private List<Rate> rates = new ArrayList<>();

    public ProductDTO() {
    }

    public ProductDTO(Long id, String name, Double price, Category category, List<Comment> comments, List<Rate> rates) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.comments = comments;
        this.rates = rates;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", comments=" + comments +
                ", rates=" + rates +
                '}';
    }
}
