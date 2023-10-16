package com.project.theatreservice.dtos;

import com.project.theatreservice.entity.Audi;
import com.project.theatreservice.entity.enums.ShowStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ShowRequest {
    private Long id;
    private Long audiId;
    private Long movieId;
    private Date startTime;
    private Date endTime;
    private ShowStatus showStatus;
    private int maxTickets;
}
