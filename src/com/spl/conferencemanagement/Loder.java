package com.spl.conferencemanagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.spl.conferencemanagement.exception.IllegalArgumentException;
import com.spl.conferencemanagement.model.Talk;
import com.spl.conferencemanagement.resource.ErrorString;
import com.spl.conferencemanagement.resource.Resource;

/**
 * Loader:
 * 
 * 1. load the content from file
 * 2. parse the items to Talk object
 * 
 * @author Michael.Shi
 * 
 */
public class Loder
{
	private static final Loder loader = new Loder();

	private Loder()
	{

	}

	public static Loder getInstance()
	{
		return loader;
	}

	/**
	 * Load content from a given file, then parse the content to a list of Talk object
	 * 
	 * @param filePath
	 * @return
	 * @throws IllegalArgumentException
	 */
	public List<Talk> loadFile(String filePath) throws IllegalArgumentException
	{
		if (!(null != filePath || Resource.EMPTY.equals(filePath)))
		{
			throw new IllegalArgumentException(ErrorString.INPUT_ARGUMENT_EMPTY);
		}

		File file = new File(filePath);
		if (!file.exists())
		{
			throw new IllegalArgumentException(ErrorString.FILE_NOT_FOUND);
		}

		// load file and transfer to content List
		List<String> contentList = load(file);

		// parse String items to Talk list
		return parse2Talks(contentList);

	}

	/**
	 * Load file line by line
	 * 
	 * note that: 
	 * 1. exclude the empty line 
	 * 2. replace "lightning" to "5min"
	 * 
	 * @param file
	 * @return
	 */
	public List<String> load(File file)
	{
		BufferedReader bufferedReader = null;
		List<String> content = new ArrayList<String>();
		try
		{
			bufferedReader = new BufferedReader(new FileReader(file));

			String tempString;
			while ((tempString = bufferedReader.readLine()) != null)
			{
				// exclude empty line
				if (Resource.EMPTY.equals(tempString))
				{
					continue;
				}

				// replace "lightning" to "5min"
				tempString = tempString.replace(Resource.LIGHTNING_SUFFIX,
				        Resource.LIGHTNING_2_MIN);
				
				content.add(tempString);
			}
		}
		catch (IOException e)
		{
			System.err.println(ErrorString.READ_FILE_ERROR + e.getMessage());
		}
		finally
		{
			// close the reader
			if (bufferedReader != null)
			{
				try
				{
					bufferedReader.close();
				}
				catch (IOException e)
				{
					System.err.println(ErrorString.READ_FILE_ERROR
					        + e.getMessage());
				}
			}
		}

		return content;
	}

	/**
	 * Parse String items to Talk list
	 * 
	 * 1. use regex "\\s[0-9]" to split each line, to get the title & duration of each talk
	 * 
	 * 2. find the last index of "min", to get the number of duration
	 * 
	 * @param contentList content of file, in string
	 * @return talk list
	 * @throws IllegalArgumentException
	 */
	private List<Talk> parse2Talks(List<String> contentList)
	        throws IllegalArgumentException
	{
		List<Talk> talkList = new ArrayList<Talk>();

		for (String line : contentList)
		{
			line = line.trim();
			
			// check the validation of each line of string
			int matchedIndex = validateTalk(line);
			
			String title = getTitle(line, matchedIndex);
			int duration = getDuration(line, matchedIndex);

			talkList.add(new Talk(title, duration, line, false));
		}

		return talkList;
	}

	/**
	 * Get the title of the each conference
	 * 
	 * note that: conference title does not contain the time
	 * 
	 * @param line
	 * @param matchedIndex
	 * @return the tile of conference
	 * @throws IllegalArgumentException
	 */
	private String getTitle(String line, int matchedIndex)
	        throws IllegalArgumentException
	{
		String title = line.substring(Resource.ZERO, matchedIndex);

		// check the validation of title
		if (null == title || Resource.EMPTY.equals(title))
		{
			throw new IllegalArgumentException(ErrorString.ILLEGALTASK + line);
		}
		return title;
	}

	/**
	 * Check whether has numerics
	 * 
	 * use regex "\\s[0-9]" to split each line, since "No talk title has numbers in it."
	 * 
	 * @param line
	 * @return
	 * @throws IllegalArgumentException
	 */
	private int validateTalk(String line) throws IllegalArgumentException
	{
		int matchedIndex = -1;
		Pattern pattern = Pattern.compile(Resource.REGEX);
		Matcher matcher = pattern.matcher(line);
		if (matcher.find())
		{
			matchedIndex = matcher.start();
		}

		// check whether has valid numerics in the line
		if (matchedIndex < 0)
		{
			throw new IllegalArgumentException(ErrorString.ILLEGALTASK + line);
		}

		return matchedIndex;
	}

	/**
	 * Get the duration of the talk
	 * 
	 * @param line
	 * @param matchedIndex
	 * @return
	 * @throws IllegalArgumentException
	 */
	private int getDuration(String line, int matchedIndex)
	        throws IllegalArgumentException
	{
		String durationStr = line.substring(matchedIndex + 1);

		// check the validation of the duration
		if (!durationStr.endsWith(Resource.MIN_SUFFIX))
		{
			throw new IllegalArgumentException(ErrorString.ILLEGALTASK + line);
		}

		int duration = Integer.parseInt(durationStr.substring(0,
		        durationStr.indexOf(Resource.MIN_SUFFIX)));
		
		// duration of each conference between (0-240]
		if (duration > Resource.MAX_DURATION || duration <= Resource.ZERO)
		{
			throw new IllegalArgumentException(ErrorString.ILLEGALTASK + line);
		}

		return duration;
	}
}
