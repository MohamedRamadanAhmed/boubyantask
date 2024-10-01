package com.boubyan.boubyantask.service;

import com.boubyan.boubyantask.entity.User;
import com.boubyan.boubyantask.mapper.UserMapper;
import com.boubyan.boubyantask.model.dto.UserDTO;
import com.boubyan.boubyantask.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    private final UserMapper userMapper;

    public UserDTO createUser(UserDTO userDTO) {
        try {
            User user = userMapper.mapUserDTOToUser(userDTO);
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return userDTO;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findUserByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            List<String> roles = new ArrayList<>();
            roles.add("USER");
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getEmail())
                    .password(user.getPassword())
                    .roles(roles.toArray(new String[0]))
                    .build();
        } else {
            throw new BadCredentialsException("invalid credentials");
        }
    }

}
