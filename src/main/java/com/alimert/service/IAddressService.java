package com.alimert.service;

import com.alimert.controller.RootEntity;
import com.alimert.dto.DtoAddress;
import com.alimert.dto.DtoAddressIU;

import java.util.List;

public interface IAddressService {

    public DtoAddress saveAddress(DtoAddressIU address);

    public DtoAddress getAddressById(Long id);

    public List<DtoAddress> getAllAddresses();

    public DtoAddress updateAddress(Long id, DtoAddressIU dtoAddressIU);

    public RootEntity<Void> deleteAddress(Long id);
}
