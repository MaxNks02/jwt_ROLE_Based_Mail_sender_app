package com.example.jwtexample.repository;


import com.example.jwtexample.entity.Card;
import com.example.jwtexample.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

@RepositoryRestResource(path = "card")
public interface CardRepository extends JpaRepository<Card, Integer> {

    //controller va servicesz nazorat
    @PreAuthorize("hasAuthority('ADD_EMPLOYEE')")
    @Override
    <S extends Card> S save(S entity);
}
