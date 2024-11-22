package com.alimert.service.impl;

import com.alimert.controller.RootEntity;
import com.alimert.dto.DtoAddress;
import com.alimert.dto.DtoAddressIU;
import com.alimert.model.Address;
import com.alimert.repository.AddressRepository;
import com.alimert.service.IAddressService;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @Override
    public DtoAddress getAddressById(Long id) {
        DtoAddress dtoAddress = new DtoAddress();
        Optional<Address> optAddress = addressRepository.findById(id);
        if (optAddress.isEmpty()) {
            return null;
        }
        Address address = optAddress.get();
        BeanUtils.copyProperties(address, dtoAddress);

        return dtoAddress;
    }

    @Override
    public List<DtoAddress> getAllAddresses() {
        List<DtoAddress> dtoAddressList = new ArrayList<>();
        List<Address> addressList = addressRepository.findAll();
        if (!addressList.isEmpty() && addressList != null) {
            for (Address address : addressList) {
                DtoAddress dtoAddress = new DtoAddress();
                BeanUtils.copyProperties(address, dtoAddress);
                dtoAddressList.add(dtoAddress);
            }
        }
        return dtoAddressList;
    }

    @Override
    public DtoAddress updateAddress(Long id, DtoAddressIU dtoAddressIU) {
        DtoAddress dtoAddress = new DtoAddress();
        Optional<Address> optAddress = addressRepository.findById(id);
        if (optAddress.isPresent()) {
            Address dbAddress = optAddress.get();
            dbAddress.setCity(dtoAddressIU.getCity());
            dbAddress.setDistrict(dtoAddressIU.getDistrict());
            dbAddress.setNeighborhood(dtoAddressIU.getNeighborhood());
            dbAddress.setStreet(dtoAddressIU.getStreet());
            Address address = addressRepository.save(dbAddress);
            BeanUtils.copyProperties(address, dtoAddress);
            return dtoAddress;
        }
        return null;
    }

    @Override
    public RootEntity<Void> deleteAddress(Long id) {
        Optional<Address> optAddress = addressRepository.findById(id);
        if (optAddress != null) {
            addressRepository.deleteById(id);
        } else {
            throw new OpenApiResourceNotFoundException("Address not found");
        }


        return null;
    }

}
