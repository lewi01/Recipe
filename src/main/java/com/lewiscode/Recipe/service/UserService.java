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


@Service
public class UserService  implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw  new UsernameNotFoundException("Not found" + username);
        }
        return new MyUserDetails(user);
    }
    public User saveUser(User user){
        User user1 = userRepository.findByEmail(user.getEmail());
        if (user1 != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return userRepository.save(user);
    }
    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
