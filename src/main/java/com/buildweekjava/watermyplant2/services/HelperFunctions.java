package com.buildweekjava.watermyplant2.services;

import com.buildweekjava.watermyplant2.models.ValidationError;

import java.util.List;

public interface HelperFunctions
{

    List<ValidationError> getConstraintViolation(Throwable cause);

    boolean isAuthorizedToMakeChange(String username);
}
