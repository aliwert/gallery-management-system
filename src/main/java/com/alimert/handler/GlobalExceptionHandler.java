package com.alimert.handler;


import com.alimert.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {BaseException.class})
    public ResponseEntity<ApiError<?>> handleBaseException(BaseException e, WebRequest request) {
        return ResponseEntity.badRequest().body(createApiError(e.getMessage(), request));
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ApiError<Map<String, List<String>>>> handleMethodArgNotValidException(MethodArgumentNotValidException e, WebRequest request) {
        Map<String, List<String>> map = new HashMap<>();
        for (ObjectError allError : e.getBindingResult().getAllErrors()) {
            String field = ((FieldError) allError).getField();

            if (map.containsKey(field)) {
                map.put(field, addValue(map.get(field), allError.getDefaultMessage()));
            }else{
                map.put(field, addValue(new ArrayList<>(), allError.getDefaultMessage()));
            }
        }

       return ResponseEntity.badRequest().body(createApiError(map, request));
    }
    private List<String> addValue(List<String> list, String value) {
        list.add(value);
        return list;
    }


    private String getHostName() {
        try {
            Inet4Address.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        return "";
    }
    public <E> ApiError<E> createApiError(E message, WebRequest request) {
        ApiError<E> apiError = new ApiError<>();
        apiError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        Exception<E> exception = new Exception<>();
        exception.setPath(request.getDescription(false));
        exception.setDate(new Date());
        exception.setMessage(message);
        exception.setHostName(getHostName());

        apiError.setException(exception);


        return apiError;
    }
}
