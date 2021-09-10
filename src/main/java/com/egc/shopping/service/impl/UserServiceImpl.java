package com.egc.shopping.service.impl;

import com.egc.shopping.domain.Role;
import com.egc.shopping.domain.User;
import com.egc.shopping.dto.JwtTokenDTO;
import com.egc.shopping.dto.SingUpDTO;
import com.egc.shopping.dto.UserResponseDTO;
import com.egc.shopping.enums.RoleType;
import com.egc.shopping.enums.UserStatus;
import com.egc.shopping.exception.CustomException;
import com.egc.shopping.mapper.UserMapper;
import com.egc.shopping.repository.UserRepository;
import com.egc.shopping.security.JwtTokenProvider;
import com.egc.shopping.service.RoleService;
import com.egc.shopping.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;
    private final RoleService roleService;


    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager, UserMapper userMapper, RoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
        this.userMapper = userMapper;
        this.roleService = roleService;
    }

    @Override
    public JwtTokenDTO signIn(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return new JwtTokenDTO(jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles()));
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public JwtTokenDTO signUp(SingUpDTO singUpDTO) {


        if (!userRepository.existsByUsername(singUpDTO.getUsername())) {
            User user = new User();

            Set<RoleType> roleType = singUpDTO.getRoleType();
            Set<Role> roles = new HashSet<>();
            user.setUsername(singUpDTO.getUsername());
            user.setRoles(roles);
            user.setPassword(passwordEncoder.encode(singUpDTO.getPassword()));
            user.setStatus(UserStatus.UNBLOCKED);
            userRepository.save(user);

            roleType.forEach(r -> {
                Optional<Role> role = roleService.findByName(r);
                role.ifPresent(roles::add);
            });

            User byUsername = userRepository.findByUsername(singUpDTO.getUsername());
            byUsername.setRoles(roles);
            User savedUser = userRepository.save(byUsername);

            String token = jwtTokenProvider.createToken(savedUser.getUsername(), savedUser.getRoles());
            return new JwtTokenDTO(token);
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
    public JwtTokenDTO refresh(String username) {
        return new JwtTokenDTO(jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles()));
    }

    @Override
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

}
