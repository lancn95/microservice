package com.microservices.studentservice.service.Impl;

import com.microservices.studentservice.entity.Student;
import com.microservices.studentservice.repository.StudentRepository;
import com.microservices.studentservice.request.CreateStudentRequest;
import com.microservices.studentservice.response.AddressResponse;
import com.microservices.studentservice.response.StudentResponse;
import com.microservices.studentservice.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final WebClient webClient;

    public StudentServiceImpl(StudentRepository studentRepository,
                              WebClient webClient) {
        this.studentRepository = studentRepository;
        this.webClient = webClient;
    }


    @Override
    public StudentResponse createStudent(CreateStudentRequest request) {
        Student student = new Student();
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setEmail(request.getEmail());

        student.setAddressId(request.getAddressId());
        student = studentRepository.save(student);

        StudentResponse studentResponse = new StudentResponse(student);
        studentResponse.setAddressResponse(getAddressById(student.getAddressId()));
        return studentResponse;
    }

    @Override
    public StudentResponse getById(Long id) {
        Student student = studentRepository.getById(id);
        StudentResponse studentResponse = new StudentResponse(student);
        studentResponse.setAddressResponse(getAddressById(student.getAddressId()));
        return studentResponse;
    }

    public AddressResponse getAddressById(Long addressId){
        Mono<AddressResponse> addressResponseMono = webClient.get().uri("/getById/" + addressId)
                .retrieve().bodyToMono(AddressResponse.class);
        return addressResponseMono.block();
    }
}
