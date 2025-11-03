package com.minorProject.bloodBank.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    //private static final long serialVersionUID = 1L;
    private String resourceName;   //searching for what? like bloodBank? donor?
    private String fieldName;      //searching for which field?
    private Object fieldValue;     //which field(id/name) I'm searching

//	public ResourceNotFoundException(String message)
//	{
//		super(message);	    //donor not found with id 99
//	}

    //creating constructor
    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue)
    {
        //super(String.format("%s not found with %s : '%s'" , resourceName , fieldName , fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

}
