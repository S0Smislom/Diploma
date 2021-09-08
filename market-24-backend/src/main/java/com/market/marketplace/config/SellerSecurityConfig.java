package com.market.marketplace.config;

import com.market.marketplace.filters.JwtRequestFilter;
import com.market.marketplace.services.SellerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Controller;

//
//@EnableWebSecurity
//@Controller
//public class SellerSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private SellerDetailsService sellerDetailsService;
//    @Autowired
//    private JwtRequestFilter jwtRequestFilter;
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(sellerDetailsService);
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http // all other requests handled here
//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic();
//    }
//}
