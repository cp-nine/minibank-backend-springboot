package com.nano.minibank.helpers;

public class EntityNotFoundException extends UserException {

    public EntityNotFoundException(String code, String description) {
        super(code, description);
    }
}
