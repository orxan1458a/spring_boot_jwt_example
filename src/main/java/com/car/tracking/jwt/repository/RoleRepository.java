package com.car.tracking.jwt.repository;

import com.car.tracking.jwt.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, String> {
    Role findByRoleName(String roleName);

}
