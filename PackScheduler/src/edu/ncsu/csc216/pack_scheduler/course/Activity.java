package edu.ncsu.csc216.pack_scheduler.course;

/** 
 * abstract class for creating activity objects
 * both courses and events will be created from this
 * super class, and it will maintain, title, meetingdays
 * start time, and end time
 * 
 * @author William
 *
 */
abstract public class Activity implements Conflict {

	/** the latest a class can be */
	private static final int UPPER_TIME = 2400;
	/** the number of minutes in an hour */
	private static final int UPPER_HOUR = 60;
	/** Course's title. */
	private String title;
	/** Course's meeting days */
	private String meetingDays;
	/** Course's starting time */
	private int startTime;
	/** Course's ending time */
	private int endTime;
	
	

	public abstract int compareTo(Course c);
	
	/**
	 * Creates an abstract Short array that is populated with the Activity's fields
	 * 
	 * @return String[]
	 * 			An array populated by State of an Activity object
	 */	
	public abstract String[] getShortDisplayArray();
	
	/**
	 * Creates an abstract long array that is populated with the Activity's fields
	 * 
	 * @return String[]
	 * 			An array populated by State of an Activity object
	 */
	public abstract String[] getLongDisplayArray();
	/**
	 * Tests to see if an Activity is a duplicate.
	 * 
	 * @param activity
	 * 			the object provided, to be tested
	 * @return boolean
	 * 			returns true if the Activity object is not unique or false if it is  	
	 */
	public abstract boolean isDuplicate(Activity activity);
	
	/**
	 * Creates an Activity with the given title, meetingDays, startTime and endTime
	 * @param title
	 * 			the title of an event
	 * @param meetingDays
	 * 			the meetingDays for an event
	 * @param startTime 
	 *			the start time for an event
	 * @param endTime
	 * 			the time that an event ends
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		//title, meetingDays, start, end
		setTitle(title);
		setMeetingDays(meetingDays);
		setActivityTime(startTime, endTime);
	}

	/**
	 * Checks to see if a possible conflicting activity has the same days and time
	 * as a different activity.
	 * 
	 * @param possibleConflictingActivity
	 * 			this is the activity that will be checked against the original
	 * 			activity
	 * 
	 * @throws ConflictException
	 * 			Throws a conflict exception if the meeting days of two activities
	 * 			are the same and their meeting times overlap.				
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		//get meeting days for each activity
		String thisMeetingDays = this.meetingDays;
		String thatMeetingDays = possibleConflictingActivity.meetingDays;
		char thisDay = 'o';
		char thatDay = 'o';
		int thisMeetingDaysLen = thisMeetingDays.length();
		int thatMeetingDaysLen = thatMeetingDays.length();
		boolean sameDay = false;
		boolean isArranged = false;
		
		for (int i = 0; i < thisMeetingDaysLen; i++) {
			thisDay = thisMeetingDays.charAt(i);
			for (int j = 0; j < thatMeetingDaysLen; j++) {
				thatDay = thatMeetingDays.charAt(j);
				if (thisDay == thatDay) {
					sameDay = true;
				}
				if (thisDay == 'A' && thatDay == 'A') {
					isArranged = true;
				}
			}
		}
		
		if((sameDay && !isArranged) && ((possibleConflictingActivity.startTime >= this.startTime && possibleConflictingActivity.startTime <= this.endTime)
				|| (possibleConflictingActivity.endTime <= this.endTime && possibleConflictingActivity.endTime >= this.startTime))) {
			throw new ConflictException();
		} else if ((sameDay && !isArranged) && ((this.startTime >= possibleConflictingActivity.startTime && this.startTime <= possibleConflictingActivity.endTime)
				|| (this.endTime <= possibleConflictingActivity.endTime && this.endTime >= possibleConflictingActivity.startTime))) {
			throw new ConflictException();
		}
	}
	
	
	/**
	 * Returns the Activity's title.
	 * @return title
	 * 		the title of the activity
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Event's title.
	 * @param title 
	 * 		the string to set the title to
	 * @throws IllegalArgumentException 
	 * 		if title is null or empty
	 */
	public void setTitle(String title) {
		
		if (title == null || title.equals("")) {
			throw new IllegalArgumentException ("title cannot be empty or null");	
		} else {
			this.title = title;
		}
	}

	/**
	 * Returns the activity's meeting days.
	 * @return meetingDays
	 * 			the days the Activity meets 
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Sets the Activity's meeting days.
	 * @param meetingDays 
	 * 			the days the activity meets
	 */
	public void setMeetingDays(String meetingDays) {
		this.meetingDays = meetingDays;
	}

	/**
	 * Returns the Activity's start time.
	 * @return startTime
	 * 			the time the Activity starts
	 * 
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the Activity's end time.
	 * @return endTime
	 * 			the time the Activity ends
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * sets the Activity's time
	 * @param startTime
	 * 			the time the activity begins
	 * @param endTime
	 * 			the time activity ends
	 * @throws IllegalArgumentException 
	 * 			if certain time criteria are not met
	 * 			i.e. time must begin after 12:00am and end before 11:59pm
	 */
	public void setActivityTime(int startTime, int endTime) {
		
		if (endTime > (UPPER_TIME - 1) || endTime < 0000) {
			throw new IllegalArgumentException ("end-time must be between 23:59 and 00:00"); 
		}
		
		if (startTime > (UPPER_TIME - 1) || startTime < 0000) {
			throw new IllegalArgumentException ("start-time must be between 23:59 and 00:00"); 
		}
		
		int timeCorrection = 100;
		int correctedStart = startTime % timeCorrection;
		int correctedEnd = endTime % timeCorrection;
		correctedStart = correctedStart - UPPER_HOUR;
		correctedEnd = correctedEnd - UPPER_HOUR;
		
		if (correctedStart >= 0 || correctedEnd >= 0) {
			throw new IllegalArgumentException ("Minutes cannot be above 59"); 
		}
	
		if (endTime < startTime) {
			throw new IllegalArgumentException ("end-time must be before start-time"); 
		}
		
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * Changes military time into traditional time of Activity
	 * meeting time, with AM and PM
	 * 
	 * @return String
	 * 			Time in string form of when an activity will meet
	 */
	public String getMeetingString() {
		
		int start = startTime;
		int end = endTime;
		int startMin = start % 100;
		int endMin = end % 100;
		int startHour = start - startMin;
		int endHour = end - endMin;
		
		boolean startAM = true;
		boolean endAM = true;
		
		if (endHour > 1159) {
			if (endHour >= 1200 && endHour < 1300) {
				endAM = false;
			} else {
			endHour = endHour - 1200;
			endAM = false;
			}
		}
		
		if (startHour > 1159) {
			if (startHour >= 1200 && startHour < 1300) {
				startAM = false;
			} else {
				
			startHour = startHour - 1200;
			startAM = false;
			}
		}
		
		startHour = startHour / 100;
		endHour = endHour / 100;
		
		String startMinString = "";
		String endMinString = "";
		if (startMin < 10) {
			startMinString = "0" + Integer.toString(startMin);
		} else {
			startMinString = Integer.toString(startMin);
		}
		if (endMin < 10) {
			endMinString = "0" + Integer.toString(endMin);
		} else {
			endMinString = Integer.toString(endMin);
		}
		
		if (meetingDays.equals("A")){
			return "Arranged";
		} else {
			if (startAM && endAM) {
				return meetingDays + " " + startHour + ":" + startMinString + "AM-" + endHour + ":" + endMinString + "AM";  
			} else if (startAM && !endAM) {
				return meetingDays + " " + startHour + ":" + startMinString + "AM-" + endHour + ":" + endMinString + "PM";  
			} else {
				return meetingDays + " " + startHour + ":" + startMinString + "PM-" + endHour + ":" + endMinString + "PM";  
			}
		}	
	}
	
	

	/** 
	 * generates a hash code for an Activity object
	 * 
	 * @return int
	 * 			integer value of hashed fields from the Activity object		
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + meetingDays.hashCode();
		result = prime * result + startTime;
		result = prime * result + title.hashCode();
		return result;
	}

	/**
	 * Returns if an object is equal to a reference object
	 * 
	 * @param obj
	 * 			the object to be checked against the reference
	 * @return boolean 
	 * 			whether or not two objects are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		//activity cannot be null
		//if (obj == null)
			//return false;
		if (getClass() != obj.getClass())
			return false;
		//cast
		Activity other = (Activity) obj;
		
		if (endTime != other.endTime)
			return false;
		if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (!title.equals(other.title))
			return false;
		
		return true;
	}



}