package com.project.theatreservice.entity;

import com.project.theatreservice.entity.enums.ShowStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Show{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(targetEntity = Audi.class)
    private Audi audi;
    private Long movieId;
    private Date startTime;
    private Date endTime;
    @Enumerated
    private ShowStatus showStatus;
    private int maxTickets;
}
