package com.market.marketplace.repositories;

import com.market.marketplace.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long> {

    void deleteProductById(Long id);

    Optional<Product> findProductById(Long id);

    @Query("SELECT p from Product p where p.seller.username = :username")
    List<Product> findProductByUsername(@Param("username")String username);
}
