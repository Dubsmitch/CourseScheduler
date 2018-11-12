package edu.ncsu.csc216.pack_scheduler.user.schedule;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.ConflictException;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;
import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * tests the schedule class
 * 
 * @author William
 *
 */
public class ScheduleTest {
	/** course fields **/
	
	/** Course name */
	private static final String NAME = "CSC216";
	/** Course title */
	private static final String TITLE = "Programming Concepts - Java";
	/** Course section */
	private static final String SECTION = "001";
	/** Course credits */
	private static final int CREDITS = 4;
	/** cap **/
	@SuppressWarnings("unused")
	private static final int CAP = 25;
	/** Course instructor id */
	private static final String INSTRUCTOR_ID = "sesmith5";
	/** Course meeting days */
	private static final String MEETING_DAYS = "MW";
	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */
	private static final int END_TIME = 1445;
	
	/**
	 * tests constructor
	 */
	@Test
	public void Constructortest() {
		Schedule a = new Schedule();
		
		//check that the title has been initialized correctly
		assertEquals(a.getTitle(), "My Schedule");
		//check that the schedule has been created and is empty
		assertEquals(a.getSchedule().size(), 0); 
	}
	/**
	 * tests adding courses to the schedule
	 * @throws InvalidTransitionException 
	 */
	@Test
	public void addCourseToScheduletest() throws InvalidTransitionException {
		Schedule a = new Schedule();
		
		//check that the title has been initialized correctly
		assertEquals(a.getTitle(), "My Schedule");
		//check that the schedule has been created and is empty
		assertEquals(a.getSchedule().size(), 0); 
		//create a course to be added
		Course coursea = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, 25,
				MEETING_DAYS, START_TIME, END_TIME);
		//add course to shedule
		try {
			a.addCourseToSchedule(coursea);
		} catch (ConflictException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(a.getSchedule().get(0).equals(coursea));
	}
	
	/**
	 * tests removing courses from the schedule
	 * @throws InvalidTransitionException 
	 */
	@Test
	public void RemoveCoursefromScheduletest() throws InvalidTransitionException {
		Schedule a = new Schedule();
		
		//check that the title has been initialized correctly
		assertEquals(a.getTitle(), "My Schedule");
		//check that the schedule has been created and is empty
		assertEquals(a.getSchedule().size(), 0); 
		//create a course to be added
		Course coursea = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, 25,
				MEETING_DAYS, START_TIME, END_TIME);
		//add course to shedule
		try {
			a.addCourseToSchedule(coursea);
		} catch (ConflictException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(a.getSchedule().get(0).equals(coursea));
		
		assertTrue(a.removeCourseFromSchedule(coursea));
		
		assertEquals(a.getSchedule().size(), 0);
	}
	
	/**
	 * tests resetting schedule 
	 */
	@Test
	public void ResetScheduletest() throws InvalidTransitionException {
		Schedule a = new Schedule();
		
		//check that the title has been initialized correctly
		assertEquals(a.getTitle(), "My Schedule");
		//check that the schedule has been created and is empty
		assertEquals(a.getSchedule().size(), 0); 
		//create a course to be added
		Course coursea = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, 25,
				MEETING_DAYS, START_TIME, END_TIME);
		//add course to shedule
		try {
			a.addCourseToSchedule(coursea);
		} catch (ConflictException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(a.getSchedule().get(0).equals(coursea));
		assertEquals(a.getSchedule().size(), 1);
		
		a.resetSchedule();
		
		assertEquals(a.getSchedule().size(), 0);
	}
	
	/**
	 * tests getting the 2D string 
	 * not sure how to test this
	 */
	//@Test
	//public void getScheduleCoursestest() throws InvalidTransitionException {
	//	Schedule a = new Schedule();
	//	
	//	//check that the title has been initialized correctly
	//	assertEquals(a.getTitle(), "My Schedule");
	//	//check that the schedule has been created and is empty
	//	assertEquals(a.getSchedule().size(), 0); 
	//	//create a course to be added
	//	Course coursea = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, 
	//			MEETING_DAYS, START_TIME, END_TIME);
	//	//add course to shedule
	//	a.addCourseToSchedule(coursea);
	//	
	//	String [][] comp = new String[1][4];
	//	comp[0][0] = coursea.getName();
	//	comp[0][1] = coursea.getSection();
	//	comp[0][2] = coursea.getTitle();
	//	comp[0][3] = coursea.getMeetingString();
	//	
	//	assertTrue(a.getScheduledCourses().equals(comp));
	//	assertEquals(a.getSchedule().size(), 1);
	//	
	//	assertTrue(a.getScheduledCourses().equals(coursea.getShortDisplayArray()));
	//}
	
	/**
	 * tests setting the title
	 */
	@Test
	public void SetTitleTest() {
		Schedule a = new Schedule();
		
		//check that the title has been initialized correctly
		assertEquals(a.getTitle(), "My Schedule");
		//check that the schedule has been created and is empty
		assertEquals(a.getSchedule().size(), 0); 
		
		a.setTitle("Yo!");
		
		assertEquals(a.getTitle(), "Yo!");
	}
	
	/**
	 * tests adding to a schedule
	 * @throws InvalidTransitionException 
	 * @throws ConflictException 
	 */
	@Test
	public void testcanAdd() throws InvalidTransitionException, ConflictException {
		Schedule s1 = new Schedule();
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, CAP, MEETING_DAYS, 0011, 0012);


		
		assertTrue(s1.canAdd(c));
		s1.addCourseToSchedule(c);
		assertTrue(!s1.canAdd(c));
		
		c = null;
		assertTrue(!s1.canAdd(c));
		
	}
	
	/**
	 * tests getting the schedule's credits
	 * @throws InvalidTransitionException 
	 * @throws ConflictException 
	 */
	@Test
	public void testGetScheduleCredits() throws InvalidTransitionException, ConflictException {
		Schedule s1 = new Schedule();
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, CAP, MEETING_DAYS, 0011, 0012);


		assertEquals(s1.getScheduleCredits(), 0);

		assertTrue(s1.canAdd(c));
		s1.addCourseToSchedule(c);
		assertEquals(s1.getScheduleCredits(), 4);
		
		Course c1 = new Course("ABCD", TITLE, SECTION, 2, INSTRUCTOR_ID, CAP, MEETING_DAYS, 0013, 0014);
		assertTrue(s1.canAdd(c1));
		s1.addCourseToSchedule(c1);
		assertEquals(s1.getScheduleCredits(), 6);
	}
	
	
	
}
	

