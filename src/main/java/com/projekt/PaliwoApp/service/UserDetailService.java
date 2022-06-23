package com.projekt.PaliwoApp.service;

import com.projekt.PaliwoApp.config.UserPrincipal;
import com.projekt.PaliwoApp.model.User;
import com.projekt.PaliwoApp.repository.UserRepository;
import com.projekt.PaliwoApp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByEmail(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found!");
        }
        return new UserPrincipal(user);
    }
}
