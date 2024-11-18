package com.alimert.service.impl;

import com.alimert.dto.*;
import com.alimert.enums.CarStatusType;
import com.alimert.exception.BaseException;
import com.alimert.exception.ErrorMessage;
import com.alimert.exception.MessageType;
import com.alimert.model.Car;
import com.alimert.model.Customer;
import com.alimert.model.Gallerist;
import com.alimert.model.SoldCar;
import com.alimert.repository.CarRepository;
import com.alimert.repository.CustomerRepository;
import com.alimert.repository.GalleristRepository;
import com.alimert.repository.SoldCarRepository;
import com.alimert.service.ICurrencyRatesService;
import com.alimert.service.ISoldCarService;
import com.alimert.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Optional;

@Service
public class SoldCarServiceImpl implements ISoldCarService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ICurrencyRatesService currencyRatesService;

    @Autowired
    private SoldCarRepository soldCarRepository;

    public BigDecimal convertCustomerMoneyToUsd(Customer customer) {
        CurrencyRatesResponse currencyRates = currencyRatesService.getCurrencyRates(DateUtils.getCurrentDate(new Date()), DateUtils.getCurrentDate(new Date()));
        BigDecimal usd = new BigDecimal(currencyRates.getItems().get(0).getUsd());

        BigDecimal customerMoney = customer.getAccount().getAmount().divide(usd, 2, RoundingMode.HALF_UP);
        return customerMoney;
    }

    public boolean checkMoney(DtoSoldCarIU dtoSoldCarIU) {

        Optional<Customer> optCustomer = customerRepository.findById(dtoSoldCarIU.getCustomerId());
        if (optCustomer.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSoldCarIU.getCustomerId().toString()));
        }
        Optional<Car> optCar = carRepository.findById(dtoSoldCarIU.getCarId());
        if (optCar.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSoldCarIU.getCarId().toString()));
        }

        BigDecimal customerUsdMoney = convertCustomerMoneyToUsd(optCustomer.get());
        if (customerUsdMoney.compareTo(optCar.get().getPrice()) == 0 || customerUsdMoney.compareTo(optCar.get().getPrice()) > 0) {
            return true;
        }
        return false;
    }

    private SoldCar createSoldCar(DtoSoldCarIU dtoSoldCarIU) {
        SoldCar soldCar = new SoldCar();
        soldCar.setCreateTime(new Date());

        soldCar.setCustomer(customerRepository.findById(dtoSoldCarIU.getCustomerId()).orElse(null)); // opt. for orelse
        soldCar.setGallerist(galleristRepository.findById(dtoSoldCarIU.getGalleristId()).orElse(null));
        soldCar.setCar(carRepository.findById(dtoSoldCarIU.getCarId()).orElse(null));

        return soldCar;

    }

    public boolean checkCarStatus(Long id) {
        Optional<Car> optCar = carRepository.findById(id);
        if (optCar.isPresent() && optCar.get().getCarStatusType().name().equals(CarStatusType.SOLD.name())) {
            return false;
        }
        return true;
    }

    public BigDecimal remaningCustomerMoney(Customer customer, Car car) {
        BigDecimal customerUsdMoney = convertCustomerMoneyToUsd(customer);
        BigDecimal remaningCustomerUsdMoney = customerUsdMoney.subtract(car.getPrice());

        CurrencyRatesResponse currencyRates = currencyRatesService.getCurrencyRates(DateUtils.getCurrentDate(new Date()), DateUtils.getCurrentDate(new Date()));
        BigDecimal usd = new BigDecimal(currencyRates.getItems().get(0).getUsd());
        return remaningCustomerUsdMoney.multiply(usd);

    }


    @Override
    public DtoSoldCar buyCar(DtoSoldCarIU dtoSoldCarIU) {


        if (!checkMoney(dtoSoldCarIU)) {
            throw new BaseException(new ErrorMessage(MessageType.CUSTOMER_MONEY_IS_NOT_ENOUGH, ""));
        }
        if (!checkCarStatus(dtoSoldCarIU.getCarId())) {
            throw new BaseException(new ErrorMessage(MessageType.CAR_STATUS_IS_ALREADY_SOLD, dtoSoldCarIU.getCarId().toString()));
        }

        SoldCar savedSoldCar = soldCarRepository.save(createSoldCar(dtoSoldCarIU));

        Car car = savedSoldCar.getCar();
        car.setCarStatusType(CarStatusType.SOLD);

        carRepository.save(car);

        Customer customer = savedSoldCar.getCustomer();
        customer.getAccount().setAmount(remaningCustomerMoney(customer, car));
        customerRepository.save(customer);

        return convertToDto(savedSoldCar);
    }

    public DtoSoldCar convertToDto(SoldCar soldCar) {
        DtoSoldCar dtoSoldCar = new DtoSoldCar();
        DtoCustomer dtoCustomer = new DtoCustomer();
        DtoGallerist dtoGallerist = new DtoGallerist();
        DtoCar dtoCar = new DtoCar();

        BeanUtils.copyProperties(soldCar, dtoCar);
        BeanUtils.copyProperties(soldCar.getCustomer(), dtoCustomer);
        BeanUtils.copyProperties(soldCar.getGallerist(), dtoGallerist);
        BeanUtils.copyProperties(soldCar.getCar(), dtoCar);

        dtoSoldCar.setCustomer(dtoCustomer);
        dtoSoldCar.setDtoGallerist(dtoGallerist);
        dtoSoldCar.setDtoCar(dtoCar);
        return dtoSoldCar;
    }
}
