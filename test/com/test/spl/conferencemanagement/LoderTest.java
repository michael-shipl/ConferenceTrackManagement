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

import com.spl.conferencemanagement.Loder;
import com.spl.conferencemanagement.exception.IllegalArgumentException;
import com.spl.conferencemanagement.exception.IllegalTalkException;
import com.spl.conferencemanagement.model.Talk;

public class LoderTest
{

	private static Loder loder = Loder.getInstance();
	private static List<String> targetStringList;
	private static List<Talk> targetTalkList;
	public static String filePath = "D:\\ConferenceData.txt";
	public static String errorFilePath = "D:\\error.txt";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		targetStringList = new ArrayList<String>();
		targetStringList.add("Writing Fast Tests Against Enterprise Rails 60min");
		targetStringList.add("Overdoing it in Python 45min");
		targetStringList.add("Lua for the Masses 30min");
		targetStringList.add("Ruby Errors from Mismatched Gem Versions 45min");
		targetStringList.add("Common Ruby Errors 45min");
		targetStringList.add("Rails for Python Developers 5min");
		targetStringList.add("Communicating Over Distance 60min");
		targetStringList.add("Accounting-Driven Development 45min");
		targetStringList.add("Woah 30min");
		targetStringList.add("Sit Down and Write 30min");
		targetStringList.add("Pair Programming vs Noise 45min");
		targetStringList.add("Rails Magic 60min");
		targetStringList.add("Ruby on Rails: Why We Should Move On 60min");
		targetStringList.add("Clojure Ate Scala (on my project) 45min");
		targetStringList.add("Programming in the Boondocks of Seattle 30min");
		targetStringList.add("Ruby vs. Clojure for Back-End Development 30min");
		targetStringList.add("Ruby on Rails Legacy App Maintenance 60min");
		targetStringList.add("A World Without HackerNews 30min");
		targetStringList.add("User Interface CSS in Rails Apps 30min");

		targetTalkList = new ArrayList<Talk>();
		targetTalkList.add(new Talk("Writing Fast Tests Against Enterprise Rails", 60,
		        "Writing Fast Tests Against Enterprise Rails 60min", false));
		targetTalkList.add(new Talk("Overdoing it in Python", 45, "Overdoing it in Python 45min",
		        false));
		targetTalkList.add(new Talk("Lua for the Masses", 30, "Lua for the Masses 30min", false));
		targetTalkList.add(new Talk("Ruby Errors from Mismatched Gem Versions", 45,
		        "Ruby Errors from Mismatched Gem Versions 45min", false));
		targetTalkList.add(new Talk("Common Ruby Errors", 45, "Common Ruby Errors 45min", false));
		targetTalkList.add(new Talk("Rails for Python Developers", 5,
		        "Rails for Python Developers 5min", false));
		targetTalkList.add(new Talk("Communicating Over Distance", 60,
		        "Communicating Over Distance 60min", false));
		targetTalkList.add(new Talk("Accounting-Driven Development", 45,
		        "Accounting-Driven Development 45min", false));
		targetTalkList.add(new Talk("Woah", 30, "Woah 30min", false));
		targetTalkList.add(new Talk("Sit Down and Write", 30, "Sit Down and Write 30min", false));
		targetTalkList.add(new Talk("Pair Programming vs Noise", 45,
		        "Pair Programming vs Noise 45min", false));
		targetTalkList.add(new Talk("Rails Magic", 60, "Rails Magic 60min", false));
		targetTalkList.add(new Talk("Ruby on Rails: Why We Should Move On", 60,
		        "Ruby on Rails: Why We Should Move On 60min", false));
		targetTalkList.add(new Talk("Clojure Ate Scala (on my project)", 45,
		        "Clojure Ate Scala (on my project) 45min", false));
		targetTalkList.add(new Talk("Programming in the Boondocks of Seattle", 30,
		        "Programming in the Boondocks of Seattle 30min", false));
		targetTalkList.add(new Talk("Ruby vs. Clojure for Back-End Development", 30,
		        "Ruby vs. Clojure for Back-End Development 30min", false));
		targetTalkList.add(new Talk("Ruby on Rails Legacy App Maintenance", 60,
		        "Ruby on Rails Legacy App Maintenance 60min", false));
		targetTalkList.add(new Talk("A World Without HackerNews", 30,
		        "A World Without HackerNews 30min", false));
		targetTalkList.add(new Talk("User Interface CSS in Rails Apps", 30,
		        "User Interface CSS in Rails Apps 30min", false));

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
	public void testLoadFile()
	{
		File file = new File(filePath);
		List<String> acturalStringList = loder.load(file);
		for (int i = 0; i < acturalStringList.size(); i++)
		{
			assertEquals(targetStringList.get(i), acturalStringList.get(i));
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLoadFile_illegalArgument() throws IllegalArgumentException
	{
		loder.loadFile(errorFilePath);
	}

	@Test
	public void testLoad()
	{
		try
		{
			List<Talk> acturalTalkList = loder.loadFile(filePath);
			for (int i = 0; i < acturalTalkList.size(); i++)
			{
				assertEquals(targetTalkList.get(i), acturalTalkList.get(i));
			}
		}
		catch (IllegalArgumentException e)
		{
			e.printStackTrace();
		}

	}

}
