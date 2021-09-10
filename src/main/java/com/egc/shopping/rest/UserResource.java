package com.egc.shopping.rest;

import com.egc.shopping.dto.JwtTokenDTO;
import com.egc.shopping.dto.SingUpDTO;
import com.egc.shopping.dto.UserResponseDTO;
import com.egc.shopping.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/users")
@Api(tags = "users")
public class UserResource {


    private final UserService userService;


    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtTokenDTO> login(@ApiParam("Username") @RequestParam String username,
                                             @ApiParam("Password") @RequestParam String password) {
        return ResponseEntity.ok(userService.signIn(username, password));
    }

    @PostMapping("/signup")
    public ResponseEntity<JwtTokenDTO> signUp(@ApiParam("Signup User") @RequestBody SingUpDTO user) {
        return ResponseEntity.ok(userService.signUp(user));
    }

    @DeleteMapping(value = "/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> delete(@ApiParam("Username") @PathVariable String username) {
        userService.delete(username);
        return ResponseEntity.ok(username);
    }

    @GetMapping(value = "/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserResponseDTO> search(@ApiParam("Username") @PathVariable String username) {
        return ResponseEntity.ok(userService.search(username));
    }

    @GetMapping(value = "/me")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<UserResponseDTO> whoAmI(HttpServletRequest req) {
        return ResponseEntity.ok(userService.whoAmI(req));
    }

    @GetMapping("/refresh")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public JwtTokenDTO refresh(HttpServletRequest req) {
        return userService.refresh(req.getRemoteUser());
    }

}
