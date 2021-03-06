package com.egc.shopping.repository;

import com.egc.shopping.domain.Role;
import com.egc.shopping.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleType roleName);

}
