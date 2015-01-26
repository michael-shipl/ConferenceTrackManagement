package com.spl.conferencemanagement.model;

import java.util.Calendar;

import com.spl.conferencemanagement.resource.Resource;

/**
 * Each session contains multiple talks.
 * No talk title has numbers in it.
 * 
 * @author Michael.Shi
 *
 */
public class Talk implements Comparable<Talk>
{
	private String title = Resource.EMPTY;;
	private int duration = 0;
	private boolean scheduled = false;
	private String description = Resource.EMPTY;
	private Calendar StartTime;
	
	public Talk()
	{
	}
	
	public Talk(String _title, int _duration, String _description, boolean _scheduled)
	{
		title = _title;
		duration = _duration;
		scheduled = _scheduled;
		description = _description;
	}

	public Calendar getStartTime()
	{
		return StartTime;
	}

	public void setStartTime(Calendar startTime)
	{
		StartTime = startTime;
	}

	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public int getDuration()
	{
		return duration;
	}
	public void setDuration(int duration)
	{
		this.duration = duration;
	}
	public boolean isScheduled()
	{
		return scheduled;
	}
	public void setScheduled(boolean scheduled)
	{
		this.scheduled = scheduled;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	@Override
	public int compareTo(Talk o)
	{
		Talk talk = (Talk) o;
		
		if(this.duration > talk.duration)
		{
			return -1;
		}
		else if(this.duration < talk.duration)
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}

	@Override
    public int hashCode()
    {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result
	            + ((description == null) ? 0 : description.hashCode());
	    result = prime * result + duration;
	    result = prime * result + ((title == null) ? 0 : title.hashCode());
	    return result;
    }

	@Override
    public boolean equals(Object obj)
    {
	    if (this == obj)
		    return true;
	    if (obj == null)
		    return false;
	    if (getClass() != obj.getClass())
		    return false;
	    Talk other = (Talk) obj;
	    if (description == null)
	    {
		    if (other.description != null)
			    return false;
	    }
	    else
		    if (!description.equals(other.description))
			    return false;
	    if (duration != other.duration)
		    return false;
	    if (title == null)
	    {
		    if (other.title != null)
			    return false;
	    }
	    else
		    if (!title.equals(other.title))
			    return false;
	    return true;
    }
}
