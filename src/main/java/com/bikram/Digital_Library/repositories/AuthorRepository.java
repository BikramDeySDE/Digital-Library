package com.bikram.Digital_Library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bikram.Digital_Library.entities.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

}
