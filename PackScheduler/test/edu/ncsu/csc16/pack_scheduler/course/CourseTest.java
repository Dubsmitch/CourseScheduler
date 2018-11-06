package edu.ncsu.csc16.pack_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Activity;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * Tests the Course class.
 * 
 * Note that test methods for all getters have been omitted.  They
 * will be tested through other methods.
 * 
 * @author Sarah Heckman
 */
public class CourseTest {
	
	/** Course name */
	private static final String NAME = "CSC216";
	/** Course title */
	private static final String TITLE = "Programming Concepts - Java";
	/** Course section */
	private static final String SECTION = "001";
	/** Course credits */
	private static final int CREDITS = 4;
	/** Course instructor id */
	private static final String INSTRUCTOR_ID = "sesmith5";
	/** Course's cap **/
	private static final int CAP = 25;
	/** Course meeting days */
	private static final String MEETING_DAYS = "MW";
	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */
	private static final int END_TIME = 1445;

	/**
	 * Tests the Course constructor with all field parameters.
	 * @throws InvalidTransitionException 
	 */
	@Test
	public void testCourseStringStringStringIntStringStringIntInt() throws InvalidTransitionException {
		//Setting name can only be tested through the constructor
		
		//Testing for null name
		Course c = null;
		try {
			c = new Course(null, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, CAP, MEETING_DAYS, START_TIME, END_TIME);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(c);
		}
		
		//Testing for empty string name
		c = null;
		try {
			c = new Course("", TITLE, SECTION, CREDITS, INSTRUCTOR_ID, CAP, MEETING_DAYS, START_TIME, END_TIME);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(c);
		}
		
		//Testing for name with length less than 4
		c = null;
		try {
			c = new Course("E11", TITLE, SECTION, CREDITS, INSTRUCTOR_ID, CAP, MEETING_DAYS, START_TIME, END_TIME);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(c);
		}
		
		//Testing for name with length greater than 6
		c = null;
		try {
			c = new Course("CSC2167", TITLE, SECTION, CREDITS, INSTRUCTOR_ID, CAP, MEETING_DAYS, START_TIME, END_TIME);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(c);
		}
		
		//Test a valid construction
		c = null;
		try {
			c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, CAP, MEETING_DAYS, START_TIME, END_TIME);
			assertEquals(NAME, c.getName());
			assertEquals(TITLE, c.getTitle());
			assertEquals(SECTION, c.getSection());
			assertEquals(CREDITS, c.getCredits());
			assertEquals(INSTRUCTOR_ID, c.getInstructorId());
			assertEquals(MEETING_DAYS, c.getMeetingDays());
			assertEquals(START_TIME, c.getStartTime());
			assertEquals(END_TIME, c.getEndTime());
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

	/**
	 * Tests the Course constructor with 4 parameters.
	 * @throws InvalidTransitionException 
	 */
	@Test
	public void testCourseStringStringStringIntStringString() throws InvalidTransitionException {
		//Test a valid construction and make sure values are correct
		Course c = null;
		try {
			c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, CAP, "A");
			assertEquals(NAME, c.getName());
			assertEquals(TITLE, c.getTitle());
			assertEquals(SECTION, c.getSection());
			assertEquals(CREDITS, c.getCredits());
			assertEquals(INSTRUCTOR_ID, c.getInstructorId());
			assertEquals("A", c.getMeetingDays());
			assertEquals(0, c.getStartTime());
			assertEquals(0, c.getEndTime());
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

	/**
	 * Tests setTitle().
	 * @throws InvalidTransitionException 
	 */
	@Test
	public void testSetTitle() throws InvalidTransitionException {
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, CAP, MEETING_DAYS, START_TIME, END_TIME);
		assertEquals(NAME, c.getName());
		assertEquals(TITLE, c.getTitle());
		assertEquals(SECTION, c.getSection());
		assertEquals(CREDITS, c.getCredits());
		assertEquals(INSTRUCTOR_ID, c.getInstructorId());
		assertEquals(MEETING_DAYS, c.getMeetingDays());
		assertEquals(START_TIME, c.getStartTime());
		assertEquals(END_TIME, c.getEndTime());
		
		//Test that setting the title to null doesn't change the title (or anything else).
		try {
			c.setTitle(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(NAME, c.getName());
			assertEquals(TITLE, c.getTitle());
			assertEquals(SECTION, c.getSection());
			assertEquals(CREDITS, c.getCredits());
			assertEquals(INSTRUCTOR_ID, c.getInstructorId());
			assertEquals(MEETING_DAYS, c.getMeetingDays());
			assertEquals(START_TIME, c.getStartTime());
			assertEquals(END_TIME, c.getEndTime());
		}
		
		//Test that setting the title to "" doesn't change the title (or anything else).
		try {
			c.setTitle("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(NAME, c.getName());
			assertEquals(TITLE, c.getTitle());
			assertEquals(SECTION, c.getSection());
			assertEquals(CREDITS, c.getCredits());
			assertEquals(INSTRUCTOR_ID, c.getInstructorId());
			assertEquals(MEETING_DAYS, c.getMeetingDays());
			assertEquals(START_TIME, c.getStartTime());
			assertEquals(END_TIME, c.getEndTime());
		}
		
		//Valid set
		c.setTitle("A new title");
		assertEquals(NAME, c.getName());
		assertEquals("A new title", c.getTitle());
		assertEquals(SECTION, c.getSection());
		assertEquals(CREDITS, c.getCredits());
		assertEquals(INSTRUCTOR_ID, c.getInstructorId());
		assertEquals(MEETING_DAYS, c.getMeetingDays());
		assertEquals(START_TIME, c.getStartTime());
		assertEquals(END_TIME, c.getEndTime());
	}

	/**
	 * Tests setSection().
	 * @throws InvalidTransitionException 
	 */
	@Test
	public void testSetSection() throws InvalidTransitionException {
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, CAP, MEETING_DAYS, START_TIME, END_TIME);
		assertEquals(NAME, c.getName());
		assertEquals(TITLE, c.getTitle());
		assertEquals(SECTION, c.getSection());
		assertEquals(CREDITS, c.getCredits());
		assertEquals(INSTRUCTOR_ID, c.getInstructorId());
		assertEquals(MEETING_DAYS, c.getMeetingDays());
		assertEquals(START_TIME, c.getStartTime());
		assertEquals(END_TIME, c.getEndTime());
		
		//Test that setting the section to null doesn't change the section (or anything else).
		try {
			c.setSection(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(NAME, c.getName());
			assertEquals(TITLE, c.getTitle());
			assertEquals(SECTION, c.getSection());
			assertEquals(CREDITS, c.getCredits());
			assertEquals(INSTRUCTOR_ID, c.getInstructorId());
			assertEquals(MEETING_DAYS, c.getMeetingDays());
			assertEquals(START_TIME, c.getStartTime());
			assertEquals(END_TIME, c.getEndTime());
		}
		
		//Test that setting the section to "" doesn't change the section (or anything else).
		try {
			c.setSection("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(NAME, c.getName());
			assertEquals(TITLE, c.getTitle());
			assertEquals(SECTION, c.getSection());
			assertEquals(CREDITS, c.getCredits());
			assertEquals(INSTRUCTOR_ID, c.getInstructorId());
			assertEquals(MEETING_DAYS, c.getMeetingDays());
			assertEquals(START_TIME, c.getStartTime());
			assertEquals(END_TIME, c.getEndTime());
		}
		
		//Test that setting the section to "00" doesn't change the section (or anything else).
		try {
			c.setSection("00");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(NAME, c.getName());
			assertEquals(TITLE, c.getTitle());
			assertEquals(SECTION, c.getSection());
			assertEquals(CREDITS, c.getCredits());
			assertEquals(INSTRUCTOR_ID, c.getInstructorId());
			assertEquals(MEETING_DAYS, c.getMeetingDays());
			assertEquals(START_TIME, c.getStartTime());
			assertEquals(END_TIME, c.getEndTime());
		}
		
		//Test that setting the section to "0012" doesn't change the section (or anything else).
		try {
			c.setSection("0012");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(NAME, c.getName());
			assertEquals(TITLE, c.getTitle());
			assertEquals(SECTION, c.getSection());
			assertEquals(CREDITS, c.getCredits());
			assertEquals(INSTRUCTOR_ID, c.getInstructorId());
			assertEquals(MEETING_DAYS, c.getMeetingDays());
			assertEquals(START_TIME, c.getStartTime());
			assertEquals(END_TIME, c.getEndTime());
		}
		
		//Test valid section
		c.setSection("002");
		assertEquals(NAME, c.getName());
		assertEquals(TITLE, c.getTitle());
		assertEquals("002", c.getSection());
		assertEquals(CREDITS, c.getCredits());
		assertEquals(INSTRUCTOR_ID, c.getInstructorId());
		assertEquals(MEETING_DAYS, c.getMeetingDays());
		assertEquals(START_TIME, c.getStartTime());
		assertEquals(END_TIME, c.getEndTime());
	}

	/**
	 * Tests that the credits are set correctly.
	 * @throws InvalidTransitionException 
	 */
	@Test
	public void testSetCredits() throws InvalidTransitionException {
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, CAP, MEETING_DAYS, START_TIME, END_TIME);
		assertEquals(NAME, c.getName());
		assertEquals(TITLE, c.getTitle());
		assertEquals(SECTION, c.getSection());
		assertEquals(CREDITS, c.getCredits());
		assertEquals(INSTRUCTOR_ID, c.getInstructorId());
		assertEquals(MEETING_DAYS, c.getMeetingDays());
		assertEquals(START_TIME, c.getStartTime());
		assertEquals(END_TIME, c.getEndTime());
		
		//Test that setting the credits to 0 doesn't change the credits (or anything else).
		try {
			c.setCredits(0);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(NAME, c.getName());
			assertEquals(TITLE, c.getTitle());
			assertEquals(SECTION, c.getSection());
			assertEquals(CREDITS, c.getCredits());
			assertEquals(INSTRUCTOR_ID, c.getInstructorId());
			assertEquals(MEETING_DAYS, c.getMeetingDays());
			assertEquals(START_TIME, c.getStartTime());
			assertEquals(END_TIME, c.getEndTime());
		}
		
		//Test that setting the credits to 6 doesn't change the credits (or anything else).
		try {
			c.setCredits(6);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(NAME, c.getName());
			assertEquals(TITLE, c.getTitle());
			assertEquals(SECTION, c.getSection());
			assertEquals(CREDITS, c.getCredits());
			assertEquals(INSTRUCTOR_ID, c.getInstructorId());
			assertEquals(MEETING_DAYS, c.getMeetingDays());
			assertEquals(START_TIME, c.getStartTime());
			assertEquals(END_TIME, c.getEndTime());
		}
		
		//Test valid credits
		c.setCredits(3);
		assertEquals(NAME, c.getName());
		assertEquals(TITLE, c.getTitle());
		assertEquals(SECTION, c.getSection());
		assertEquals(3, c.getCredits());
		assertEquals(INSTRUCTOR_ID, c.getInstructorId());
		assertEquals(MEETING_DAYS, c.getMeetingDays());
		assertEquals(START_TIME, c.getStartTime());
		assertEquals(END_TIME, c.getEndTime());
	}

	/**
	 * Tests that the instructor id is set correctly.
	 * @throws InvalidTransitionException 
	 */
	@Test
	public void testSetInstructorId() throws InvalidTransitionException {
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, CAP, MEETING_DAYS, START_TIME, END_TIME);
		assertEquals(NAME, c.getName());
		assertEquals(TITLE, c.getTitle());
		assertEquals(SECTION, c.getSection());
		assertEquals(CREDITS, c.getCredits());
		assertEquals(INSTRUCTOR_ID, c.getInstructorId());
		assertEquals(MEETING_DAYS, c.getMeetingDays());
		assertEquals(START_TIME, c.getStartTime());
		assertEquals(END_TIME, c.getEndTime());
		
		//Test that setting the instructor id to null doesn't change the instructor id (or anything else).
		try {
			c.setInstructorId(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(NAME, c.getName());
			assertEquals(TITLE, c.getTitle());
			assertEquals(SECTION, c.getSection());
			assertEquals(CREDITS, c.getCredits());
			assertEquals(INSTRUCTOR_ID, c.getInstructorId());
			assertEquals(MEETING_DAYS, c.getMeetingDays());
			assertEquals(START_TIME, c.getStartTime());
			assertEquals(END_TIME, c.getEndTime());
		}
		
		//Test that setting the instructor id to "" doesn't change the instructor id (or anything else).
		try {
			c.setInstructorId("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(NAME, c.getName());
			assertEquals(TITLE, c.getTitle());
			assertEquals(SECTION, c.getSection());
			assertEquals(CREDITS, c.getCredits());
			assertEquals(INSTRUCTOR_ID, c.getInstructorId());
			assertEquals(MEETING_DAYS, c.getMeetingDays());
			assertEquals(START_TIME, c.getStartTime());
			assertEquals(END_TIME, c.getEndTime());
		}
		
		//Valid set
		c.setInstructorId("jtking");
		assertEquals(NAME, c.getName());
		assertEquals(TITLE, c.getTitle());
		assertEquals(SECTION, c.getSection());
		assertEquals(CREDITS, c.getCredits());
		assertEquals("jtking", c.getInstructorId());
		assertEquals(MEETING_DAYS, c.getMeetingDays());
		assertEquals(START_TIME, c.getStartTime());
		assertEquals(END_TIME, c.getEndTime());
	}

	/**
	 * Tests that the meeting days are set correctly.
	 * @throws InvalidTransitionException 
	 */
	@Test
	public void testSetMeetingDays() throws InvalidTransitionException {
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, CAP, MEETING_DAYS, START_TIME, END_TIME);
		assertEquals(NAME, c.getName());
		assertEquals(TITLE, c.getTitle());
		assertEquals(SECTION, c.getSection());
		assertEquals(CREDITS, c.getCredits());
		assertEquals(INSTRUCTOR_ID, c.getInstructorId());
		assertEquals(MEETING_DAYS, c.getMeetingDays());
		assertEquals(START_TIME, c.getStartTime());
		assertEquals(END_TIME, c.getEndTime());
		
		//Test that setting the meeting days to null doesn't change the meeting days (or anything else).
		try {
			c.setMeetingDays(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(NAME, c.getName());
			assertEquals(TITLE, c.getTitle());
			assertEquals(SECTION, c.getSection());
			assertEquals(CREDITS, c.getCredits());
			assertEquals(INSTRUCTOR_ID, c.getInstructorId());
			assertEquals(MEETING_DAYS, c.getMeetingDays());
			assertEquals(START_TIME, c.getStartTime());
			assertEquals(END_TIME, c.getEndTime());
		}
		
		//Test that setting the meeting days to "" doesn't change the meeting days (or anything else).
		try {
			c.setMeetingDays("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(NAME, c.getName());
			assertEquals(TITLE, c.getTitle());
			assertEquals(SECTION, c.getSection());
			assertEquals(CREDITS, c.getCredits());
			assertEquals(INSTRUCTOR_ID, c.getInstructorId());
			assertEquals(MEETING_DAYS, c.getMeetingDays());
			assertEquals(START_TIME, c.getStartTime());
			assertEquals(END_TIME, c.getEndTime());
		}
		
		//Test that setting the meeting days with an invalid character doesn't change the meeting days (or anything else).
		try {
			c.setMeetingDays("MWS");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(NAME, c.getName());
			assertEquals(TITLE, c.getTitle());
			assertEquals(SECTION, c.getSection());
			assertEquals(CREDITS, c.getCredits());
			assertEquals(INSTRUCTOR_ID, c.getInstructorId());
			assertEquals(MEETING_DAYS, c.getMeetingDays());
			assertEquals(START_TIME, c.getStartTime());
			assertEquals(END_TIME, c.getEndTime());
		}
		
		//Test that setting the meeting days more than one character other than 'A' doesn't change the meeting days (or anything else).
		try {
			c.setMeetingDays("MA");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(NAME, c.getName());
			assertEquals(TITLE, c.getTitle());
			assertEquals(SECTION, c.getSection());
			assertEquals(CREDITS, c.getCredits());
			assertEquals(INSTRUCTOR_ID, c.getInstructorId());
			assertEquals(MEETING_DAYS, c.getMeetingDays());
			assertEquals(START_TIME, c.getStartTime());
			assertEquals(END_TIME, c.getEndTime());
		}
		
		//Valid set
		c.setMeetingDays("TH");
		assertEquals(NAME, c.getName());
		assertEquals(TITLE, c.getTitle());
		assertEquals(SECTION, c.getSection());
		assertEquals(CREDITS, c.getCredits());
		assertEquals(INSTRUCTOR_ID, c.getInstructorId());
		assertEquals("TH", c.getMeetingDays());
		assertEquals(START_TIME, c.getStartTime());
		assertEquals(END_TIME, c.getEndTime());
	}

	/**
	 * Tests that the course times are set correctly.
	 * @throws InvalidTransitionException 
	 */
	@Test
	public void testsetActivityTime() throws InvalidTransitionException {
		//The code below is commented out until you make some changes to Course.
		//Once those are made, remove the line of code fail() and uncomment the provided tests.

		
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, CAP, MEETING_DAYS, START_TIME, END_TIME);
		assertEquals(NAME, c.getName());
		assertEquals(TITLE, c.getTitle());
		assertEquals(SECTION, c.getSection());
		assertEquals(CREDITS, c.getCredits());
		assertEquals(INSTRUCTOR_ID, c.getInstructorId());
		assertEquals(MEETING_DAYS, c.getMeetingDays());
		assertEquals(START_TIME, c.getStartTime());
		assertEquals(END_TIME, c.getEndTime());
		
		//Test that setting the start time to 2400 doesn't change the start time (or anything else).
		try {
			c.setActivityTime(2400, 1445);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(NAME, c.getName());
			assertEquals(TITLE, c.getTitle());
			assertEquals(SECTION, c.getSection());
			assertEquals(CREDITS, c.getCredits());
			assertEquals(INSTRUCTOR_ID, c.getInstructorId());
			assertEquals(MEETING_DAYS, c.getMeetingDays());
			assertEquals(START_TIME, c.getStartTime());
			assertEquals(END_TIME, c.getEndTime());
		}
		
		//Test that setting the start time to 1360 doesn't change the start time (or anything else).
		try {
			c.setActivityTime(1360, 1445);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(NAME, c.getName());
			assertEquals(TITLE, c.getTitle());
			assertEquals(SECTION, c.getSection());
			assertEquals(CREDITS, c.getCredits());
			assertEquals(INSTRUCTOR_ID, c.getInstructorId());
			assertEquals(MEETING_DAYS, c.getMeetingDays());
			assertEquals(START_TIME, c.getStartTime());
			assertEquals(END_TIME, c.getEndTime());
		}
		
		//Test that setting the start time to -1 doesn't change the start time (or anything else).
		try {
			c.setActivityTime(-1, 1445);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(NAME, c.getName());
			assertEquals(TITLE, c.getTitle());
			assertEquals(SECTION, c.getSection());
			assertEquals(CREDITS, c.getCredits());
			assertEquals(INSTRUCTOR_ID, c.getInstructorId());
			assertEquals(MEETING_DAYS, c.getMeetingDays());
			assertEquals(START_TIME, c.getStartTime());
			assertEquals(END_TIME, c.getEndTime());
		}
		
		//Test that setting the start time to 2400 doesn't change the start time (or anything else).
		try {
			c.setActivityTime(1330, 2400);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(NAME, c.getName());
			assertEquals(TITLE, c.getTitle());
			assertEquals(SECTION, c.getSection());
			assertEquals(CREDITS, c.getCredits());
			assertEquals(INSTRUCTOR_ID, c.getInstructorId());
			assertEquals(MEETING_DAYS, c.getMeetingDays());
			assertEquals(START_TIME, c.getStartTime());
			assertEquals(END_TIME, c.getEndTime());
		}
		
		//Test that setting the start time to 1360 doesn't change the start time (or anything else).
		try {
			c.setActivityTime(1330, 1360);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(NAME, c.getName());
			assertEquals(TITLE, c.getTitle());
			assertEquals(SECTION, c.getSection());
			assertEquals(CREDITS, c.getCredits());
			assertEquals(INSTRUCTOR_ID, c.getInstructorId());
			assertEquals(MEETING_DAYS, c.getMeetingDays());
			assertEquals(START_TIME, c.getStartTime());
			assertEquals(END_TIME, c.getEndTime());
		}
		
		//Test that setting the start time to -1 doesn't change the start time (or anything else).
		try {
			c.setActivityTime(1330, -1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(NAME, c.getName());
			assertEquals(TITLE, c.getTitle());
			assertEquals(SECTION, c.getSection());
			assertEquals(CREDITS, c.getCredits());
			assertEquals(INSTRUCTOR_ID, c.getInstructorId());
			assertEquals(MEETING_DAYS, c.getMeetingDays());
			assertEquals(START_TIME, c.getStartTime());
			assertEquals(END_TIME, c.getEndTime());
		}
		
		//Test that having the start time after the end time doesn't change the values.
		try {
			c.setActivityTime(1445, 1330);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(NAME, c.getName());
			assertEquals(TITLE, c.getTitle());
			assertEquals(SECTION, c.getSection());
			assertEquals(CREDITS, c.getCredits());
			assertEquals(INSTRUCTOR_ID, c.getInstructorId());
			assertEquals(MEETING_DAYS, c.getMeetingDays());
			assertEquals(START_TIME, c.getStartTime());
			assertEquals(END_TIME, c.getEndTime());
		}
		
		//Valid set of start time
		c.setActivityTime(1350, 1445);
		assertEquals(NAME, c.getName());
		assertEquals(TITLE, c.getTitle());
		assertEquals(SECTION, c.getSection());
		assertEquals(CREDITS, c.getCredits());
		assertEquals(INSTRUCTOR_ID, c.getInstructorId());
		assertEquals(MEETING_DAYS, c.getMeetingDays());
		assertEquals(1350, c.getStartTime());
		assertEquals(END_TIME, c.getEndTime());
		
		//Valid set of end time
		c.setActivityTime(1350, 1526);
		assertEquals(NAME, c.getName());
		assertEquals(TITLE, c.getTitle());
		assertEquals(SECTION, c.getSection());
		assertEquals(CREDITS, c.getCredits());
		assertEquals(INSTRUCTOR_ID, c.getInstructorId());
		assertEquals(MEETING_DAYS, c.getMeetingDays());
		assertEquals(1350, c.getStartTime());
		assertEquals(1526, c.getEndTime());
	}
	
	/**
	 * Tests that getMeetingString() works correctly
	 * @throws InvalidTransitionException 
	 */
	@Test
	public void testGetMeetingString() throws InvalidTransitionException {
		//The code below is commented out until you make some changes to Course.
		//Once those are made, remove the line of code fail() and uncomment the provided tests.

		
		Activity c1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, CAP, MEETING_DAYS, START_TIME, END_TIME);
		assertEquals("MW 1:30PM-2:45PM", c1.getMeetingString());
		Activity c2 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, CAP, MEETING_DAYS, 900, 1035);
		assertEquals("MW 9:00AM-10:35AM", c2.getMeetingString());
		Activity c3 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, CAP, "A");
		assertEquals("Arranged", c3.getMeetingString());
		Activity c4 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, CAP, "TH", 1145, 1425);
		assertEquals("TH 11:45AM-2:25PM", c4.getMeetingString());
	}

	/**
	 * Tests that the equals method works for all Course fields.
	 * @throws InvalidTransitionException 
	 */
	@Test
	public void testEqualsObject() throws InvalidTransitionException {
		Activity c1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, CAP, MEETING_DAYS, START_TIME, END_TIME);
		Activity c2 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, CAP, MEETING_DAYS, START_TIME, END_TIME);
		Activity c3 = new Course(NAME, "Different", SECTION, CREDITS, INSTRUCTOR_ID, CAP, MEETING_DAYS, START_TIME, END_TIME);
		Activity c4 = new Course(NAME, TITLE, "002", CREDITS, INSTRUCTOR_ID, CAP, MEETING_DAYS, START_TIME, END_TIME);
		Activity c5 = new Course(NAME, TITLE, SECTION, 5, INSTRUCTOR_ID, CAP, MEETING_DAYS, START_TIME, END_TIME);
		Activity c6 = new Course(NAME, TITLE, SECTION, CREDITS, "Different", CAP, MEETING_DAYS, START_TIME, END_TIME);
		Activity c7 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, CAP, "TH", START_TIME, END_TIME);
		Activity c8 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, CAP, MEETING_DAYS, 830, END_TIME);
		Activity c9 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, CAP, MEETING_DAYS, START_TIME, 1400);
		Course c10 = new Course (NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, CAP, MEETING_DAYS, START_TIME, END_TIME);
		String c11 = "ASD";
		//Test for equality in both directions
		assertTrue(c1.equals(c2));
		assertTrue(c2.equals(c1));
		assertTrue(c10.equals(c10));
		
		//test a different object
		assertFalse(c1.equals(c11));
		
		//Test for each of the fields
		assertFalse(c1.equals(c3));
		assertFalse(c1.equals(c4));
		assertFalse(c1.equals(c5));
		assertFalse(c1.equals(c6));
		assertFalse(c1.equals(c7));
		assertFalse(c1.equals(c8));
		assertFalse(c1.equals(c9));
	}
	
	/**
	 * Tests that hashCode works correctly.
	 * @throws InvalidTransitionException 
	 */
	@Test
	public void testHashCode() throws InvalidTransitionException {
		Activity c1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, CAP, MEETING_DAYS, START_TIME, END_TIME);
		Activity c2 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, CAP, MEETING_DAYS, START_TIME, END_TIME);
		Activity c3 = new Course(NAME, "Different", SECTION, CREDITS, INSTRUCTOR_ID, CAP, MEETING_DAYS, START_TIME, END_TIME);
		Activity c4 = new Course(NAME, TITLE, "002", CREDITS, INSTRUCTOR_ID, CAP, MEETING_DAYS, START_TIME, END_TIME);
		Activity c5 = new Course(NAME, TITLE, SECTION, 5, INSTRUCTOR_ID, CAP, MEETING_DAYS, START_TIME, END_TIME);
		Activity c6 = new Course(NAME, TITLE, SECTION, CREDITS, "Different", CAP, MEETING_DAYS, START_TIME, END_TIME);
		Activity c7 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, CAP, "TH", START_TIME, END_TIME);
		Activity c8 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, CAP, MEETING_DAYS, 830, END_TIME);
		Activity c9 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, CAP, MEETING_DAYS, START_TIME, 1400);
		
		//Test for the same hash code for the same values
		assertEquals(c1.hashCode(), c2.hashCode());
		
		//Test for each of the fields
		assertNotEquals(c1.hashCode(), c3.hashCode());
		assertNotEquals(c1.hashCode(), c4.hashCode());
		assertNotEquals(c1.hashCode(), c5.hashCode());
		assertNotEquals(c1.hashCode(), c6.hashCode());
		assertNotEquals(c1.hashCode(), c7.hashCode());
		assertNotEquals(c1.hashCode(), c8.hashCode());
		assertNotEquals(c1.hashCode(), c9.hashCode());
	}

	/**
	 * Tests that toString returns the correct comma-separated value.
	 * @throws InvalidTransitionException 
	 */
	@Test
	public void testToString() throws InvalidTransitionException {
		Activity c1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, CAP, MEETING_DAYS, START_TIME, END_TIME);
		String s1 = "CSC216,Programming Concepts - Java,001,4,sesmith5,25,MW,1330,1445";
		assertEquals(s1, c1.toString());
		
		Activity c2 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, CAP, "A");
		String s2 = "CSC216,Programming Concepts - Java,001,4,sesmith5,25,A";
		assertEquals(s2, c2.toString());
	}
	
	/**
	 * tests that the compareTo() functions correctly
	 * testing ordering by last name, then first name, then id
	 * @throws InvalidTransitionException 
	 */
	@Test
	public void testCompareTo() throws InvalidTransitionException {
		
		Course c1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, CAP, MEETING_DAYS, START_TIME, END_TIME);
		Course c2 = new Course ("abcd", TITLE, SECTION, CREDITS, INSTRUCTOR_ID, CAP, MEETING_DAYS, START_TIME, END_TIME);
		Course c3 = new Course (NAME, TITLE, "aaa", CREDITS, INSTRUCTOR_ID, CAP, MEETING_DAYS, START_TIME, END_TIME);
		//Student s4 = new Student ("b", "b", "a", "email@ncsu.edu", "hashedpassword", 15);
		//compare two equal students
		assertEquals(0, c1.compareTo(c1));
		
		//compare two course with different names
		assertEquals(-1, c1.compareTo(c2));
		assertEquals(1, c2.compareTo(c1));
		
		//compare two students with same names, different section
		assertEquals(-1, c1.compareTo(c3));
		assertEquals(1, c3.compareTo(c1));
	}	
	
	/**
	 * Tests Course.getLongDisplayArray().
	 * @throws InvalidTransitionException 
	 */
	@Test
	public void testGetLongDisplayArray() throws InvalidTransitionException {
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, CAP, MEETING_DAYS, START_TIME, END_TIME);
		assertEquals(NAME, c.getName());
		assertEquals(TITLE, c.getTitle());
		assertEquals(SECTION, c.getSection());
		assertEquals(CREDITS, c.getCredits());
		assertEquals(INSTRUCTOR_ID, c.getInstructorId());
		assertEquals(MEETING_DAYS, c.getMeetingDays());
		assertEquals(START_TIME, c.getStartTime());
		assertEquals(END_TIME, c.getEndTime());
		String [] actualLongDisplay = c.getLongDisplayArray();
		assertEquals(NAME, actualLongDisplay[0]);
		assertEquals(SECTION, actualLongDisplay[1]);
		assertEquals(TITLE, actualLongDisplay[2]);
		assertEquals("" + CREDITS, actualLongDisplay[3]);
		assertEquals(INSTRUCTOR_ID, actualLongDisplay[4]);
		assertEquals("MW 1:30PM-2:45PM", actualLongDisplay[5]);
		assertEquals("", actualLongDisplay[6]);
	}

	/**
	 * Tests Course.getShortDisplayArray().
	 * @throws InvalidTransitionException 
	 */
	@Test
	public void testGetShortDisplayArray() throws InvalidTransitionException {
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, CAP, MEETING_DAYS, START_TIME, END_TIME);
		assertEquals(NAME, c.getName());
		assertEquals(TITLE, c.getTitle());
		assertEquals(SECTION, c.getSection());
		assertEquals(CREDITS, c.getCredits());
		assertEquals(INSTRUCTOR_ID, c.getInstructorId());
		assertEquals(MEETING_DAYS, c.getMeetingDays());
		assertEquals(START_TIME, c.getStartTime());
		assertEquals(END_TIME, c.getEndTime());
		String [] actualShortDisplay = c.getShortDisplayArray();
		assertEquals(NAME, actualShortDisplay[0]);
		assertEquals(SECTION, actualShortDisplay[1]);
		assertEquals(TITLE, actualShortDisplay[2]);
		assertEquals("MW 1:30PM-2:45PM", actualShortDisplay[3]);
	}

}
