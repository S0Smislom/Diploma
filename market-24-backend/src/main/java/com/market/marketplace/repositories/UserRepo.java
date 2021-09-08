package com.market.marketplace.repositories;


import com.market.marketplace.models.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
//import org.springframework.security.core.userdetails.User;


public interface UserRepo extends JpaRepository<MyUser,Integer> {
    Optional<MyUser> findUserByUsername(String username);
    Boolean existsByUsername(String username);
    Optional<MyUser> findUserById(Long id);
    void deleteUserById(Long id);
}
