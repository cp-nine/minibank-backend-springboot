package com.nano.minibank.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserException.class);

    @SuppressWarnings("rawtypes")
    @org.springframework.web.bind.annotation.ExceptionHandler(value = UserException.class)
    public ResponseEntity<CommonResponse> catchUserException(UserException e){
        LOGGER.error(e.getMessage());
        LOGGER.warn(e.getMessage());
        LOGGER.info(e.getMessage());
        LOGGER.debug(e.getMessage());
        return new ResponseEntity<CommonResponse>(new CommonResponse(e.getCode(), e.getDescription()), HttpStatus.OK);
    }

    @SuppressWarnings("rawtypes")
    @org.springframework.web.bind.annotation.ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<CommonResponse> catchEntityNotFoundException(EntityNotFoundException e){
        LOGGER.error(e.getMessage());
        LOGGER.warn(e.getMessage());
        LOGGER.info(e.getMessage());
        LOGGER.debug(e.getMessage());
        return new ResponseEntity<CommonResponse>(new CommonResponse(e.getCode(), e.getDescription()), HttpStatus.OK);
    }

}
