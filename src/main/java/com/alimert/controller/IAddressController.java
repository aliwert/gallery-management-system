package com.alimert.controller;

import com.alimert.dto.DtoAddress;
import com.alimert.dto.DtoAddressIU;

import java.util.List;

public interface IAddressController {

    public RootEntity<DtoAddress> saveAddress(DtoAddressIU dtoAddressIU);
    public RootEntity<DtoAddress> findAddressById(Long id);
    public RootEntity<List<DtoAddress>> findAllAddresses();
}
