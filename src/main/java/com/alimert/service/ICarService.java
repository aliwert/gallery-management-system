package com.alimert.service;

import com.alimert.dto.DtoCar;
import com.alimert.dto.DtoCarIU;

public interface ICarService {

    public DtoCar saveCar(DtoCarIU dtoCarIU);
}
