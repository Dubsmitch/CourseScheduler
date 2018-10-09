package edu.ncsu.csc216.pack_scheduler.catalog;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;


import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc216.collections.list.*;

import edu.ncsu.csc216.pack_scheduler.course.Activity;
import edu.ncsu.csc216.pack_scheduler.course.Course;


/**
 * Tests the CourseCatalog class.
 * 
 * @author Sarah Heckman
 */
public class CourseCatalogTest {
	
	/** Valid course records */
	private final String validTestFile = "test-files/course_records.txt";
	/** Invalid course records */
	private final String invalidTestFile = "test-files/invalid_course_records.txt";
	
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
	/** Course meeting days */
	private static final String MEETING_DAYS = "TH";
	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */
	private static final int END_TIME = 1445;


	/**
	 * Resets course_records.txt for use in other tests.
	 */
	@Before
	public void setUp() throws Exception {
		//Reset course_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "starter_course_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "course_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}
	
	/**
	 * Tests CourseCatalog().
	 */
	@Test
	public void testCourseCatalog() {
		//Test with invalid file.  Should have an empty catalog and schedule. 
		CourseCatalog c1 = new CourseCatalog();
		assertEquals(0, c1.getCourseCatalog().length);
	}
	
	/**
	 * Tests loadFromFile().
	 */
	@Test
	public void TestLoadCoursesFromFile() {
		CourseCatalog c1 = new CourseCatalog();
		c1.loadCoursesFromFile(validTestFile);
		assertEquals(8, c1.getCourseCatalog().length);
	}
	
	/**
	 * Tests newCourseCatalog().
	 */
	@Test
	public void testNewCourseCatalog() {
		//load a catalog
		CourseCatalog c1 = new CourseCatalog();
		c1.loadCoursesFromFile(validTestFile);
		assertEquals(8, c1.getCourseCatalog().length);
		
		//then replace with a new one
		c1.newCourseCatalog();
		
		//compare
		assertEquals(0, c1.getCourseCatalog().length);
	}
	
	/**
	 * Tests addCourseToCatalog().
	 */
	@Test
	public void testAddCourseToCatalog() {
		//load a catalog
		CourseCatalog c1 = new CourseCatalog();
		c1.loadCoursesFromFile(validTestFile);
		
		//add course
		c1.addCourseToCatalog("ABCD", TITLE, SECTION, CREDITS, INSTRUCTOR_ID, MEETING_DAYS, 
				START_TIME, END_TIME);
		assertEquals(9, c1.getCourseCatalog().length);
	
	}
	
	/**
	 * Tests removing a course
	 */
	@Test
	public void testRemoveCourseFromCatalog() {
		//load a catalog
		CourseCatalog c1 = new CourseCatalog();
		c1.loadCoursesFromFile(validTestFile);
		
		//add course
		c1.addCourseToCatalog("ABCD", TITLE, SECTION, CREDITS, INSTRUCTOR_ID, MEETING_DAYS, 
				START_TIME, END_TIME);
		assertEquals(9, c1.getCourseCatalog().length);
		
		//remove Course
		c1.removeCourseFromCatalog("ABCD", SECTION);
		assertEquals(8, c1.getCourseCatalog().length);
	}
	
	/**
	 * Test getting a course from the catalog
	 */
	@Test
	public void testGetCourseFromCatalog() {
		//create course that will be added
		Course a= new Course ("ABCD", TITLE, SECTION, CREDITS, INSTRUCTOR_ID, MEETING_DAYS, 
				START_TIME, END_TIME);
		//load a catalog
		CourseCatalog c1 = new CourseCatalog();
		c1.loadCoursesFromFile(validTestFile);
		
		//add course
		c1.addCourseToCatalog("ABCD", TITLE, SECTION, CREDITS, INSTRUCTOR_ID, MEETING_DAYS, 
				START_TIME, END_TIME);
		
		//test getting a course
		assertEquals(a, c1.getCourseFromCatalog("ABCD", SECTION));
		
	}
	
	/**
	 * tests retrieving a string of the course catalog
	 */
	@Test
	public void testGetCourseCatalog() {
		CourseCatalog ws = new CourseCatalog();
		ws.loadCoursesFromFile(validTestFile);
		
		//Get the catalog and make sure contents are correct
		//Name, section, title
		String [][] catalog = ws.getCourseCatalog();
		//Row 0
		assertEquals("CSC116", catalog[0][0]);
		assertEquals("001", catalog[0][1]);
		assertEquals("Intro to Programming - Java", catalog[0][2]);
		assertEquals("MW 9:10AM-11:00AM", catalog[0][3]);
		//Row 1
		assertEquals("CSC116", catalog[1][0]);
		assertEquals("002", catalog[1][1]);
		assertEquals("Intro to Programming - Java", catalog[1][2]);
		assertEquals("MW 11:20AM-1:10PM", catalog[1][3]);
		//Row 2
		assertEquals("CSC116", catalog[2][0]);
		assertEquals("003", catalog[2][1]);
		assertEquals("Intro to Programming - Java", catalog[2][2]);
		assertEquals("TH 11:20AM-1:10PM", catalog[2][3]);
		//Row 3
		assertEquals("CSC216", catalog[3][0]);
		assertEquals("001", catalog[3][1]);
		assertEquals("Programming Concepts - Java", catalog[3][2]);
		assertEquals("TH 1:30PM-2:45PM", catalog[3][3]);
		//Row 4
		assertEquals("CSC216", catalog[4][0]);
		assertEquals("002", catalog[4][1]);
		assertEquals("Programming Concepts - Java", catalog[4][2]);
		assertEquals("MW 1:30PM-2:45PM", catalog[4][3]);
		//Row 5
		assertEquals("CSC216", catalog[5][0]);
		assertEquals("601", catalog[5][1]);
		assertEquals("Programming Concepts - Java", catalog[5][2]);
		assertEquals("Arranged", catalog[5][3]);
		//Row 6
		assertEquals("CSC226", catalog[6][0]);
		assertEquals("001", catalog[6][1]);
		assertEquals("Discrete Mathematics for Computer Scientists", catalog[6][2]);
		assertEquals("MWF 9:35AM-10:25AM", catalog[6][3]);
		//Row 7
		assertEquals("CSC230", catalog[7][0]);
		assertEquals("001", catalog[7][1]);
		assertEquals("C and Software Tools", catalog[7][2]);
		assertEquals("MW 11:45AM-1:00PM", catalog[7][3]);
	}
	
	/**
	 * Test saveCourseCatalog().
	 */
	@Test
	public void testExportSchedule() {
		//Test that empty schedule exports correctly
		CourseCatalog ws = new CourseCatalog();
		ws.saveCourseCatalog("test-files/actual_empty_export.txt");
		checkFiles("test-files/expected_empty_export.txt", "test-files/actual_empty_export.txt");
		
		//Add courses and test that exports correctly
		ws.addCourseToCatalog("ABCD", TITLE, SECTION, CREDITS, INSTRUCTOR_ID, MEETING_DAYS, 
				START_TIME, END_TIME);

		assertEquals(1, ws.getCourseCatalog().length);
		ws.saveCourseCatalog("test-files/actual_schedule_export.txt");
		checkFiles("test-files/expected_schedule_export.txt", "test-files/actual_schedule_export.txt");
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new File(expFile));
			 Scanner actScanner = new Scanner(new File(actFile));) {
			
			while (actScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			if (expScanner.hasNextLine()) {
				fail();
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
}