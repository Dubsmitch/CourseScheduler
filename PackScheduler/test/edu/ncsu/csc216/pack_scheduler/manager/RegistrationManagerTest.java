package edu.ncsu.csc216.pack_scheduler.manager;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Student;


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
		
		//CourseCatalog cb = RegistrationManager();
		
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
	/**
	 * tests the login functionality
	 */
	@Test
	public void testLogin() {
//		the manager has already been set up so the registrar should already exist
		
		assertTrue(manager.login("wimitch", "asdf"));
		
	}
	/**
	 * tests the logout method
	 */
	@Test
	public void testLogout() {
		StudentDirectory sd = manager.getStudentDirectory();
		sd.loadStudentsFromFile("test-files/actual_student_records.txt");
		Student a = new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", "asdf", 15);
		manager.login("zking", "asdf");
		
		manager.logout();
		
		assertFalse(manager.getCurrentUser().equals(a));
	}
	
	
	/**
	 * tests if the current user can be fetched
	 */
	@Test
	public void testGetCurrentUser() {
		StudentDirectory sd = manager.getStudentDirectory();
		sd.loadStudentsFromFile("test-files/actual_student_records.txt");
		Student a = new Student("William", "Mitchell", "wimitcc", "wimitchell@ncsu.edu", "asdf", 15);
		manager.login("wimitcc", "asdf");
		
		assertEquals(manager.getCurrentUser(), null);
	}

}

