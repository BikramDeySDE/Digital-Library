package com.bikram.Digital_Library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bikram.Digital_Library.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
