package com.egc.shopping.configuration;

import com.egc.shopping.domain.Role;
import com.egc.shopping.dto.SingUpDTO;
import com.egc.shopping.enums.RoleType;
import com.egc.shopping.repository.RoleRepository;
import com.egc.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class UserRoleConfig {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    public void init() {
        Role adminRole = new Role();
        Role userRole = new Role();
        adminRole.setName(RoleType.ROLE_ADMIN);
        userRole.setName(RoleType.ROLE_USER);
        roleRepository.deleteAll();
        roleRepository.save(adminRole);
        roleRepository.save(userRole);

        SingUpDTO admin = new SingUpDTO();
        admin.setUsername("admin");
        admin.setPassword("123");
        Set<RoleType> adminRoles = new HashSet();
        adminRoles.add(RoleType.ROLE_ADMIN);
        adminRoles.add(RoleType.ROLE_USER);
        admin.setRoleType(adminRoles);

        SingUpDTO user1 = new SingUpDTO();
        user1.setUsername("user");
        user1.setPassword("123");

        Set<RoleType> userRoles = new HashSet();
        userRoles.add(RoleType.ROLE_USER);
        user1.setRoleType(userRoles);

        userService.deleteAllUsers();
        userService.signUp(user1);
        userService.signUp(admin);
    }

    @Bean(initMethod = "init")
    public UserRoleConfig getMailService() {
        return new UserRoleConfig();
    }
}