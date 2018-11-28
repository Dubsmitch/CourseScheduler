package edu.ncsu.csc216.pack_scheduler.directory;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;

public class FacultyDirectoryTest {
	/** Valid course records */
	private final String validTestFile = "test-files/faculty_records.txt";
	
	/** Test first name */
	private static final String FIRST_NAME = "Fa";
	/** Test last name */
	private static final String LAST_NAME = "Culty";
	/** Test id */
	private static final String ID = "fculty";
	/** Test email */
	private static final String EMAIL = "fculty@ncsu.edu";
	/** Test password */
	private static final String PASSWORD = "pw";
	/** unmatching password **/
	private static final String NOTPASS = "Pw";
	/** Test empty password */
	private static final String EMPTYPASS = "";
	/** Test null password */
	private static final String NULLPASS = null;
	/** Test max credits */
	private static final int MAX_COURSE = 1;
	

	/**
	 * Tests FacultyDirectory() constructor.
	 */
	@Test
	public void testFacultyDirectory() {
		//Test that the StudentDirectory is initialized to an empty list
		FacultyDirectory fd = new FacultyDirectory();
		assertFalse(fd.removeFaculty("sesmith5"));
		assertEquals(0, fd.getFacultyDirectory().length);
	}
	
	/**
	 * tests loading from a file
	 */
	@Test
	public void testLoadFaculty() {
		FacultyDirectory fd = new FacultyDirectory();
				
		//Test valid file
		fd.loadFacultyFromFile(validTestFile);
		assertEquals(8, fd.getFacultyDirectory().length);
		//make sure it is in correct order by testing first name
		assertEquals("Ashely", fd.getFacultyDirectory()[0][0]);
		
	}
	
	/**
	 * tests adding faculty
	 */
	@Test
	public void testAddFaculty() {
		FacultyDirectory fd = new FacultyDirectory();
		
		fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_COURSE);
		String[][] facultyDirectory = fd.getFacultyDirectory();
		assertEquals(1, facultyDirectory.length);
		assertEquals(FIRST_NAME, facultyDirectory[0][0]);
		assertEquals(LAST_NAME, facultyDirectory[0][1]);
		assertEquals(ID, facultyDirectory[0][2]);
		
		
		assertTrue(!fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_COURSE));
		
		try {
			fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, NOTPASS, MAX_COURSE);
			fail("shouldn't be able to create this, pw does not match");
		} catch (IllegalArgumentException e) {
			
		}
	}
	@Test
	public void testSaveFile() {
		FacultyDirectory fd = new FacultyDirectory();
		
		//add faculty Ashely,Witt,awitt,mollis@Fuscealiquetmagna.net,0ÉRú±"ÃùuŸ¦Ù\7X²F´þâ9•{-OîFâapÄ,2
		fd.addFaculty("Ashely", "Witt", "awitt", "mollis@Fuscealiquetmagna.net", "pw", 
				"pw", 2);
		//test to make sure faculty added
		assertEquals(1, fd.getFacultyDirectory().length);
		
		fd.saveFacultyDirectory("test-files/actual_faculty_directory.txt");
		checkFiles("test-files/expected_faculty_directory.txt", "test-files/actual_faculty_directory.txt" );
	}
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new FileInputStream(expFile));
			Scanner actScanner = new Scanner(new FileInputStream(actFile));
			
			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
	
	/**
	 * tests getting faculty by id
	 */
	@Test
	public void testGetFacultyById() {
		FacultyDirectory fd = new FacultyDirectory();
		
		//add faculty Ashely,Witt,awitt,mollis@Fuscealiquetmagna.net,0ÉRú±"ÃùuŸ¦Ù\7X²F´þâ9•{-OîFâapÄ,2
		fd.addFaculty("Ashely", "Witt", "awitt", "mollis@Fuscealiquetmagna.net", "pw", 
				"pw", 2);
		//test to make sure faculty added
		assertEquals(1, fd.getFacultyDirectory().length);
		Faculty a = new Faculty("Ashely", "Witt", "awitt", "mollis@Fuscealiquetmagna.net", 
				"pw", 2);
		assertEquals(a, fd.getFacultyById("awitt"));
		assertEquals(null, fd.getFacultyById("witt"));
	}
	/**
	 * tests removing a faculty
	 */
	@Test
	public void testRemoveFaculty() {
FacultyDirectory fd = new FacultyDirectory();
		
		//add faculty Ashely,Witt,awitt,mollis@Fuscealiquetmagna.net,0ÉRú±"ÃùuŸ¦Ù\7X²F´þâ9•{-OîFâapÄ,2
		fd.addFaculty("Ashely", "Witt", "awitt", "mollis@Fuscealiquetmagna.net", "pw", 
				"pw", 2);
		//test to make sure faculty added
		assertEquals(1, fd.getFacultyDirectory().length);
		
		assertTrue(fd.removeFaculty("awitt"));
		assertEquals(0, fd.getFacultyDirectory().length);
	}
}
