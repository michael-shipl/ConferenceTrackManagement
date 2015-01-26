package com.spl.conferencemanagement;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import com.spl.conferencemanagement.exception.IllegalArgumentException;
import com.spl.conferencemanagement.model.Session;
import com.spl.conferencemanagement.model.Talk;
import com.spl.conferencemanagement.model.Track;
import com.spl.conferencemanagement.resource.Resource;
import com.spl.conferencemanagement.util.PrintUtil;

/**
 * Conference Manager
 * 
 * 1. manage the conference
 * 
 * @author Michael.Shi
 *
 */
public class ConferenceManager
{
	public static ConferenceManager conferenceManager = new ConferenceManager();

	private ConferenceManager()
	{

	}

	public static ConferenceManager getInstance()
	{
		return conferenceManager;
	}

	/**
	 * Arrange the conferences
	 * 
	 * 1. load content from file
	 * 
	 * 2. do the arrangement
	 * 
	 * 3. print the result
	 * 
	 * @param filePath
	 * @throws IllegalArgumentException
	 */
	public List<Track> arrangeConference(String filePath)
	        throws IllegalArgumentException
	{
		// load conferences from the file
		List<Talk> talks = Loder.getInstance().loadFile(filePath);

		// arrange the conferences
		List<Track> tracks = arrange(talks);

		// print the result
		PrintUtil.printArrangedTrack(tracks);

		return tracks;
	}

	/**
	 * Arrange the talks
	 * 
	 * 1. sort the talks by descending oder in duration 
	 * 2. get the number of tracks 
	 * 3. arrange the morning session & afternoon session for each track
	 * 
	 * @param talks
	 * @return
	 */
	private List<Track> arrange(List<Talk> talks)
	{
		// sort the talk list by descending order in duration
		Collections.sort(talks);

		// get the number of tracks
		int totalTrackNum = getTotalTrackNum(talks);

		List<Track> result = new ArrayList<Track>();
		for (int i = 0; i < totalTrackNum; i++)
		{
			Session morningSession = arrageMorningSession(talks);

			Session afternoonSession = arrageAfternoonSession(talks);

			result.add(new Track(i, morningSession, afternoonSession));
		}

		return result;
	}

	/**
	 * Arrange morning session
	 * 
	 * note that:
	 * 1. morning session begin at: 9:00am, end by: 12:00 noon 
	 * 2. make "Lunch" belongs to morning session
	 * 
	 * @param talks
	 * @return
	 */
	private Session arrageMorningSession(List<Talk> talks)
	{
		Session session = new Session();
		List<Talk> targetMorningTalks = session.getTalks();

		// arrange the talks
		arrangeTalks(talks, targetMorningTalks, Resource.MORNING_MAX_DURATION);

		// make the time table right
		arrangeSessionTimeTable(targetMorningTalks,
		        Resource.MORNING_SESSION_START_POINT);
		
		// make "Lunch" event start time
		Calendar lunchStartTime = Calendar.getInstance();
		lunchStartTime.set(Calendar.HOUR_OF_DAY, Resource.LUNCH_START_POINT);
		lunchStartTime.set(Calendar.MINUTE, Resource.ZERO);
		lunchStartTime.set(Calendar.SECOND, Resource.ZERO);
		
		Talk lunch = new Talk(Resource.LUNCH, Resource.LUNCH_DURATION, Resource.LUNCH, true);
		lunch.setStartTime(lunchStartTime);
		
		// add "Lunch" as an event to morning session
		targetMorningTalks.add(lunch);
				
		session.setTalks(targetMorningTalks);

		return session;
	}

	/**
	 * Arrange afternoon session
	 * 
	 * note that:
	 * 1. afternoon session begin at: 13:00pm, must finish in time for the networking event.
	 * 	  The networking event can start no earlier than 4:00 and no later than 5:00.  
	 * 2. make "Networking Event" belongs to morning session
	 * 
	 * @param talks
	 * @return
	 */
	private Session arrageAfternoonSession(List<Talk> talks)
	{
		Session session = new Session();
		List<Talk> targetAfternoonTalks = session.getTalks();
		
		// arrange the talks
		arrangeTalks(talks, targetAfternoonTalks,
		        Resource.AFTERNOON_MAX_DURATION);

		// make the time table right
		Calendar cal = arrangeSessionTimeTable(targetAfternoonTalks,
		        Resource.AFTEERNOON_SESSION_START_POINT);
				
		
		// calculate the networking event start time
		if(cal.get(Calendar.HOUR_OF_DAY) < Resource.NETWORKING_EARLIEST_START_POINT)
		{
			cal.set(Calendar.HOUR_OF_DAY, Resource.NETWORKING_EARLIEST_START_POINT);
			cal.set(Calendar.MINUTE, Resource.ZERO);
			cal.set(Calendar.SECOND, Resource.ZERO);
		}
		
		Talk networkingEvnet = new Talk(Resource.NETWORKING_EVENT, 0,
		        Resource.NETWORKING_EVENT, true);
		networkingEvnet.setStartTime(cal);
		
		// add NetWorking Event to afternoon session
		targetAfternoonTalks.add(networkingEvnet);

		session.setTalks(targetAfternoonTalks);
		return session;
	}

	/**
	 * Make the time table of each session right
	 * 
	 * @param targetMorningTalks
	 * @param morningSessionStartPoint
	 */
	private Calendar arrangeSessionTimeTable(List<Talk> targetMorningTalks,
	        int sessionStartPoint)
	{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, sessionStartPoint);
		cal.set(Calendar.MINUTE, Resource.ZERO);
		cal.set(Calendar.SECOND, Resource.ZERO);

		for (Talk talk : targetMorningTalks)
		{
			talk.setStartTime(cal);
			cal = getNextTalkStartTime(talk);
		}
		
		return cal;
	}

	/**
	 * Get the arranged time of next talk
	 * 
	 * @param talk
	 * @return
	 */
	private Calendar getNextTalkStartTime(Talk talk)
	{
		Calendar cal = (Calendar) talk.getStartTime().clone();

		cal.add(Calendar.MINUTE, talk.getDuration());

		return cal;
	}
	
	/**
	 * Arrange the talks
	 * 
	 * @param talks
	 * @param targetTalks
	 * @param maxDuration
	 */
	private void arrangeTalks(List<Talk> talks, List<Talk> targetTalks,
	        int maxDuration)
	{
		int totalDuration = 0;

		for (Talk talk : talks)
		{
			if (talk.getDuration() + totalDuration > maxDuration
			        || talk.isScheduled())
			{
				continue;
			}

			totalDuration += talk.getDuration();
			targetTalks.add(talk);
			talk.setScheduled(true);
		}
	}

	/**
	 * Calculate the number of tracks
	 * 
	 * @param talks
	 * @return
	 */
	private int getTotalTrackNum(List<Talk> talks)
	{
		int totalTalkDuration = 0;

		if (null == talks || talks.isEmpty())
		{
			return 0;
		}

		for (Talk talk : talks)
		{
			totalTalkDuration += talk.getDuration();
		}

		return (int) Math.ceil(((double) totalTalkDuration / (double) Resource.MIN_OF_DAY));
	}
}
