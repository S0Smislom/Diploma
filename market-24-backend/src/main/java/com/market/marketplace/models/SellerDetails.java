package com.market.marketplace.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class SellerDetails implements UserDetails {


    private static final long serialVersionUID = 1L;
    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    private Long INN;
    private String contactInfo;

    public SellerDetails(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities, Long INN, String contactInfo) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.INN = INN;
        this.contactInfo = contactInfo;
    }

    public static SellerDetails build(Seller seller){
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(ERole.ROLE_SELLER.name()));
       return new SellerDetails(
               seller.getId(),
               seller.getUsername(),
               seller.getPassword(),
               list,
               seller.getINN(),
               seller.getContactInfo()

       );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getINN() {
        return INN;
    }

    public String getContactInfo() {
        return contactInfo;
    }
}
