package com.buildweekjava.watermyplant2.exceptions;

// error when a resource is not found when its supposed to be
public class ResourceNotFoundException
        extends RuntimeException
{
    public ResourceNotFoundException(String message)
    {
        super("Error from a Lambda School Application " + message);
    }
}