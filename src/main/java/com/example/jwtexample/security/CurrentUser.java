package com.example.jwtexample.security;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.PARAMETER})
@AuthenticationPrincipal
//securityContextHolder dan danniy userni ko'tarib chiqish
public @interface CurrentUser {
}
