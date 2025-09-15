package com.collegesearch.collegesearch.dto;

import lombok.Data;


@Data
public class UserDto {
    int id;
    String name;
    String email;
    String city;
    String course;
}
