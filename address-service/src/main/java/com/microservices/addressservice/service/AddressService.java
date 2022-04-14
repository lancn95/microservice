package com.microservices.addressservice.service;

import com.microservices.addressservice.request.CreateAddressRequest;
import com.microservices.addressservice.response.AddressResponse;

public interface AddressService {
    AddressResponse createAddress(CreateAddressRequest request);

    AddressResponse getById(Long id);
}
