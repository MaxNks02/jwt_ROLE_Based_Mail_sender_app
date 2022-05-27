package com.example.jwtexample.controller;


import com.example.jwtexample.component.GmailSender;
import com.example.jwtexample.dto.LoginDTO;
import com.example.jwtexample.entity.User;
import com.example.jwtexample.security.CurrentUser;
import com.example.jwtexample.security.JwtProvider;
import com.example.jwtexample.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/auth/")

public class AuthController {

   @Autowired
   GmailSender gmailSender;

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    AuthService authService;

    @Autowired
    JwtProvider jwtProvider;




    @PostMapping("login")
    public HttpEntity<?> login(@RequestBody LoginDTO dto) {
        System.out.println(dto);
        String token = jwtProvider.generateToken(dto.getUserName());
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("Hi,\n" +
                "\n" +
                "You are logged in " +dto.getGmail());
        message.setSubject("GO GO");
        message.setSentDate(new Date());
        message.setTo(dto.getGmail());
        JavaMailSender mailSender = gmailSender.send();
        mailSender.send(message);
        return ResponseEntity.ok().body(token);
    }

    @GetMapping("/me")
    public HttpEntity<?> getMe(@CurrentUser User user) { //Parametr
        return ResponseEntity.ok().body("Mana" + user);
    }

}
