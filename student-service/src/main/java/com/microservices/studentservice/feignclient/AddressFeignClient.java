package com.microservices.studentservice.feignclient;

import com.microservices.studentservice.response.AddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(url = "${address.service.url}",  use it without eureka
//             value = "address-feign-client",
//@FeignClient(value = "ADDRESS-SERVICE", use it  eureka
//             path = "/api/address")
@FeignClient(value = "api-gateway")
public interface AddressFeignClient {

    @GetMapping("/address-service/api/address/getById/{id}")
    AddressResponse getById(@PathVariable long id);
}
