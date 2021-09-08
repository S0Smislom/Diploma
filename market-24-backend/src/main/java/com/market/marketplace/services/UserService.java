package com.market.marketplace.services;


import com.market.marketplace.models.MyUser;
import com.market.marketplace.repositories.UserRepo;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService{
    private UserRepo userRepo;
    UserService(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    public MyUser addUser(MyUser user){
        return userRepo.save(user);
    }

    public List<MyUser> findAll(){
        return userRepo.findAll();
    }

    public MyUser updateUser(MyUser user){
        return userRepo.save(user);
    }

    public Boolean existByName(String username){
        return userRepo.existsByUsername(username);
    }

    public void deleteUser(Long id){
        userRepo.deleteUserById(id);
    }

    public MyUser findUserByUsername(String username){
        return userRepo.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

    }
    public MyUser findUserById(Long id){
        return userRepo.findUserById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: "+ id));
    }

}
