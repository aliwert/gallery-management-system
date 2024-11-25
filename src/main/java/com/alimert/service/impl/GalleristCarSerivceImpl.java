package com.alimert.service.impl;

import com.alimert.dto.*;
import com.alimert.exception.BaseException;
import com.alimert.exception.ErrorMessage;
import com.alimert.exception.MessageType;
import com.alimert.model.Car;
import com.alimert.model.Gallerist;
import com.alimert.model.GalleristCar;
import com.alimert.repository.CarRepository;
import com.alimert.repository.GalleristCarRepository;
import com.alimert.repository.GalleristRepository;
import com.alimert.service.IGalleristCarService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GalleristCarSerivceImpl implements IGalleristCarService {


    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private GalleristCarRepository galleristCarRepository;

    private GalleristCar createGalleristCar(DtoGalleristCarIU dtoGalleristCarIU) {
        Optional<Gallerist> optGallerist = galleristRepository.findById(dtoGalleristCarIU.getGalleristId());
        if (optGallerist.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristCarIU.getGalleristId().toString()));
        }

        Optional<Car> optCar = carRepository.findById(dtoGalleristCarIU.getCarId());
        if (optCar.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristCarIU.getCarId().toString()));
        }

        GalleristCar galleristCar = new GalleristCar();
        galleristCar.setCreateTime(new Date());
        galleristCar.setGallerist(optGallerist.get());
        galleristCar.setCar(optCar.get());

        return galleristCar;
    }


    @Override
    public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU) {
        DtoGalleristCar dtoGalleristCar = new DtoGalleristCar();
        DtoGallerist dtoGallerist = new DtoGallerist();
        DtoCar dtoCar = new DtoCar();
        DtoAddress dtoAddress = new DtoAddress();

        GalleristCar savedGalleristCar = galleristCarRepository.save(createGalleristCar(dtoGalleristCarIU));

        BeanUtils.copyProperties(savedGalleristCar, dtoGalleristCar);
        BeanUtils.copyProperties(savedGalleristCar.getGallerist(), dtoGallerist);
        BeanUtils.copyProperties(savedGalleristCar.getGallerist().getAddress(), dtoAddress);
        BeanUtils.copyProperties(savedGalleristCar.getCar(), dtoCar);

        dtoGallerist.setAddress(dtoAddress);
        dtoGalleristCar.setGallerist(dtoGallerist);
        dtoGalleristCar.setCar(dtoCar);

        return dtoGalleristCar;
    }

    @Override
    public DtoGalleristCar getGalleristCarById(Long id) {
        //gallerist, car

        DtoGalleristCar dtoGalleristCar = new DtoGalleristCar();
        Optional<GalleristCar> optGalleristCar = galleristCarRepository.findById(id);
        if (optGalleristCar.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));
        }
        GalleristCar galleristCar = optGalleristCar.get();
        BeanUtils.copyProperties(galleristCar, dtoGalleristCar);

        DtoGallerist dtoGallerist = new DtoGallerist();
        dtoGallerist.setId(galleristCar.getGallerist().getId());
        dtoGallerist.setFirstName(galleristCar.getGallerist().getFirstName());
        dtoGallerist.setLastName(galleristCar.getGallerist().getLastName());
        dtoGallerist.setAddress(dtoGallerist.getAddress());

        DtoCar dtoCar = new DtoCar();
        dtoCar.setId(galleristCar.getCar().getId());
        dtoCar.setPlate(galleristCar.getCar().getPlate());
        dtoCar.setBrand(galleristCar.getCar().getBrand());
        dtoCar.setModel(galleristCar.getCar().getModel());
        dtoCar.setPrice(galleristCar.getCar().getPrice());
        dtoCar.setDamagePrice(galleristCar.getCar().getDamagePrice());
        dtoCar.setCurrencyType(galleristCar.getCar().getCurrencyType());
        dtoCar.setCreateTime(galleristCar.getCar().getCreateTime());

        dtoGalleristCar.setGallerist(dtoGallerist);
        dtoGalleristCar.setCar(dtoCar);

        return dtoGalleristCar;
    }

    @Override
    public List<DtoGalleristCar> getAllGalleristCar() {

        List<DtoGalleristCar> dtoGalleristCarList = new ArrayList<>();
        List<GalleristCar> galleristCarList = galleristCarRepository.findAll();
        if (!galleristCarList.isEmpty()) {
            for (GalleristCar galleristCar : galleristCarList) {
                DtoGalleristCar dtoGalleristCar = new DtoGalleristCar();
                BeanUtils.copyProperties(galleristCar, dtoGalleristCar);
                galleristCarList.get(galleristCarList.indexOf(galleristCar));


                DtoGallerist dtoGallerist = new DtoGallerist();
                dtoGallerist.setId(galleristCar.getGallerist().getId());
                dtoGallerist.setFirstName(galleristCar.getGallerist().getFirstName());
                dtoGallerist.setLastName(galleristCar.getGallerist().getLastName());
                dtoGallerist.setAddress(dtoGallerist.getAddress());

                DtoCar dtoCar = new DtoCar();
                dtoCar.setId(galleristCar.getCar().getId());
                dtoCar.setCarStatusType(galleristCar.getCar().getCarStatusType());
                dtoCar.setPrice(galleristCar.getCar().getPrice());
                dtoCar.setDamagePrice(galleristCar.getCar().getDamagePrice());
                dtoCar.setCurrencyType(galleristCar.getCar().getCurrencyType());
                dtoCar.setModel(galleristCar.getCar().getModel());
                dtoCar.setBrand(galleristCar.getCar().getBrand());
                dtoCar.setCreateTime(galleristCar.getCar().getCreateTime());

                dtoGalleristCar.setGallerist(dtoGallerist);
                dtoGalleristCar.setCar(dtoCar);
                dtoGalleristCarList.add(dtoGalleristCar);
                return dtoGalleristCarList;


            }
        }


        return List.of();
    }
}
