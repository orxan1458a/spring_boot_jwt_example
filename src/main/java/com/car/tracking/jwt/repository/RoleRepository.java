package com.car.tracking.jwt.repository;

import com.car.tracking.jwt.entity.db.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Role findByRoleName(String roleName);

}
