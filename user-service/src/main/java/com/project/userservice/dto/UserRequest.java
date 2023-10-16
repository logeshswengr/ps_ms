package com.project.userservice.dto;

import com.project.userservice.annotations.Mask;
import com.project.userservice.annotations.PII;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Mask
public class UserRequest {
    private Integer id;
    private String name;
    @PII(keepLastDigits=4)
    private String email;
    @PII
    private String password;
}
