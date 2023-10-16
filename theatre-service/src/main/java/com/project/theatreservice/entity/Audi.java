package com.project.theatreservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.theatreservice.entity.enums.Features;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Audi{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private int maxRows;
    private int maxColumns;
    @ManyToOne(targetEntity = Theatre.class)
    private Theatre theatre;
    @Enumerated(EnumType.ORDINAL)
    private List<Features> supportedFeatures;
}
