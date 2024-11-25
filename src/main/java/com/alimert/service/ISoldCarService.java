package com.alimert.service;

import com.alimert.dto.DtoSoldCar;
import com.alimert.dto.DtoSoldCarIU;

import java.util.List;

public interface ISoldCarService {

    public DtoSoldCar buyCar(DtoSoldCarIU dtoSoldCarIU);

    public DtoSoldCar getSoldCarById(Long id);

}
