package com.project.theatreservice.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Theatre{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY, targetEntity=City.class)
    private City city;
}
