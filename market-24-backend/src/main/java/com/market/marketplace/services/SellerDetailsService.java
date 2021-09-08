package com.market.marketplace.services;

import com.market.marketplace.exceptions.SellerNotFoundException;
import com.market.marketplace.models.Seller;
import com.market.marketplace.models.SellerDetails;
import com.market.marketplace.repositories.SellerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SellerDetailsService implements UserDetailsService {

    @Autowired
    SellerRepo sellerRepo;

    @Override
    public SellerDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Seller seller = sellerRepo.findSellerByUsername(username)
                .orElseThrow(() -> new SellerNotFoundException("Seller Not Found with username: " + username));
        return SellerDetails.build(seller);
    }
}
