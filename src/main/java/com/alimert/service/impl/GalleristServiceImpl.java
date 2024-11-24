package com.alimert.service.impl;


import com.alimert.dto.DtoAddress;
import com.alimert.dto.DtoGallerist;
import com.alimert.dto.DtoGalleristIU;
import com.alimert.exception.BaseException;
import com.alimert.exception.ErrorMessage;
import com.alimert.exception.MessageType;
import com.alimert.model.Address;
import com.alimert.model.Gallerist;
import com.alimert.repository.AddressRepository;
import com.alimert.repository.GalleristRepository;
import com.alimert.service.IGalleristService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GalleristServiceImpl implements IGalleristService {

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private AddressRepository addressRepository;

    private Gallerist createGallerist(DtoGalleristIU dtoGalleristIU) {
        Optional<Address> address = addressRepository.findById(dtoGalleristIU.getAddressId());
        if (address.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristIU.getAddressId().toString()));
        }
        Gallerist gallerist = new Gallerist();
        gallerist.setCreateTime(new Date());

        BeanUtils.copyProperties(dtoGalleristIU, gallerist);
        gallerist.setAddress(address.get());

        return gallerist;
    }


    @Override
    public DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU) {
        DtoGallerist dtoGallerist = new DtoGallerist();
        DtoAddress dtoAddress = new DtoAddress();
        Gallerist savedGallerist = galleristRepository.save(createGallerist(dtoGalleristIU));
        BeanUtils.copyProperties(dtoGalleristIU, dtoGallerist);
        BeanUtils.copyProperties(savedGallerist.getAddress(), dtoAddress);
        dtoGallerist.setAddress(dtoAddress);
        return dtoGallerist;
    }

    @Override
    public List<DtoGallerist> getAllGallerist() {
        List<DtoGallerist> dtoGallerists = new ArrayList<>();
        List<Gallerist> galleristList = galleristRepository.findAll();

        if (!galleristList.isEmpty()) {
            for (Gallerist gallerist : galleristList) {
                DtoGallerist dtoGallerist = new DtoGallerist();
                BeanUtils.copyProperties(gallerist, dtoGallerist);
                galleristList.get(galleristList.indexOf(gallerist));

                DtoAddress dtoAddress = new DtoAddress();
                dtoAddress.setDistrict(gallerist.getAddress().getDistrict());
                dtoAddress.setCity(gallerist.getAddress().getCity());
                dtoAddress.setStreet(gallerist.getAddress().getStreet());
                dtoAddress.setNeighborhood(gallerist.getAddress().getNeighborhood());
                dtoGallerist.setAddress(dtoAddress);
                dtoGallerists.add(dtoGallerist);
            }
        }
        return dtoGallerists;
    }

    @Override
    public DtoGallerist getGalleristById(Long id) {
        DtoGallerist dtoGallerist = new DtoGallerist();
        Optional<Gallerist> optGallerist = galleristRepository.findById(id);
        if (optGallerist.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));
        }
        Gallerist gallerist = optGallerist.get();
        BeanUtils.copyProperties(gallerist, dtoGallerist);

        DtoAddress dtoAddress = new DtoAddress();
        dtoAddress.setId(gallerist.getAddress().getId());
        dtoAddress.setCity(gallerist.getAddress().getCity());
        dtoAddress.setDistrict(gallerist.getAddress().getDistrict());
        dtoAddress.setStreet(gallerist.getAddress().getStreet());
        dtoAddress.setCreateTime(gallerist.getAddress().getCreateTime());
        dtoAddress.setNeighborhood(gallerist.getAddress().getNeighborhood());

        dtoGallerist.setAddress(dtoAddress);

        return dtoGallerist;
    }

    @Override
    public DtoGallerist updateGallerist(Long id, DtoGalleristIU dtoGalleristIU) {
        DtoGallerist dtoGallerist = new DtoGallerist();
        Optional<Gallerist> optGallerist = galleristRepository.findById(id);
        if (optGallerist.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));
        }
        Gallerist dbGallerist = optGallerist.get();
        dbGallerist.setFirstName(dtoGalleristIU.getFirstName());
        dbGallerist.setLastName(dtoGalleristIU.getLastName());
        dbGallerist.setCreateTime(new Date());
        Gallerist updatedGallerist = galleristRepository.save(dbGallerist);
        BeanUtils.copyProperties(updatedGallerist, dtoGallerist);
        return dtoGallerist;
    }
}
