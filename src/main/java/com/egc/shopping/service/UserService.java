package com.egc.shopping.service;

import com.egc.shopping.dto.SingUpDTO;

import com.egc.shopping.dto.UserResponseDTO;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    String signIn(String username, String password);

    String signUp(SingUpDTO dto);

    void delete(String username);

    UserResponseDTO search(String username);

    UserResponseDTO whoAmI(HttpServletRequest req);

    String refresh(String username);
}
