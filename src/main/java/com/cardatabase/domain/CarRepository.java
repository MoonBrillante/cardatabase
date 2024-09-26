package com.cardatabase.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CarRepository extends CrudRepository<Car, Long> {
    Optional<Object> findByRegistrationNumber(String registrationNumber);
}