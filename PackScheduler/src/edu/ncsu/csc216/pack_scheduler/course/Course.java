/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidator;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * Extends Activity to create specific course objects. maintains information about
 * courses that a student can pick
 * 
 * @author William
 *
 */
public class Course extends Activity implements Comparable<Course> {
	
	/** constant for section length */
	private static final int SECTION_LENGTH = 3;
	/** constant for section name length maximum */
	private static final int MAX_NAME_LENGTH = 6;
	/** constant for section minimum name length */
	private static final int MIN_NAME_LENGTH = 4;
	/** maximum number of credits */
	private static final int MAX_CREDITS = 5;
	/** minimum number of credits */
	private static final int MIN_CREDITS = 1;
	
	/**Course's roll **/
	private CourseRoll roll;
	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/** validator **/
	private CourseNameValidator validator;
	
	/**
	 * Creates a Course with the given name, title, section, credits, instructorId, and meetingDays for 
	 * courses that are arranged.
	 * @param name
	 * 			the name of the course
	 * @param title
	 * 			the title of the course
	 * @param section
	 * 			the section of the course
	 * @param credits
	 * 			the number of credits of the course
	 * @param instructorId
	 * 			the instructor of the course
	 * @param enrollmentCap
	 * 			the enrollment cap for the course
	 * @param meetingDays
	 * 			the days the course meets
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays) {
	    this(name, title, section, credits, instructorId, enrollmentCap, meetingDays, 0, 0);
	    
	    roll = new CourseRoll(enrollmentCap, this);
	}

	/**
	 * Constructs a Course object with values for all fields.
	 * @param name
	 * 			the name of the course
	 * @param title
	 * 			the title of the course
	 * @param section
	 * 			the section of the course
	 * @param credits
	 * 			the number of credits of the course
	 * @param instructorId
	 * 			the instructor of the course
	 * @param enrollmentCap
	 * 			the enrollment cap for the course
	 * @param meetingDays
	 * 			the days the course meets
	 * @param startTime
	 * 			the start time of the course
	 * @param endTime
	 * 			the end time of the course
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays,
			int startTime, int endTime) {
	    super(title, meetingDays, startTime, endTime);
		
		setName(name);
	    setSection(section);
	    setCredits(credits);
	    setInstructorId(instructorId);
	    
	    roll = new CourseRoll(enrollmentCap, this);

	    
	}

	/**
	 * Returns the Course's name.
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Course's name.  If the name is null, has a length less than 4 or 
	 * greater than 6, an IllegalArgumentException is thrown.
	 * @param name 
	 * 		the name of the course to be set
	 * @throws InvalidTransitionException 
	 */
	private void setName(String name){
	    validator = new CourseNameValidator();
	    
	    if (name == null) {
	        throw new IllegalArgumentException("Invalid course name");
	    }
	    
	    if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
	        throw new IllegalArgumentException("Invalid course name");
	    }
	    try {
	    	validator.isValid(name);
	    } catch (InvalidTransitionException e) {
	    	
	    }
	    this.name = name;
	}

	/**
	 * Returns the Course's section.
	 * @return the section
	 * 		the section of the course.
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Sets the Course's section.
	 * 
	 * @param section 
	 * 		The section for the course
	 * @throws IllegalArgumentException 
	 * 		if Section is null or not 3 characters long 
	 */
	public void setSection(String section) {

		if (section == null) {
			throw new IllegalArgumentException ("Invalid section number");		
		} 
		
		int sectionLength = section.length();
		
		if (sectionLength != SECTION_LENGTH) {
			throw new IllegalArgumentException ("Invalid section number");	
		}
		
		this.section = section;
		
	}

	/**
	 * Returns the Course's credits
	 * 
	 * @return credits
	 * 		provides the number of credits a course has
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Sets the Course's credits
	 * 
	 * @param credits 
	 * 		Sets the number of credits a course has
	 * 
	 * @throws IllegalArgumentException if credits are less than 1 or more than 5
	 */
	public void setCredits(int credits) {
		if (credits < MIN_CREDITS || credits > MAX_CREDITS) {
			throw new IllegalArgumentException ("credits cannot be above 5 or less than 1");	
		} else {
			this.credits = credits;
		}		
	}

	/**
	 * Returns the instructor's Id for the course.
	 * 
	 * @return instructorId
	 * 		the instructor ID for the instructor that is teaching the course
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Sets the Course's instructor Id.
	 * 
	 * @param instructorId
	 *			the ID for the instructor teaching the course
	 * @throws IllegalArgumentException 
	 * 			if the instructor id is null or empty
	 */
	public void setInstructorId(String instructorId) {
		
			this.instructorId = instructorId;
	
	}
	
	/**
	 * Sets the days that a course is meeting, can be A -arranged
	 * M, T, W, H or F
	 * 
	 * @param meetingDays
	 * 			the days that a course will meet; A, M, T, W, H, or F
	 * @throws IllegalArgumentException 
	 * 			if the days provided include any invalid Character
	 * 			if 'A' is present and not the only character provided
	 * 			if the String provided is empty or null
	 */
	@Override
	public void setMeetingDays(String meetingDays) {
		if (meetingDays == null || meetingDays.equals("")) {
			throw new IllegalArgumentException ("Invalid meeting days");	
		}
		boolean okay = false;
		boolean meetingDayA = false;
		int meetingDaysLength = meetingDays.length();
		char meetingDaysChar = 'a';
	
		// test if any of the letters are not A, M, T, W, H or F
		// if not throw
		for(int i = 0; i < meetingDaysLength; i++) {
			meetingDaysChar = meetingDays.charAt(i);
			if (meetingDaysChar == 'A' || meetingDaysChar == 'M' || 
					meetingDaysChar == 'T' || meetingDaysChar == 'W' || 
					meetingDaysChar == 'H' || meetingDaysChar == 'F') {
				okay = true;
			} else {
				throw new IllegalArgumentException ("Invalid meeting days");					
			}
			
			if(meetingDaysChar == 'A') {
				meetingDayA = true;
			}
	
		}
		// A must be alone 
		if( meetingDayA && meetingDaysLength > 1) {
			throw new IllegalArgumentException ("Invalid meeting days");
		}
		if(okay) {	
			super.setMeetingDays(meetingDays);
		}
	}
	/**
	 * Returns a comma separated value String of all Course fields.
	 * 
	 * @return String 
	 * 			A textual representation of Course separated by commas
	 */
	@Override
	public String toString() {
	    if (super.getMeetingDays().equals("A")) {
	        return name + "," + super.getTitle() + "," + section + "," + credits + "," + instructorId + ","
	        		+ this.roll.getEnrollmentCap() + "," + super.getMeetingDays();
	    }
	    return name + "," + super.getTitle() + "," + section + "," + credits + "," + instructorId + ","
	    		 + this.roll.getEnrollmentCap() + "," + super.getMeetingDays() + "," + super.getStartTime() + "," + super.getEndTime(); 
	}
	
	/**
	 * Tests to see if a course is a duplicate activity. Tests
	 * if the provided object is a Course object
	 * and if that course object has the same name.
	 * then if the course object has the same section.
	 * 
	 * @param activity
	 * 			the object provided; will be casted as a
	 * 			a Course object
	 * @return boolean
	 * 			returns true if the Course object is not unique or false if it is
	 * 			true if the course is unique
	 * 			false if it already exists  	
	 */
	public boolean isDuplicate(Activity activity) {
		if (activity == null) {
			return false;
		}
		if (activity.getClass() != Course.class) {
			return false;
		}
		
		Course other = (Course) activity;
		
		return (getName().equals(other.getName()) && getSection() == other.getSection());
	}
	
	/**
	 * Creates a short array that is populated with the Courses fields
	 * but only includes the name, section, title and meeting days
	 * 
	 * @return String[]
	 * 			An array of State of a Course object
	 */
	public String[] getShortDisplayArray() {
		//Course name, section, title, and meeting days string.
		String [] shortArray;
		shortArray = new String [5];
		String title = super.getTitle();
		String meetingDays = super.getMeetingString();
		int cap = this.roll.getEnrollmentCap(); 
		for (int i = 0; i < 5; i++) {
			if (i == 0) {
				shortArray [i] = this.getName();  
			}
			if (i == 1) {
				shortArray [i] = this.getSection(); 
			}
    	
			if (i == 2) {
				shortArray [i] = title; 
			}
			if (i == 3) {
				shortArray [i] = meetingDays;
			}
			if (i == 4) {
				shortArray [i] = Integer.toString(cap);
			}
			
		}
	return shortArray;
	}
	
	/**
	 * Creates a long array that is populated with the Courses fields
	 * and includes the name, section, title, credits, instructorId,
	 * meeting days and an empty field for a state that only Event has
	 * 
	 * @return String[]
	 * 			An array populated by State of a Course object
	 */
	public String[] getLongDisplayArray() {
		//Course name, section, title, credits, instructorId, meeting days string, 
		//empty string (for a field that Event will have that Course does not).
		String [] longArray;
		longArray = new String [7];
		String title = super.getTitle();
		String meetingDays = super.getMeetingString();
		String empty = "";
		
		for (int i = 0; i < 7; i++) {
			if (i == 0) {
				longArray [i] = this.getName();  
			}
    	
			if (i == 1) {
				longArray [i] = this.getSection(); 
			}
    	
			if (i == 2) {
				longArray [i] = title; 
			}
			if (i == 3) {
				longArray [i] = Integer.toString(this.getCredits());
			}
			if (i == 4) {
				longArray [i] = this.getInstructorId(); 
			}
    	
			if (i == 5) {
				longArray [i] = meetingDays; 
			}
			if (i == 6) {
				longArray [i] = empty;
			}
		}
	return longArray;
	}	
	/** 
	 * generates a hash code for a Course object
	 * 
	 * @return int
	 * 			integer value of hashed fields from the Course object		
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + instructorId.hashCode();
		result = prime * result + name.hashCode();
		result = prime * result + section.hashCode();
		return result;
	}

	/**
	 * Compares two course objects by comparing all state fields
	 * 
	 * @return boolean
	 * 			If a course object is equal to another course 
	 * 			object in all fields this returns true
	 * 			else false
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		//cast
		Course other = (Course) obj;
		//test fields, can't be null
		if (credits != other.credits)
			return false;
		if (!instructorId.equals(other.instructorId))
			return false;
		if (!name.equals(other.name))
			return false;
		if (!section.equals(other.section))
			return false;
		
		return true;
	}
	
	/**
	 * Compares two Courses, starting with the course name
	 * then moving to the Section
	 * 
	 * @param c
	 * 			course to be compared to calling course
	 * 
	 * @return int
	 * 			returns 1, if the object is greater
	 * 			returns 0, if the object is equal
	 * 			returns -1, if the object is less
	 */
	@Override
	public int compareTo(Course c) {
		//Course other = (Course) c;
		if (this.getName().compareTo(c.getName()) == 0) {
			if(this.getSection().compareTo(c.getSection()) == 0) {
				return 0;
			} else {
				if (this.getSection().compareTo(c.getSection()) > 0) {
					return 1;
				} else {
					return -1;
				}
			}
		} else {
			if (this.getName().compareTo(c.getName()) > 0) {
				return 1;
			} else {
				return -1;
			}	
		}
		
	}
	
	public CourseRoll getCourseRoll() {
		return this.roll;
	}
}
