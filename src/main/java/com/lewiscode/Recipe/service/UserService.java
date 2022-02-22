package com.lewiscode.Recipe.service;

import com.lewiscode.Recipe.entity.MyUserDetails;
import com.lewiscode.Recipe.entity.User;
import com.lewiscode.Recipe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService  implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);
        user.orElseThrow(()->new UsernameNotFoundException("Not found"+ username));
        return user.map(MyUserDetails::new).get();
    }
    public User saveUser(User user){
        return userRepository.save(user);
    }
    public Optional<User> getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
