package com.alimert.controller.impl;

import com.alimert.controller.BaseController;
import com.alimert.controller.IAddressController;
import com.alimert.controller.RootEntity;
import com.alimert.dto.DtoAddress;
import com.alimert.dto.DtoAddressIU;
import com.alimert.service.IAddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rest/api/address")
public class AddressControllerImpl extends BaseController implements IAddressController {

    @Autowired
    private IAddressService addressService;

    @Override
    @PostMapping("/save")
    public RootEntity<DtoAddress> saveAddress(@Valid @RequestBody DtoAddressIU dtoAddressIU) {
        return ok(addressService.saveAddress(dtoAddressIU));
    }
}
