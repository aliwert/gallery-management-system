package com.alimert.handler;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exception<E> {
    private String path;

    private Date date;

    private String hostName;

    private E message;
}
