package com.spl.conferencemanagement.exception;

public class IllegalArgumentException extends Exception
{
	private static final long serialVersionUID = 1L;

	public IllegalArgumentException(String errorMsg)
	{
		super(errorMsg);
	}
}
