package com.microservices.addressservice.controller;

import com.microservices.addressservice.request.CreateAddressRequest;
import com.microservices.addressservice.response.AddressResponse;
import com.microservices.addressservice.service.AddressService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService){
        this.addressService  = addressService;
    }

    @PostMapping("/create")
    public AddressResponse createAddress(@RequestBody CreateAddressRequest request){
        return addressService.createAddress(request);
    }

    @GetMapping("/getById/{id}")
    public AddressResponse getById(@PathVariable(value = "id") Long id){
        return addressService.getById(id);
    }
}
