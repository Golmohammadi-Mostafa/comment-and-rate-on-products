package com.egc.shopping;

import com.egc.shopping.domain.Role;
import com.egc.shopping.domain.User;
import com.egc.shopping.dto.SingUpDTO;
import com.egc.shopping.enums.RoleType;
import com.egc.shopping.mapper.UserMapper;
import com.egc.shopping.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    private final UserService userService;
    private final UserMapper userMapper;

    public Application(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(Application.class);
        Environment env = app.run(args).getEnvironment();
        String protocol = "http";
        if (env.getProperty("server.ssl.key-store") != null) {
            protocol = "https";
        }
        LOGGER.info(
                "\n----------------------------------------------------------\n\t"
                        + "Application '{}' is running! Access URLs:\n\t" + "Local: \t\t{}://localhost:{}\n\t"
                        + "External: \t{}://{}:{}\n\t"
                        + "Profile(s): \t{}\n----------------------------------------------------------",
                env.getProperty("spring.application.name"), protocol, env.getProperty("server.port"), protocol,
                InetAddress.getLocalHost().getHostAddress(), env.getProperty("server.port"), env.getActiveProfiles());
    }


    @Override
    public void run(String... params) {
        SingUpDTO admin = new SingUpDTO();
        admin.setUsername("admin");
        admin.setPassword("admin");
        Set<RoleType> adminRole = new HashSet();
        adminRole.add(RoleType.ROLE_ADMIN);
        admin.setRoleType(adminRole);

        userService.signUp(admin);

        SingUpDTO user1 = new SingUpDTO();
        user1.setUsername("USER_1");
        user1.setPassword("123");

        Set<RoleType> userRole = new HashSet();
        userRole.add(RoleType.ROLE_USER);
        user1.setRoleType(userRole);
        userService.signUp(user1);

        Set<RoleType> userRole2 = new HashSet();
        userRole2.add(RoleType.ROLE_USER);
        SingUpDTO user2 = new SingUpDTO();
        user2.setUsername("USER_2");
        user2.setPassword("123");
        user2.setRoleType(userRole2);

        userService.signUp(user2);
    }

}
