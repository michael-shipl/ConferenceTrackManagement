package com.spl.conferencemanagement.bootstrap;

import com.spl.conferencemanagement.ConferenceManager;
import com.spl.conferencemanagement.exception.IllegalArgumentException;

/**
 * Problem Two: Conference Track Management
 * 
 * 
 * Solution:
 * 
 * Step1. read content from given file
 * 
 * Step2. parse the content to talk list
 * 
 * Step3. sort the talk list by descending order in duration of each talk
 * 
 * Step4. arrange the morning session of each track
 * 
 * Step5. arrange the afternoon session of each track
 * 
 * Step6. print the result
 * 
 * 
 * @author Michael.Shi
 * 
 */
public class Main
{
	/**
	 * Start from here
	 * 
	 * @param args path of input file
	 */
	public static void main(String[] args)
	{
		String filePath = "resource/Input";

		ConferenceManager conferenceManager = ConferenceManager.getInstance();
		
		try
		{
			conferenceManager.arrangeConference(filePath);
		}
		catch (IllegalArgumentException e)
		{
			System.err.println(e.getMessage());
		}

	}
}
