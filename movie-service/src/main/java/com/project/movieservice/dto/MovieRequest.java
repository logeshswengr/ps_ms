package com.project.movieservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MovieRequest {
    private Integer id;
    private String name;
    private String description;
    private Integer durationMint;
    private String genre;
}
