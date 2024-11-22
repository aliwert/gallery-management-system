package com.alimert.service;

import com.alimert.dto.DtoAddress;
import com.alimert.dto.DtoAddressIU;

import java.util.List;

public interface IAddressService {

    public DtoAddress saveAddress(DtoAddressIU address);
    public DtoAddress getAddressById(Long id);
    public List<DtoAddress> getAllAddresses();
}
