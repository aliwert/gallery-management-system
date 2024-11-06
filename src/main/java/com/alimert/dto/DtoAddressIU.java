package com.alimert.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class DtoAddressIU { // for insert & update


    @NotEmpty
    private String city;

    @NotEmpty
    private String district;

    @NotEmpty
    private String neighborhood;

    @NotEmpty
    private String street;
}
