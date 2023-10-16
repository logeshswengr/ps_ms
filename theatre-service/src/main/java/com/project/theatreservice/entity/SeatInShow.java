package com.project.theatreservice.entity;

import com.project.theatreservice.entity.enums.SeatStatus;
import com.project.theatreservice.entity.enums.SeatType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SeatInShow{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY, targetEntity = Seat.class)
    private Seat seat;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY, targetEntity = Show.class)
    private Show show;
    @Enumerated(value = EnumType.ORDINAL)
    private SeatStatus seatStatus;
    private Date lockedAt;
}
