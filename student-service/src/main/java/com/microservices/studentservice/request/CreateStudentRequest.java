package com.microservices.studentservice.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateStudentRequest {
    private String firstName;
    private String lastName;
    private String email;
    private Long addressId;
}
