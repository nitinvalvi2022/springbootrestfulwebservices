package com.spring.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.crud.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
