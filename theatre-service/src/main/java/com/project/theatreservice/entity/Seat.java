package com.project.theatreservice.entity;

import com.project.theatreservice.entity.enums.SeatType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Seat{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private int rowNum;
    private int colNum;
    @Enumerated(EnumType.ORDINAL)
    private SeatType seatType;
}
