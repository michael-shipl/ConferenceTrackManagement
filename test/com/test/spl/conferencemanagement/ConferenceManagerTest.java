package com.test.spl.conferencemanagement;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.spl.conferencemanagement.ConferenceManager;
import com.spl.conferencemanagement.exception.IllegalArgumentException;
import com.spl.conferencemanagement.model.Session;
import com.spl.conferencemanagement.model.Talk;
import com.spl.conferencemanagement.model.Track;

public class ConferenceManagerTest
{

	private static ConferenceManager conferenceManager = ConferenceManager
	        .getInstance();
	private static List<Track> targetTrackList;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		Session morningSession1 = new Session();
		Session afternoonSession1 = new Session();
		targetTrackList = new ArrayList<Track>();
		
		Track track1 = new Track(0);
		track1.setMorningSession(morningSession1);
		track1.setAfternoonSession(afternoonSession1);
		
		List<Talk> morningTalks1 = new ArrayList<Talk>();
		morningTalks1.add(new Talk("Writing Fast Tests Against Enterprise Rails", 60,
		        "Writing Fast Tests Against Enterprise Rails 60min", false));
		morningTalks1.add(new Talk("Communicating Over Distance", 60,
		        "Communicating Over Distance 60min", false));
		morningTalks1.add(new Talk("Rails Magic", 60, "Rails Magic 60min", false));
		morningTalks1.add(new Talk("LUNCH", 60,
		        "LUNCH", false));
		morningSession1.setTalks(morningTalks1);
		
		List<Talk> afternoonTalks1 = new ArrayList<Talk>();
		afternoonTalks1.add(new Talk("Ruby on Rails: Why We Should Move On", 60, "Ruby on Rails: Why We Should Move On 60min", false));
		afternoonTalks1.add(new Talk("Ruby on Rails Legacy App Maintenance", 60,
		        "Ruby on Rails Legacy App Maintenance 60min", false));
		afternoonTalks1.add(new Talk("Overdoing it in Python", 45,
		        "Overdoing it in Python 45min", false));
		afternoonTalks1.add(new Talk("Ruby Errors from Mismatched Gem Versions", 45,
		        "Ruby Errors from Mismatched Gem Versions 45min", false));
		afternoonTalks1.add(new Talk("Lua for the Masses", 30,
		        "Lua for the Masses 30min", false));
		afternoonTalks1.add(new Talk("Networking Event", 0, "Networking Event", false));
		afternoonSession1.setTalks(afternoonTalks1);
		
		
		
		Session morningSession2 = new Session();
		Session afternoonSession2 = new Session();
		Track track2 = new Track(1);
		track2.setMorningSession(morningSession2);
		track2.setAfternoonSession(afternoonSession2);
		
		
		List<Talk> morningTalks = new ArrayList<Talk>();
		morningTalks.add(new Talk("Common Ruby Errors", 45, "Common Ruby Errors 45min", false));
		morningTalks.add(new Talk("Accounting-Driven Development", 45,
		        "Accounting-Driven Development 45min", false));
		morningTalks.add(new Talk("Pair Programming vs Noise", 45,
		        "Pair Programming vs Noise 45min", false));
		morningTalks.add(new Talk("Clojure Ate Scala (on my project)", 45,
		        "Clojure Ate Scala (on my project) 45min", false));
		morningTalks.add(new Talk("LUNCH", 60,
		        "LUNCH", false));
		morningSession2.setTalks(morningTalks);
		
		List<Talk> afternoonTalks = new ArrayList<Talk>();
		afternoonTalks.add(new Talk("Woah", 30, "Woah 30min", false));
		afternoonTalks.add(new Talk("Sit Down and Write", 30, "Sit Down and Write 30min", false));
		afternoonTalks.add(new Talk("Programming in the Boondocks of Seattle", 30,
		        "Programming in the Boondocks of Seattle 30min", false));
		afternoonTalks.add(new Talk("Ruby vs. Clojure for Back-End Development", 30,
		        "Ruby vs. Clojure for Back-End Development 30min", false));
		afternoonTalks.add(new Talk("A World Without HackerNews", 30,
		        "A World Without HackerNews 30min", false));
		afternoonTalks.add(new Talk("User Interface CSS in Rails Apps", 30,
		        "User Interface CSS in Rails Apps 30min", false));
		afternoonTalks.add(new Talk("Rails for Python Developers", 5,
		        "Rails for Python Developers 5min", false));
		afternoonTalks.add(new Talk("Networking Event", 0, "Networking Event", false));
		afternoonSession2.setTalks(afternoonTalks);
		
		targetTrackList.add(track1);
		targetTrackList.add(track2);
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
	}

	@Before
	public void setUp() throws Exception
	{
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testArrangeConference()
	{
		try
        {
	        List<Track> acturalTrackList = conferenceManager.arrangeConference(LoderTest.filePath);
			for (int i = 0; i < acturalTrackList.size(); i++)
			{
				assertEquals(targetTrackList.get(i), acturalTrackList.get(i));
			}
        }
        catch (IllegalArgumentException e)
        {
	        e.printStackTrace();
        }
	}

}
