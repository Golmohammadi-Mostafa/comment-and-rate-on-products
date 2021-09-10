package com.egc.shopping.service;

import com.egc.shopping.domain.Role;
import com.egc.shopping.enums.RoleType;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findByName(RoleType roleName);
}