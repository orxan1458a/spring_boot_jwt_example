package com.car.tracking.jwt.repository;

import com.car.tracking.jwt.entity.db.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserName(String userName);

}
