package edu.ncsu.csc216.pack_scheduler.manager;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;


public class RegistrationManagerTest {
	
	private RegistrationManager manager;
	
	/**
	 * Sets up the CourseManager and clears the data.
	 * @throws Exception if error
	 */
	@Before
	public void setUp() throws Exception {
		manager = RegistrationManager.getInstance();
		manager.clearData();
	}
	
	/**
	 * this test should return a course catalog that matches the
	 * current course catalog
	 */
	@Test
	public void testGetCourseCatalog() {
		
		CourseCatalog ca = manager.getCourseCatalog();
		assertEquals(ca.getCourseCatalog().length, 0);
	}
	
	/**
	 * should return an empty directory from the manager
	 * (because it was cleared)
	 */
	@Test
	public void testGetStudentDirectory() {
		StudentDirectory sd = manager.getStudentDirectory();
		assertEquals(sd.getStudentDirectory().length, 0);
	}

//	@Test
//	public void testLogin() {
//		fail("Not yet implemented");
//	}

//	@Test
//	public void testLogout() {
//		fail("Not yet implemented");
//	}

//	@Test
//	public void testGetCurrentUser() {
//		fail("Not yet implemented");
//	}

}

