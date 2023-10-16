package com.project.theatreservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TheatreResponse {
    private Long id;
    private String name;
    private List<AudiResponse> audi;
}
