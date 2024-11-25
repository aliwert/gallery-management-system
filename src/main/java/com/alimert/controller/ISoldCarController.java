package com.alimert.controller;

import com.alimert.dto.DtoSoldCar;
import com.alimert.dto.DtoSoldCarIU;

import java.util.List;

public interface ISoldCarController {
    public RootEntity<DtoSoldCar> buyCar(DtoSoldCarIU dtoSoldCarIU);

    public RootEntity<DtoSoldCar> getSoldCarById(Long id);

}
