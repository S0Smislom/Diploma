package com.market.marketplace.controllers;

import com.market.marketplace.models.Comment;
import com.market.marketplace.models.CommentRequest;
import com.market.marketplace.models.Product;
import com.market.marketplace.models.ProductRequest;
import com.market.marketplace.repositories.SellerRepo;
import com.market.marketplace.services.CommentService;
import com.market.marketplace.services.ProductService;
import com.market.marketplace.services.SellerService;
import com.market.marketplace.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/product")
public class ProductResource {
    private final ProductService productService;
    private final CommentService commentService;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private UserService userService;


    public ProductResource(ProductService productService, CommentService commentService) {
        this.productService = productService;

        this.commentService = commentService;
    }

    @GetMapping("/all")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = productService.findAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


    @GetMapping("/find/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id){
        Product product = productService.findProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    @PostMapping("/add")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> addProduct(@RequestBody ProductRequest product){
        Product newProduct  = new Product();
        newProduct.setName(product.getName());
        newProduct.setPrice(product.getPrice());
        newProduct.setCount(product.getCount());
        newProduct.setDescription(product.getDescription());
        newProduct.setImgUrl(product.getImgUrl());
        newProduct.setRating(5);
        newProduct.setCity(product.getCity());
        newProduct.setRegion(product.getRegion());
        try{
            newProduct.setSeller(sellerService.findSellerById(product.getSeller()));

        }catch (Exception e){
            return new ResponseEntity<>("Seller not found!", HttpStatus.BAD_REQUEST);
        }

        productService.addProduct(newProduct);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }
    @PutMapping("/update")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> updateProduct(@RequestBody ProductRequest product){
        Product newProduct  = new Product();
        newProduct.setId(product.getId());
        newProduct.setName(product.getName());
        newProduct.setPrice(product.getPrice());
        newProduct.setCount(product.getCount());
        newProduct.setDescription(product.getDescription());
        newProduct.setImgUrl(product.getImgUrl());
        newProduct.setRating(5);
        newProduct.setCity(product.getCity());
        newProduct.setRegion(product.getRegion());
        try{
            newProduct.setSeller(sellerService.findSellerById(product.getSeller()));

        }catch (Exception e){
            return new ResponseEntity<>("Seller not found!", HttpStatus.BAD_REQUEST);
        }

//        productService.addProduct(newProduct);
//        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
        Product updateProduct = productService.updateProduct(newProduct);
        return new ResponseEntity<>(updateProduct, HttpStatus.OK);
    }
    @Transactional
    @DeleteMapping("/delete/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id){
        System.out.println(id);
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/find/seller_username/{username}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Product>> findProductBySellerId(@PathVariable("username") String username){
        List<Product> products = productService.findProductByUsername(username);
//        System.out.println(products);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

//    @GetMapping("/comments/{id}")
//    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/comment/all")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Comment>> getAllComments(){
        List<Comment> comments = commentService.findAllComments();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/comment/find/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Comment>> getProductComments(@PathVariable("id") Long productId){
        List<Comment> comments = commentService.getProductComments(productId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }


    @Transactional
    @PostMapping("/comment/add")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Comment> addComment(@RequestBody CommentRequest comment){
        Comment newComment = new Comment();
        newComment.setId(comment.getId());
        newComment.setProductID(comment.getProductID());
        newComment.setUser(userService.findUserById(comment.getUserID()));
        newComment.setText(comment.getText());
        newComment.setRate(comment.getRate());
        Product product = productService.findProductById(comment.getProductID());
        product.setRating((product.getRating() + comment.getRate())/2);
        productService.updateProduct(product);
//        Comment newComment = commentService.addComment(comment);
        return new ResponseEntity<>(commentService.addComment(newComment), HttpStatus.CREATED);
    }

    @Transactional
    @DeleteMapping("/comment/delete/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> deleteComment(@PathVariable("id") Long id){
        Comment comment = commentService.findCommentById(id);
        Product product = productService.findProductById(comment.getProductID());
        product.setRating(product.getRating() * 2 -comment.getRate());
        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
    