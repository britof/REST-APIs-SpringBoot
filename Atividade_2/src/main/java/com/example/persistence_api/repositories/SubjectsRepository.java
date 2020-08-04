package com.example.persistence_api.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.persistence_api.entities.Subject;

@Repository
public interface SubjectsRepository<T, ID extends Serializable> extends JpaRepository<Subject, Integer> {

}