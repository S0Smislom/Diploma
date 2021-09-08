package com.market.marketplace.controllers;

import com.market.marketplace.models.ChangePasswordRequest;
import com.market.marketplace.models.Seller;
import com.market.marketplace.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
@CrossOrigin(origins = "http://localhost:4200")
public class SellerResource {

    @Autowired
    private SellerService sellerService;

    @PutMapping("/update")
    @Transactional
    public ResponseEntity<?> updateSeller(@RequestBody Seller seller){
//        System.out.println(seller.getUsername()+" "+seller.getINN()+" ");
//        Seller tempSeller = sellerService.findSellerByUsername(seller.getUsername());
//        seller.setProducts(tempSeller.getProducts());
        Seller updateSeller = sellerService.updateSeller(seller);

        return new ResponseEntity<>(updateSeller, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Seller>> getAllSellers(){
        return new ResponseEntity<>(sellerService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<?> deleteSeller(@PathVariable Long id){
        sellerService.deleteSeller(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/find/{username}")
    public ResponseEntity<Seller> getSellerByUsername(@PathVariable String username){
        Seller seller = sellerService.findSellerByUsername(username);
        return new ResponseEntity<>(seller,HttpStatus.OK);
    }


}
