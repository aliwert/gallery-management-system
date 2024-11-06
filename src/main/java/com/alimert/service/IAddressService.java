package com.alimert.service;

import com.alimert.dto.DtoAddress;
import com.alimert.dto.DtoAddressIU;

public interface IAddressService {

    public DtoAddress saveAddress(DtoAddressIU address);
}
