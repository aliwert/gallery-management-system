package com.alimert.service.impl;

import com.alimert.dto.DtoSoldCar;
import com.alimert.dto.DtoSoldCarIU;
import com.alimert.exception.BaseException;
import com.alimert.exception.ErrorMessage;
import com.alimert.exception.MessageType;
import com.alimert.model.Car;
import com.alimert.model.Customer;
import com.alimert.model.Gallerist;
import com.alimert.repository.CarRepository;
import com.alimert.repository.CustomerRepository;
import com.alimert.repository.GalleristRepository;
import com.alimert.service.ISoldCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SoldCarServiceImpl implements ISoldCarService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private CarRepository carRepository;

    public boolean checkMoney(DtoSoldCarIU dtoSoldCarIU) {

        Optional<Customer> optCustomer = customerRepository.findById(dtoSoldCarIU.getCustomerId());
        if (optCustomer.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSoldCarIU.getCustomerId().toString()));
        }
        Optional<Car> optCar = carRepository.findById(dtoSoldCarIU.getCarId());
        if (optCar.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSoldCarIU.getCarId().toString()));
        }
        Optional<Gallerist> optGallerist = galleristRepository.findById(dtoSoldCarIU.getGalleristId());
        if (optGallerist.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSoldCarIU.getGalleristId().toString()));
        }

        return false;
    }


    @Override
    public DtoSoldCar buyCar(DtoSoldCarIU dtoSoldCarIU) {
        return null;
    }
}
