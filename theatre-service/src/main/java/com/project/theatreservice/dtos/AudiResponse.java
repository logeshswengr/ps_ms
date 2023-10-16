package com.project.theatreservice.dtos;

import com.project.theatreservice.entity.Theatre;
import com.project.theatreservice.entity.enums.Features;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AudiResponse {

    private Long id;
    private String number;
    private int maxRows;
    private int maxColumns;
    private Theatre theatre;
    private List<Features> supportedFeatures;
}
