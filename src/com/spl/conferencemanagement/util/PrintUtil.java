package com.spl.conferencemanagement.util;

import java.text.SimpleDateFormat;
import java.util.List;

import com.spl.conferencemanagement.model.Session;
import com.spl.conferencemanagement.model.Talk;
import com.spl.conferencemanagement.model.Track;
import com.spl.conferencemanagement.resource.Resource;

/**
 * Help to print the result
 * 
 * @author Michael.Shi
 *
 */
public class PrintUtil
{
	/**
	 * Print all the talks of each Track
	 * 
	 * @param tracks
	 */
	public static void printArrangedTrack(List<Track> tracks)
	{
		int trackNum = 1;
		for (Track track : tracks)
		{
			System.out.println("Track " + trackNum + ": ");
			
			printArrangedSessions(track.getSessions());
			
			System.out.println();
			trackNum++;
		}
	}

	private static void printArrangedSessions(List<Session> sessions)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat(Resource.DATE_FORMAT);
		for(Session session : sessions)
		{
			for(Talk talk :session.getTalks())
			{
				System.out.print(dateFormat.format(talk.getStartTime().getTime()) + " ");
				System.out.println(talk.getDescription());
			}
		}
	}

}
