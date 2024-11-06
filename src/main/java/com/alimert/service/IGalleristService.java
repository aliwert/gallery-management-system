package com.alimert.service;

import com.alimert.dto.DtoGallerist;
import com.alimert.dto.DtoGalleristIU;

public interface IGalleristService {

    public DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU);
}
