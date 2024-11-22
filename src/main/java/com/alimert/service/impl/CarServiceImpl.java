package com.alimert.service.impl;

import com.alimert.controller.RootEntity;
import com.alimert.dto.DtoCar;
import com.alimert.dto.DtoCarIU;
import com.alimert.model.Account;
import com.alimert.model.Car;
import com.alimert.repository.CarRepository;
import com.alimert.service.ICarService;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements ICarService {

    @Autowired
    private CarRepository carRepository;

    private Car createCar(DtoCarIU dtoCarIU) {
        Car car = new Car();
        car.setCreateTime(new Date());
        BeanUtils.copyProperties(dtoCarIU, car);
        return car;
    }


    @Override
    public DtoCar saveCar(DtoCarIU dtoCarIU) {
        DtoCar dtoCar = new DtoCar();
        Car savedCar = carRepository.save(createCar(dtoCarIU));

        BeanUtils.copyProperties(savedCar, dtoCar);
        return dtoCar;
    }

    @Override
    public DtoCar getCarById(Long id) {
        DtoCar dtoCar = new DtoCar();
        Optional<Car> optCar = carRepository.findById(id);
        if (optCar.isEmpty()) {
            return null;
        }
        Car car = optCar.get();
        BeanUtils.copyProperties(car, dtoCar);

        return dtoCar;
    }

    @Override
    public List<DtoCar> getAllCars() {
        List<DtoCar> dtoCars = new ArrayList<>();
        List<Car> carList = carRepository.findAll();
        if (!carList.isEmpty() && carList != null) {
            for (Car car : carList) {
                DtoCar dtoCar = new DtoCar();
                BeanUtils.copyProperties(car, dtoCar);
                dtoCars.add(dtoCar);
            }
        }
        return dtoCars;
    }

    @Override
    public DtoCar updateCar(Long id, DtoCarIU dtoCarIU) {
        DtoCar dtoCar = new DtoCar();
        Optional<Car> optCar = carRepository.findById(id);
        if (optCar.isPresent()) {
            Car dbCar = optCar.get();
            dbCar.setBrand(dtoCarIU.getBrand());
            dbCar.setModel(dtoCarIU.getModel());
            dbCar.setPrice(dtoCarIU.getPrice());
            dbCar.setPlate(dtoCarIU.getPlate());
            dbCar.setCarStatusType(dtoCarIU.getCarStatusType());
            dbCar.setProductionYear(dtoCarIU.getProductionYear());
            dbCar.setDamagePrice(dtoCarIU.getDamagePrice());
            dbCar.setCurrencyType(dtoCarIU.getCurrencyType());
            Car updateAccount = carRepository.save(dbCar);
            BeanUtils.copyProperties(updateAccount, dtoCar);
            return dtoCar;
        }
        return null;
    }

    @Override
    public RootEntity<Void> deleteCar(Long id) {
        Optional<Car> optCar = carRepository.findById(id);
        if (optCar != null) {
            carRepository.deleteById(id);
        }else {
            throw new OpenApiResourceNotFoundException("Car not found");
        }
        return null;
    }
}
