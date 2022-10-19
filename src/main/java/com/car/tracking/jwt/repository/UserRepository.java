package com.car.tracking.jwt.repository;

import com.car.tracking.jwt.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    User findByUserName(String userName);

}
