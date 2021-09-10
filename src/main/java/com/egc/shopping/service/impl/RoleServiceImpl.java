package com.egc.shopping.service.impl;

import com.egc.shopping.domain.Role;
import com.egc.shopping.enums.RoleType;
import com.egc.shopping.repository.RoleRepository;
import com.egc.shopping.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<Role> findByName(RoleType roleName) {
        return roleRepository.findByName(roleName);
    }
}