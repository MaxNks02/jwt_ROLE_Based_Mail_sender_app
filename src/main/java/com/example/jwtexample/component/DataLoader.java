package com.example.jwtexample.component;

import com.example.jwtexample.entity.Company;
import com.example.jwtexample.entity.Role;
import com.example.jwtexample.entity.User;
import com.example.jwtexample.entity.enums.PermissionEnum;
import com.example.jwtexample.entity.enums.RoleEnum;
import com.example.jwtexample.repository.CompanyRepository;
import com.example.jwtexample.repository.RoleRepository;
import com.example.jwtexample.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {
    @Value("${spring.sql.init.mode}")
    private String mode; //always

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddl; //create

    final RoleRepository roleRepository;
    final UserRepository userRepository;
    final CompanyRepository companyRepository;
    final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (mode.equals("always") && ddl.equals("create")) {
            Role admin = new Role();
            admin.setName(RoleEnum.ADMIN);
            admin.setPermissionEnumSet(Arrays.stream(PermissionEnum.values()).collect(Collectors.toSet()));
            Role manager = new Role();
            manager.setName(RoleEnum.MANAGER);
            manager.setPermissionEnumSet(new HashSet<>(Arrays.asList(
                    PermissionEnum.READ_ALL_EMPLOYEE,
                    PermissionEnum.EDIT_EMPLOYEE,
                    PermissionEnum.READ_EMPLOYEE
            )));
            Role user_role = new Role();
            user_role.setName(RoleEnum.USER);
            user_role.setPermissionEnumSet(new HashSet<>(Arrays.asList(
                    PermissionEnum.READ_EMPLOYEE,
                    PermissionEnum.READ_ALL_EMPLOYEE
            )));
            roleRepository.save(admin);
            roleRepository.save(manager);
            roleRepository.save(user_role);

            Company company = new Company();
            company.setName("Meta");
            companyRepository.save(company);

            Set<Role> roles = new HashSet<>();
            roles.add(admin);
            roles.add(manager);
            roles.add(user_role);

            User user = new User("Max", roles,
                    "max", passwordEncoder.encode("123"), true);
            userRepository.save(user);
            User ketmon = new User("Ketmon", new HashSet<>(List.of(user_role)), "user",
                    passwordEncoder.encode("111"), true);
            userRepository.save(ketmon);
        }
    }
}
