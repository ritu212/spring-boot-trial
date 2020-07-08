package com.spring.trialproject.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//Acts as a bridge.Use to map inputs from controller to model class
@Getter
@Setter
@ToString
public class UserDto {
    private String userName;
    private String password;
    private Long phoneNumber;
}
