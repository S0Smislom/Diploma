package com.market.marketplace.services;

import com.market.marketplace.exceptions.ProductNotFoundException;
import com.market.marketplace.models.Product;
import com.market.marketplace.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepo productRepo;
    @Autowired
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Product addProduct(Product product){
//        product.setDescription(UUID.randomUUID().toString());
        return productRepo.save(product);
    }
    public List<Product> findAllProducts(){
        return productRepo.findAll();
    }
    public Product updateProduct(Product product){
        return productRepo.save(product);
    }

    public Product findProductById(Long id){
        return productRepo.findProductById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product by id " + id + " was not found"));
    }

    public void deleteProduct(Long id){
        productRepo.deleteProductById(id);
    }

    public List<Product> findProductByUsername(String username){
        return productRepo.findProductByUsername(username);
    }
}
