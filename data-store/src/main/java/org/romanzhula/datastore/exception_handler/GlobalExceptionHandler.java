package org.romanzhula.datastore.exception_handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SensorNotFoundException.class)
    public String sensorNotFound(SensorNotFoundException exception) {
        return "Sensor NOT FOUND!";
    }

    @ExceptionHandler(Exception.class)
    public String serverGlobalException(Exception exception) {
        exception.printStackTrace();
        return "An internal server error occurred.";
    }

}
