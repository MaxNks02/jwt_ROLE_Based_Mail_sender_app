package com.example.jwtexample.controller;

import com.example.jwtexample.aop.CheckPermission;
import com.example.jwtexample.entity.User;
import com.example.jwtexample.repository.CompanyRepository;
import com.example.jwtexample.repository.UserRepository;
import com.example.jwtexample.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    UserRepository userRepository;

    @PreAuthorize("hasAuthority('READ_ALL_EMPLOYEE')")
    @GetMapping
    public HttpEntity<?> getAll(HttpServletRequest request) {
        return ResponseEntity.ok().body(companyRepository.findAll());

    }

    //    @PreAuthorize("hasAuthority('ADD_EMPLOYEE')")
    @CheckPermission("ADD_EMPLOYEE") //yangi annotatsiya
    @GetMapping("/addEmployee")
    public HttpEntity<?> post() {
        return ResponseEntity.ok().body("Added");
    }






        //        String bearer = request.getHeader("Bearer");
//
//    boolean exist = false;
//        if (jwtProvider.validateToken(bearer)) {
//            if (jwtProvider.expireToken(bearer)) {
//                String userName = jwtProvider.getUserNameFromToken(bearer);
//                System.out.println(userName);
//                Optional<User> byUserName = userRepository.findByUserName(userName);
//                for (GrantedAuthority authority : byUserName.get().getAuthorities()) {
//                    if (authority.getAuthority().equals("READ_ALL_EMPLOY")) {
//                        exist = true;
//                        break;
//                    }
//                }
//            }
//        }
}
