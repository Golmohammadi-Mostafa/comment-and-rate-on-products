package com.egc.shopping.domain;

import com.egc.shopping.enums.RatingLevel;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "RATE")
public class Rate implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RatingLevel ratingLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usert_id", nullable = false)
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RatingLevel getRatingLevel() {
        return ratingLevel;
    }

    public void setRatingLevel(RatingLevel ratingLevel) {
        this.ratingLevel = ratingLevel;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
