package com.market.marketplace.controllers;

import com.market.marketplace.models.*;
import com.market.marketplace.services.SellerDetailsService;
import com.market.marketplace.services.SellerService;
import com.market.marketplace.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SellerAuthenticationResource {
    @Autowired
    private SellerDetailsService sellerDetailsService;
    @Autowired
    private SellerService sellerService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/seller_signup")
    public ResponseEntity<?> signup(@RequestBody Seller seller){
        if(sellerService.existByName(seller.getUsername())){
            return ResponseEntity.badRequest().body("Error: Username is already taken.");
        }
        seller.setPassword(encoder.encode(seller.getPassword()));
        sellerService.addSeller(seller);
        return new ResponseEntity<>("User successfully created!", HttpStatus.CREATED);
    }

    @PostMapping("/seller_login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest seller)throws Exception{

        System.out.println(seller.getUsername() + seller.getPassword());

        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(seller.getUsername(),seller.getPassword())
            );
        }catch (BadCredentialsException e){
            throw new Exception("Incorrect username or password!", e);
        }

        final SellerDetails sellerDetails = sellerDetailsService.loadUserByUsername(seller.getUsername());
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("username", sellerDetails.getUsername());
//        claims.put("contactInfo",sellerDetails.getContactInfo());
//        claims.put("inn", sellerDetails.getINN());
        claims.put("role", sellerDetails.getAuthorities());
        final String jwtToken = jwtTokenUtil.generateToken(sellerDetails,claims);
        final AuthenticationResponse response = new AuthenticationResponse(jwtToken);
        return new ResponseEntity<>(response,HttpStatus.CREATED);

    }

    @PutMapping("/seller_repassword")
    public ResponseEntity<?> resetSellerPassword(@RequestBody ChangePasswordRequest changePasswordRequest) throws Exception {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(changePasswordRequest.getUsername(),changePasswordRequest.getOldPassword())
            );
        }catch (BadCredentialsException e){
            throw new Exception("Incorrect username or password!", e);
        }
        Seller seller = sellerService.findSellerByUsername(changePasswordRequest.getUsername());

        seller.setPassword(new BCryptPasswordEncoder().encode(changePasswordRequest.getNewPassword()));
        sellerService.updateSeller(seller);
        return new ResponseEntity<>("Password successfully changed!", HttpStatus.OK);
    }
}
