package com.alimert.controller;

import com.alimert.dto.DtoAddress;
import com.alimert.dto.DtoAddressIU;

public interface IAddressController {

    public RootEntity<DtoAddress> saveAddress(DtoAddressIU dtoAddressIU);
}
