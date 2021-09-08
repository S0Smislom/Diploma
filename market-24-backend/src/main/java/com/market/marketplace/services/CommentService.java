package com.market.marketplace.services;

import com.market.marketplace.exceptions.CommentNotFoundException;
import com.market.marketplace.models.Comment;
import com.market.marketplace.repositories.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CommentService {
    private final CommentRepo commentRepo;

    @Autowired
    public CommentService(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    public Comment addComment(Comment comment){
        return commentRepo.save(comment);
    }

    public List<Comment> findAllComments(){
        return commentRepo.findAll();
    }

    public Comment updateComment(Comment comment){
        return commentRepo.save(comment);
    }

    public void deleteComment(Long id){
        commentRepo.deleteCommentById(id);
    }

    public List<Comment> getProductComments(Long productId){
       return commentRepo.findCommentsByProductId(productId)
               .orElseThrow(()->new CommentNotFoundException("There are no comments for "+productId+" product"));
    }

    public Comment findCommentById(Long id){
        return commentRepo.findCommentById(id)
                .orElseThrow(()->new CommentNotFoundException("Comment not found with id: "+id));
    }
}
