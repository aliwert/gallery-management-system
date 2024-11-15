package com.alimert.service;

import com.alimert.dto.DtoGalleristCar;
import com.alimert.dto.DtoGalleristCarIU;

public interface IGalleristCarService {

    public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);
}
