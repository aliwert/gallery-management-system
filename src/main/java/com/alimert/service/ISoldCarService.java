package com.alimert.service;

import com.alimert.dto.DtoSoldCar;
import com.alimert.dto.DtoSoldCarIU;

public interface ISoldCarService {

    public DtoSoldCar buyCar(DtoSoldCarIU dtoSoldCarIU);

}
