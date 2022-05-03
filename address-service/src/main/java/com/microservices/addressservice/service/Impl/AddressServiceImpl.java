package com.microservices.addressservice.service.Impl;

import com.microservices.addressservice.entity.Address;
import com.microservices.addressservice.repository.AddressRepository;
import com.microservices.addressservice.request.CreateAddressRequest;
import com.microservices.addressservice.response.AddressResponse;
import com.microservices.addressservice.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    Logger logger = LoggerFactory.getLogger(AddressServiceImpl.class);

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public AddressResponse createAddress(CreateAddressRequest request) {
        Address address = new Address();
        address.setStreet(request.getStreet());
        address.setCity(request.getCity());
        address = addressRepository.save(address);
        return new AddressResponse(address.getId(),
                                    address.getStreet(),
                                    address.getCity());
    }

    @Override
    public AddressResponse getById(Long id) {
        logger.info("Inside get By Id: " + id);

        Address address = addressRepository.getOne(id);
        return new AddressResponse(address.getId(),
                                    address.getStreet(),
                                    address.getCity());
    }
}
