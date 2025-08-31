package com.codehows.cardatabase.domain.repository;

import com.codehows.cardatabase.domain.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Long> {
}
