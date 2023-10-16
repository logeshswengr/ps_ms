package com.project.theatreservice.dtos;

import lombok.*;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ShowsResponse {
    private Date showTime;
    private String theatre;
    private String audi;
}
