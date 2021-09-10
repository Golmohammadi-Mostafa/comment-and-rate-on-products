package com.egc.shopping.service;

import com.egc.shopping.dto.JwtTokenDTO;
import com.egc.shopping.dto.SingUpDTO;
import com.egc.shopping.dto.UserResponseDTO;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    JwtTokenDTO signIn(String username, String password);

    JwtTokenDTO signUp(SingUpDTO dto);

    void delete(String username);

    UserResponseDTO search(String username);

    UserResponseDTO whoAmI(HttpServletRequest req);

    JwtTokenDTO refresh(String username);

    void deleteAllUsers();
}
