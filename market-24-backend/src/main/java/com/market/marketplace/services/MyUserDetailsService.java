package com.market.marketplace.services;

import com.market.marketplace.models.MyUser;
import com.market.marketplace.models.MyUserDetails;
import com.market.marketplace.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;
    @Override
    public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        MyUser user = this.userRepo.findUserByUsername(username);
//        return new User(user.getUsername(),user.getPassword(),new ArrayList<>());
        MyUser user = userRepo.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        return MyUserDetails.build(user);
    }
}
