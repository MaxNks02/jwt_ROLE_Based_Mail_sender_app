package com.example.jwtexample.entity;

import com.example.jwtexample.entity.enums.PermissionEnum;
import com.example.jwtexample.entity.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private RoleEnum name; //enum

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<PermissionEnum> permissionEnumSet;

}
