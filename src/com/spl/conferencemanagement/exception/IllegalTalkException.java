package com.spl.conferencemanagement.exception;

public class IllegalTalkException extends Exception
{
	private static final long serialVersionUID = 1L;

	public IllegalTalkException(String errorMsg)
	{
		super(errorMsg);
	}

}
