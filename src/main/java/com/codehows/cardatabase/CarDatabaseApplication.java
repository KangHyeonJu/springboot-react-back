package com.codehows.cardatabase;

import com.codehows.cardatabase.domain.AppUser;
import com.codehows.cardatabase.domain.Car;
import com.codehows.cardatabase.domain.Owner;
import com.codehows.cardatabase.domain.repository.AppUserRepository;
import com.codehows.cardatabase.domain.repository.CarRepository;
import com.codehows.cardatabase.domain.repository.OwnerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class CarDatabaseApplication implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(CarDatabaseApplication.class);
    private final CarRepository repository;
    private final OwnerRepository orepository;
    private final AppUserRepository urepository;

    public CarDatabaseApplication(CarRepository repository, OwnerRepository orepository, AppUserRepository urepository) {
        this.repository = repository;
        this.orepository = orepository;
        this.urepository = urepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(CarDatabaseApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Add owner objects and save these to db
        Owner owner1 = Owner.builder()
                .firstname("John")
                .lastname("Johnson")
                .build();
        Owner owner2 = Owner.builder()
                .firstname("Mary")
                .lastname("Robinson")
                .build();
        orepository.saveAll(Arrays.asList(owner1, owner2));

        repository.save(Car.builder()
                .brand("Ford")
                .model("Mustang")
                .color("Red")
                .registrationNumber("ADF-1121")
                .modelYear(2023)
                .price(59000)
                .owner(owner1)
                .build());

        repository.save(Car.builder()
                .brand("Nissan")
                .model("Leaf")
                .color("White")
                .registrationNumber("SSJ-3002")
                .modelYear(2020)
                .price(29000)
                .owner(owner2)
                .build());

        repository.save(Car.builder()
                .brand("Toyota")
                .model("Prius")
                .color("Silver")
                .registrationNumber("KKO-0212")
                .modelYear(2022)
                .price(39000)
                .owner(owner2)
                .build());
        // Username: user, password: user
        urepository.save(AppUser.builder()
                .username("user")
                .password("user")
                .role("USER")
                .build()
        );
        // Username: admin, password: admin
        urepository.save(AppUser.builder()
                .username("admin")
                .password("admin")
                .role("ADMIN")
                .build()
        );
        // Fetch all cars and log to console
        for (Car car : repository.findAll()) {
            logger.info("brand: {}, model: {}", car.getBrand(), car.getModel());
        }
    }

}
