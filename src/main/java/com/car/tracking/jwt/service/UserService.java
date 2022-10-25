package com.car.tracking.jwt.service;

import com.car.tracking.jwt.repository.RoleRepository;
import com.car.tracking.jwt.repository.UserRepository;
import com.car.tracking.jwt.entity.db.Role;
import com.car.tracking.jwt.entity.db.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void initRoleAndUser() {


        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
//        roleRepository.save(adminRole);
////
//        Role userRole = new Role();
//        userRole.setRoleName("User");
//        userRole.setRoleDescription("Default role for newly created record");
//        roleRepository.save(userRole);
//
//        User adminUser = new User();
//        adminUser.setUserName("admin");
//        adminUser.setPassword(getEncodedPassword("admin"));
//        adminUser.setFirstName("Orxan");
//        adminUser.setLastName("Mansurov");
//        Set<Role> adminRoles = new HashSet<>();
//        adminRoles.add(adminRole);
//        adminUser.setRole(adminRoles);
//        userRepository.save(adminUser);

//        User user = new User();
//        user.setUserName("raj123");
//        user.setUserPassword(getEncodedPassword("raj@123"));
//        user.setUserFirstName("raj");
//        user.setUserLastName("sharma");
//        Set<Role> userRoles = new HashSet<>();
//        userRoles.add(userRole);
//        user.setRole(userRoles);
//        userDao.save(user);
    }

    public User registerNewUser(User user) {
        Role role = roleRepository.findByRoleName("User");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);
        user.setPassword(getEncodedPassword(user.getPassword()));

        return userRepository.save(user);
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
