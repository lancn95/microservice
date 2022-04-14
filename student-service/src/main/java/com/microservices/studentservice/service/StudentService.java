package com.microservices.studentservice.service;

import com.microservices.studentservice.request.CreateStudentRequest;
import com.microservices.studentservice.response.StudentResponse;

public interface StudentService {
    StudentResponse createStudent(CreateStudentRequest request);

    StudentResponse getById(Long id);
}
