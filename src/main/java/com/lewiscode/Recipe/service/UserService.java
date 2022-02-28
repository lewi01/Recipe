package com.lewiscode.Recipe.service;

import com.lewiscode.Recipe.entity.MyUserDetails;
import com.lewiscode.Recipe.entity.User;
import com.lewiscode.Recipe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


@Service
public class UserService  implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);
        if (user.isEmpty()) {
            throw  new UsernameNotFoundException("Not found" + username);
        }
        return new MyUserDetails(user.get());
    }
    public void saveUser(User user){
        Optional<User> user1 = userRepository.findByEmail(user.getEmail());
        if (user1.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        user.setRoles("ROLE_USER");
        userRepository.save(user);
    }
    public Optional<User> getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
