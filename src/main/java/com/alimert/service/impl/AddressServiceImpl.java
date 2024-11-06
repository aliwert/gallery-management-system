package com.alimert.service.impl;

import com.alimert.dto.DtoAddress;
import com.alimert.dto.DtoAddressIU;
import com.alimert.model.Address;
import com.alimert.repository.AddressRepository;
import com.alimert.service.IAddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressRepository addressRepository;

    private Address createAddress(DtoAddressIU dtoAddressIU) {
        Address address = new Address();
        address.setCreateTime(new Date());

        BeanUtils.copyProperties(dtoAddressIU, address);
        return address;

    }

    @Override
    public DtoAddress saveAddress(DtoAddressIU address) {
        Address savedAddress = addressRepository.save(createAddress(address));
        DtoAddress dtoAddress = new DtoAddress();
        BeanUtils.copyProperties(savedAddress, dtoAddress);
        return dtoAddress;
    }
}
