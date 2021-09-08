package com.market.marketplace.services;

import com.market.marketplace.exceptions.SellerNotFoundException;
import com.market.marketplace.models.Seller;
import com.market.marketplace.repositories.SellerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class SellerService {
    @Autowired
    private SellerRepo sellerRepo;

    public Seller addSeller(Seller seller){
        return sellerRepo.save(seller);
    }

    public List<Seller> findAll(){
        return sellerRepo.findAll();
    }

    public Boolean existByName(String username){
        return sellerRepo.existsByUsername(username);
    }

    public Seller updateSeller(Seller seller){
        return sellerRepo.save(seller);
    }

    public void deleteSeller(Long id){
        sellerRepo.deleteSellerById(id);
    }

    public Seller findSellerByUsername(String username){
        return sellerRepo.findSellerByUsername(username)
                .orElseThrow(() -> new SellerNotFoundException("Seller Not Found with username: " + username));

    }

    public Seller findSellerById(Long id){
        return sellerRepo.findSellerById(id)
                .orElseThrow(() -> new SellerNotFoundException("Seller Not Found with id: " + id));
    }
}
