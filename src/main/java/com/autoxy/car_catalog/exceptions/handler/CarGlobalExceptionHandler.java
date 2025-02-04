package com.autoxy.car_catalog.exceptions.handler;

import com.autoxy.car_catalog.dto.CarErrorResponseDto;
import com.autoxy.car_catalog.exceptions.CarNotFoundException;
import com.autoxy.car_catalog.exceptions.CarStatusValueException;
import com.autoxy.car_catalog.exceptions.NoCarExistsException;
import org.hibernate.PropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.format.DateTimeParseException;

@ControllerAdvice
public class CarGlobalExceptionHandler {

    @ExceptionHandler(value = PropertyValueException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<CarErrorResponseDto> handlePropertyValueException(PropertyValueException pve) {
        CarErrorResponseDto errorResponse = new CarErrorResponseDto("Car attributes cannot be null!");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<CarErrorResponseDto> handleNullPointerExceptionDuringValidation(NullPointerException npe) {
        CarErrorResponseDto errorResponse = new CarErrorResponseDto("Car attributes cannot be null!");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = CarStatusValueException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<CarErrorResponseDto> handleCarStatusValueExceptionDuringValidation(CarStatusValueException csve) {
        CarErrorResponseDto errorResponse = new CarErrorResponseDto("Car status can be only \"available\" or \"sold\"!");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<CarErrorResponseDto> handleHttpMessageNotReadableException(HttpMessageNotReadableException hmnre) {
        CarErrorResponseDto errorResponse = new CarErrorResponseDto("Car price must be a valid number! It cannot contain letters.");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = DateTimeParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<CarErrorResponseDto> handleDateTimeParseExceptionDuringValidation(DateTimeParseException dtpe) {
        CarErrorResponseDto errorResponse = new CarErrorResponseDto("The year of production must be a valid year! It can contain only digits.");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = CarNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<CarErrorResponseDto> handleCarNotFoundException(CarNotFoundException cnfe) {
        CarErrorResponseDto errorResponse = new CarErrorResponseDto("the Car with the specified id doesn't exist!");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = NoCarExistsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<CarErrorResponseDto> handleNoCarExistsException(NoCarExistsException ncee) {
        CarErrorResponseDto errorResponse = new CarErrorResponseDto("No Car exists at the moment!");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

}
