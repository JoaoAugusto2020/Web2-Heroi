package com.joaoa.Web2_Heroi.repository;

import java.util.Optional;

import com.joaoa.Web2_Heroi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByEmail(String email);
}