package com.egc.shopping.service.impl;

import com.egc.shopping.domain.Role;
import com.egc.shopping.domain.User;
import com.egc.shopping.dto.SingUpDTO;
import com.egc.shopping.dto.UserResponseDTO;
import com.egc.shopping.enums.RoleType;
import com.egc.shopping.enums.UserStatus;
import com.egc.shopping.exception.CustomException;
import com.egc.shopping.mapper.UserMapper;
import com.egc.shopping.repository.UserRepository;
import com.egc.shopping.security.JwtTokenProvider;
import com.egc.shopping.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;


    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
        this.userMapper = userMapper;
    }

    @Override
    public String signIn(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles());
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public String signUp(SingUpDTO singUpDTO) {


        if (!userRepository.existsByUsername(singUpDTO.getUsername())) {
            User user = new User();

            Set<RoleType> roleType = singUpDTO.getRoleType();
            Set<Role> roles = new HashSet<>();
            roleType.forEach(r -> {
                Role role = new Role();
                role.setName(r);
                roles.add(role);
            });
            user.setUsername(singUpDTO.getUsername());
            user.setRoles(roles);
            user.setPassword(passwordEncoder.encode(singUpDTO.getPassword()));
            user.setStatus(UserStatus.UNBLOCKED);
            User savedUser = userRepository.save(user);
            return jwtTokenProvider.createToken(savedUser.getUsername(), savedUser.getRoles());
        } else {
            throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public void delete(String username) {
        userRepository.deleteByUsername(username);
    }

    @Override
    public UserResponseDTO search(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
        }
        return userMapper.userToUserResponseDTO(user);
    }

    @Override
    public UserResponseDTO whoAmI(HttpServletRequest req) {
        User user = userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
        return userMapper.userToUserResponseDTO(user);
    }

    @Override
    public String refresh(String username) {
        return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles());
    }

}
