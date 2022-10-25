package com.car.tracking.jwt.entity.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Transactional
public class User {
    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    private long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String teleqramAccount;
    private String phoneNumber;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Car> cars;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE",
            joinColumns = {
                    @JoinColumn(name = "USER_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID")
            }
    )
    private Set<Role> role;

}
