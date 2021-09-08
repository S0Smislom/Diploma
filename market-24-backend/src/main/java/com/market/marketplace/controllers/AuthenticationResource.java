package com.market.marketplace.controllers;


import com.market.marketplace.models.*;
import com.market.marketplace.repositories.RoleRepo;
import com.market.marketplace.services.MyUserDetailsService;
import com.market.marketplace.services.UserService;
import com.market.marketplace.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationResource {
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

   @Autowired
   private JwtUtil jwtTokenUtil;

   @Autowired
   private PasswordEncoder encoder;

   @Autowired
   private RoleRepo roleRepo;


    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        try{
//            System.out.println(authenticationRequest.getUsername()+" "+authenticationRequest.getPassword());
//
//            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword());
//
//            System.out.println(usernamePasswordAuthenticationToken);
//
//            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
//            System.out.println("auth passed");

//            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//            System.out.println(encoder.matches(authenticationRequest.getPassword(),));
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword())
            );

        }catch (BadCredentialsException e){
            throw new Exception("Incorrect username or password!", e);
        }
        final MyUserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
//        Setup claims
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("id", userDetails.getId());
        claims.put("username",userDetails.getUsername());
        claims.put("role", userDetails.getAuthorities());
//        claims.put("name",userDetails.getName());
//        claims.put("surname",userDetails.getSurname());
//        claims.put("email",userDetails.getEmail());
//        claims.put("phone",userDetails.getPhone());

        final String jwtToken = jwtTokenUtil.generateToken(userDetails,claims);
        final AuthenticationResponse response = new AuthenticationResponse(jwtToken);
        return new ResponseEntity<>(response,HttpStatus.CREATED);

//        final MyUserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
//        System.out.println(userDetails.toString());
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        if(encoder.matches(authenticationRequest.getPassword(),userDetails.getPassword())){
//            final String jwtToken = jwtTokenUtil.generateToken(userDetails);
//            final AuthenticationResponse response = new AuthenticationResponse(jwtToken);
//            return new ResponseEntity<>(response,HttpStatus.CREATED);
//        }
//
//        return new ResponseEntity<>("Incorrect username or password!",HttpStatus.BAD_REQUEST);

    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignupRequest signupRequest){
        if(userService.existByName(signupRequest.getUsername())){
            return ResponseEntity.badRequest().body("Error: Username is already taken.");
        }

//        System.out.println(signupRequest.getUsername()+ " " + new BCryptPasswordEncoder().encode(signupRequest.getPassword())+ " " + signupRequest.getEmail()+" "+ signupRequest.getName()+" "+ signupRequest.getSurname()+" "+ signupRequest.getPhone());

        MyUser user = new MyUser();
        user.setUsername(signupRequest.getUsername());
//        user.setPassword(encoder.encode(signupRequest.getPassword()));

        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));

        user.setName(signupRequest.getName());
        user.setSurname(signupRequest.getSurname());
        user.setEmail(signupRequest.getEmail());
        user.setPhone(signupRequest.getPhone());


        Set<String> strRoles = signupRequest.getRoles();
        Set<Role> roles = new HashSet<>();
//        System.out.println(strRoles);
        if (strRoles == null) {
            Role userRole = roleRepo.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepo.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepo.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepo.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userService.addUser(user);
        return new ResponseEntity<>("User successfully created",HttpStatus.CREATED);
    }

    @PutMapping("/repassword")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> resetPassword(@RequestBody ChangePasswordRequest changePasswordRequest) throws Exception {
        try{
//
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(changePasswordRequest.getUsername(),changePasswordRequest.getOldPassword())
            );

        }catch (BadCredentialsException e){
            throw new Exception("Incorrect username or password!", e);
        }

        MyUser user = userService.findUserByUsername(changePasswordRequest.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(changePasswordRequest.getNewPassword()));
        userService.updateUser(user);
        return new ResponseEntity<>("Password successfully changed!", HttpStatus.OK);
    }

}
