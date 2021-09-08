package com.market.marketplace.repositories;

import com.market.marketplace.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentRepo extends JpaRepository<Comment, Long> {
    Optional<Comment> findCommentById(Long id);
    void deleteCommentById(Long id);

    @Query("select c from Comment c where c.productID = :id")
    Optional<List<Comment>> findCommentsByProductId(@Param("id")Long id);

//    @Query("select c from Comment c where c.userID = :id")
//    Optional<List<Comment>> findCommentsByUserId(@Param("id")Long id);
//    Optional<Comment> findCommentById(Long id);
}
