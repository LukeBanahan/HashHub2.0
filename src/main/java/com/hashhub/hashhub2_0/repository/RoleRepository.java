package com.hashhub.hashhub2_0.repository;

import com.hashhub.hashhub2_0.models.Role;
import com.hashhub.hashhub2_0.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
