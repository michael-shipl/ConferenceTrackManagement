package com.spl.conferencemanagement.resource;

/**
 * Constants
 * 
 * @author Michael.Shi
 *
 */
public class Resource
{
	public static final String MIN_SUFFIX = "min"; // suffix of "min"
	
	public static final String LIGHTNING_SUFFIX = "lightning"; // suffix of "lightning"
	
	public static final String LIGHTNING_2_MIN = "5" + MIN_SUFFIX; // lightning = 5min
	
	public static final int MIN_OF_DAY = 60 * 7; 
	
	public static final String EMPTY = "";
	
	public static final String REGEX = "\\s[0-9]"; // No talk title has numbers in it.
	
	public static final int ZERO = 0;
	
	public static final int MAX_DURATION = 240; // max duration of each talk
	
	public static final int MORNING_MAX_DURATION = 180; // max duration of morning session 
	
	public static final int AFTERNOON_MAX_DURATION = 240; // max duration of afternoon session 
	
	public static final String DATE_FORMAT = "hh:mma";
	
	public static final int MORNING_SESSION_START_POINT = 9; // morning session start at 9:00am
	
	public static final int AFTEERNOON_SESSION_START_POINT = 13; // afternoon session start at 13:00
	
	public static final String LUNCH = "LUNCH";
	
	public static final int LUNCH_DURATION = 60;
	
	public static final int LUNCH_START_POINT = 12;
	
	public static final String NETWORKING_EVENT = "Networking Event";
	
	// The networking event can start no earlier than 4:00 and no later than 5:00.
	public static final int NETWORKING_EARLIEST_START_POINT = 16; 
	
	// The networking event can start no earlier than 4:00 and no later than 5:00.
	public static final int NETWORKING_LATEST_START_POINT = 17;
	
}
