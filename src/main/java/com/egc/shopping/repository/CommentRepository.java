package com.egc.shopping.repository;

import com.egc.shopping.domain.Comment;
import com.egc.shopping.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findCommentsByProduct(Product product);

}
