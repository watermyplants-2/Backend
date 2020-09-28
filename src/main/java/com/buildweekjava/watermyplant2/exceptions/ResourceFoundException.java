package com.buildweekjava.watermyplant2.exceptions;

// resource is found when is not supposed to
public class ResourceFoundException
        extends RuntimeException
{
    public ResourceFoundException(String message)
    {
        super("Error from a Lambda School Application " + message);
    }
}