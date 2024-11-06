package com.alimert.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoGallerist extends DtoBase {

    private String firstName;

    private String lastName;

    private DtoAddress address;
}
