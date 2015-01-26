package com.spl.conferencemanagement.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Each Track has morning session & afternoon session Each Session has some
 * talks
 * 
 * @author Michael.Shi
 * 
 */
public class Session
{
	private List<Talk> talks;

	public Session()
	{
		talks = new ArrayList<Talk>();
	}

	public List<Talk> getTalks()
	{
		return talks;
	}

	public void setTalks(List<Talk> talks)
	{
		this.talks = talks;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((talks == null) ? 0 : talks.hashCode());
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
		Session other = (Session) obj;
		if (talks == null)
		{
			if (other.talks != null)
				return false;
		}
		else if (!talks.equals(other.talks))
				return false;
		
		return true;
	}

}
