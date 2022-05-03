package com.microservices.studentservice.service.Impl;

import com.microservices.studentservice.entity.Student;
import com.microservices.studentservice.feignclient.AddressFeignClient;
import com.microservices.studentservice.repository.StudentRepository;
import com.microservices.studentservice.request.CreateStudentRequest;
import com.microservices.studentservice.response.AddressResponse;
import com.microservices.studentservice.response.StudentResponse;
import com.microservices.studentservice.service.StudentService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final WebClient webClient;
    private final AddressFeignClient addressFeignClient;

    public StudentServiceImpl(StudentRepository studentRepository,
                              WebClient webClient,
                              AddressFeignClient addressFeignClient) {
        this.studentRepository = studentRepository;
        this.webClient = webClient;
        this.addressFeignClient = addressFeignClient;
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
//        studentResponse.setAddressResponse(getAddressById(student.getAddressId()));

        studentResponse.setAddressResponse(addressFeignClient.getById(student.getAddressId()));
        return studentResponse;
    }

    @Override
    @Cacheable(key = "#id", value = "Student")
    public StudentResponse getById(Long id) {
        System.out.println("Start call student ...");
        Student student = studentRepository.getOne(id);
        StudentResponse studentResponse = new StudentResponse(student);
//        studentResponse.setAddressResponse(getAddressById(student.getAddressId()));

        studentResponse.setAddressResponse(addressFeignClient.getById(student.getAddressId()));
        return studentResponse;
    }

    public AddressResponse getAddressById(Long addressId){
        Mono<AddressResponse> addressResponseMono = webClient.get().uri("/getById/" + addressId)
                                                    .retrieve()
                                                    .bodyToMono(AddressResponse.class);
        return addressResponseMono.block();
    }
}
