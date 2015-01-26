package com.spl.conferencemanagement.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The conference has multiple tracks,
 * each of which has a morning and afternoon session
 * 
 * @author Michael.Shi
 *
 */
public class Track
{
	private int number;
	private Session morningSession;
	private Session afternoonSession;

	public Track()
	{
		
	}
	
	public Track(int _number)
	{
		this.number = _number;
		morningSession = new Session();
		afternoonSession = new Session();
	}
	
	public Track(int number, Session morningSession, Session afternoonSession)
	{
		super();
		this.number = number;
		this.morningSession = morningSession;
		this.afternoonSession = afternoonSession;
	}

	public int getNumber()
	{
		return number;
	}

	public void setNumber(int number)
	{
		this.number = number;
	}


	public Session getMorningSession()
	{
		return morningSession;
	}

	public void setMorningSession(Session morningSession)
	{
		this.morningSession = morningSession;
	}

	public Session getAfternoonSession()
	{
		return afternoonSession;
	}

	public void setAfternoonSession(Session afternoonSession)
	{
		this.afternoonSession = afternoonSession;
	}

	public List<Talk> getTasks()
	{
		List<Talk> talks = new ArrayList<Talk>();
		talks.addAll(this.morningSession.getTalks());	
		talks.addAll(this.afternoonSession.getTalks());	
		return talks;
	}
	
	public List<Session> getSessions()
	{
		List<Session> sessions = new ArrayList<Session>();
		sessions.add(morningSession);
		sessions.add(afternoonSession);
		return sessions;
	}

	@Override
    public int hashCode()
    {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((afternoonSession == null) ? 0 : afternoonSession.hashCode());
	    result = prime * result + ((morningSession == null) ? 0 : morningSession.hashCode());
	    result = prime * result + number;
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
	    Track other = (Track) obj;
	    if (afternoonSession == null)
	    {
		    if (other.afternoonSession != null)
			    return false;
	    }
	    else
		    if (!afternoonSession.equals(other.afternoonSession))
			    return false;
	    if (morningSession == null)
	    {
		    if (other.morningSession != null)
			    return false;
	    }
	    else
		    if (!morningSession.equals(other.morningSession))
			    return false;
	    if (number != other.number)
		    return false;
	    return true;
    }
	
	
	
}

