package com.market.marketplace.repositories;

import com.market.marketplace.models.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SellerRepo extends JpaRepository<Seller, Integer> {
    Optional<Seller> findSellerByUsername(String username);

    Boolean existsByUsername(String username);
    void deleteSellerById(Long id);
    Optional<Seller> findSellerById(Long id);
}
