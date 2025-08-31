package com.codehows.cardatabase.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ownerid;

    private String firstname, lastname;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<Car> cars;
}
