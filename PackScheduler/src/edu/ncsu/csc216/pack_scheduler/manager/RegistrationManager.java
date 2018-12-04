package edu.ncsu.csc216.pack_scheduler.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.ConflictException;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;
/**
 * creates and manages the registration system including
 * the coursecatalog, studentDirectory, an instance of the
 * manager, and the registrar
 * 
 * @author William
 *
 */
public class RegistrationManager {
	
	private static RegistrationManager instance;
	  private CourseCatalog courseCatalog;
	 
	private StudentDirectory studentDirectory;
	  private User registrar;
	  private User currentUser;
	private FacultyDirectory facultyDirectory;
	
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	private static final String PROP_FILE = "registrar.properties";
	
	/**
	 * creates an instance of the registrar, manager, courseCatalog
	 * and the studentDirectory
	 */
	private RegistrationManager() {
		createRegistrar();
		CourseCatalog c1 = new CourseCatalog();
		//c1.loadCoursesFromFile("test-files/actual_t39_course_catalog.txt");
		courseCatalog = c1;
		
		StudentDirectory sd = new StudentDirectory();
		//sd.loadStudentsFromFile("test-files/actual_t19_student_directory.txt");
		studentDirectory = sd;
		
		//RegistrationManager manager = RegistrationManager.getInstance();
		
		facultyDirectory = new FacultyDirectory();
	}
	/**
	 * creates a registrar from a file
	 */
	private void createRegistrar() {
		Properties prop = new Properties();
		
		try (InputStream input = new FileInputStream(PROP_FILE)) {
			prop.load(input);
			
			String hashPW = hashPW(prop.getProperty("pw"));
			
			registrar = new Registrar(prop.getProperty("first"), prop.getProperty("last"), 
					prop.getProperty("id"), prop.getProperty("email"), hashPW);
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot create registrar.");
		}
	}
	/**
	 * hashes the password
	 * @param pw
	 * 			the password
	 * @return String
	 * 			the hashed password
	 */
	private String hashPW(String pw) {
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(pw.getBytes());
			return new String(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
	}
	/**
	 * creates an instance of the registration manager 
	 * of if one exists then it returns the current manager
	 * @return registrationManager
	 * 			the current instance of the manager
	 */
	public static RegistrationManager getInstance() {
		  if (instance == null) {
			instance = new RegistrationManager();
			
		}
		return instance;
	}
	/**
	 * provides the current course catalog
	 * @return course Catalog
	 * 		the current course catalog
	 */
	public CourseCatalog getCourseCatalog() {
		return courseCatalog;
	}
	/**
	 * provides the current student Directory
	 * @return StudentDirectory
	 * 			returns the student Directory
	 */
	public StudentDirectory getStudentDirectory() {
		return studentDirectory;
	}
	/**
	 * provides the current faculty directory
	 * 
	 * @return FacultyDirectory
	 * 		the current faculty directory
	 */
	public FacultyDirectory getFacultyDirectory() {
		return facultyDirectory;
	}
	/**
	 * logs in the current user
	 * @param id
	 * 			the unity id of the user
	 * @param password
	 * 			the password of the user
	 * @return
	 * 		if the user can log in or not
	 */
	public boolean login(String id, String password) {
		if (registrar.getId().equals(id)) {
			MessageDigest digest;
			try {
				digest = MessageDigest.getInstance(HASH_ALGORITHM);
				digest.update(password.getBytes());
				String localHashPW = new String(digest.digest());
					if (registrar.getPassword().equals(localHashPW)) {
						currentUser = registrar;
						return true;
					}
			} catch (NoSuchAlgorithmException e) {
				throw new IllegalArgumentException();
			}
		}	
		
		Student s = studentDirectory.getStudentById(id);
		try {
			MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
			digest.update(password.getBytes());
			String localHashPW = new String(digest.digest());
			if(s == null) {
				throw new IllegalArgumentException ("User doesn't exist.");
			}
			if (s.getPassword().equals(localHashPW)) {
				currentUser = s;
				return true;
			}
		} catch (NoSuchAlgorithmException e){
				throw new IllegalArgumentException("user doesn't exist.");
		}	
		
		Faculty f = facultyDirectory.getFacultyById(id);
		try {
			MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
			digest.update(password.getBytes());
			String localHashPW = new String(digest.digest());
			if(f == null) {
				throw new IllegalArgumentException ("User doesn't exist.");
			}
			if (f.getPassword().equals(localHashPW)) {
				currentUser = f;
				return true;
			}
		} catch (NoSuchAlgorithmException e){
				throw new IllegalArgumentException("user doesn't exist.");
		}	
		
			
	return false;
	}
	/**
	 * log's out the user;
	 */
	public void logout() {
		currentUser = registrar; 
	}
	
	/**
	 * returns the type of user
	 * 
	 * @return User
	 * 			type of user
	 */
	public User getCurrentUser() {
		return currentUser;
	}
	/**
	 * clears the student directory and course catalog
	 */
	public void clearData() {
		courseCatalog = new CourseCatalog();
		
		studentDirectory = new StudentDirectory();
	}
	
	/**
	 * innerclass to create a registrar user
	 * @author William
	 *
	 */
	private static class Registrar extends User {
		/**
		 * Create a registrar user with the user id and password 
		 * in the registrar.properties file.
		 */
		public Registrar(String firstName, String lastName, String id, String email, String hashPW) {
			super(firstName, lastName, id, email, hashPW);
		}
	}
	/**
	 * Returns true if the logged in student can enroll in the given course.
	 * @param c Course to enroll in
	 * @return true if enrolled
	 */
	public boolean enrollStudentInCourse(Course c) {
	    if (currentUser == null || !(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        Schedule schedule = s.getSchedule();
	        CourseRoll roll = c.getCourseRoll();
	        
	        if (s.canAdd(c) && roll.canEnroll(s)) {
	            schedule.addCourseToSchedule(c);
	            roll.enroll(s);
	            return true;
	        }
	        
	    } catch (IllegalArgumentException e) {
	        return false;
	    } catch (ConflictException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return false;
	}

	/**
	 * Returns true if the logged in student can drop the given course.
	 * @param c Course to drop
	 * @return true if dropped
	 */
	public boolean dropStudentFromCourse(Course c) {
	    if (currentUser == null || !(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        c.getCourseRoll().drop(s);
	        return s.getSchedule().removeCourseFromSchedule(c);
	    } catch (IllegalArgumentException e) {
	        return false; 
	    }
	}
	
	/**
	 * adds a faculty member to a course
	 * @param course
	 * 		the course to be added to
	 * @param faculty
	 * 		the faculty memeber to add the course to
	 * @return boolean
	 * 		if a course gets added
	 */
	public boolean addFacultyToCourse(Course course, Faculty faculty) {
		if (currentUser != null && currentUser instanceof Registrar) {
			faculty.getSchedule().addCourseToSchedule(course);
			return true;
		}
		return false;
	}
	/**
	 * removes a faculty member to a course
	 * @param course
	 * 		the course to be added to
	 * @param faculty
	 * 		the faculty member to add the course to
	 * @return boolean
	 * 		if a course gets removed
	 */
	public boolean removeFacultyFromCourse(Course course, Faculty faculty) {
		if (currentUser != null && currentUser instanceof Registrar) {
			faculty.getSchedule().removeCourseFromSchedule(course);
			return true;
		}
		return false;
	}
	/**
	 * resets a faculty member's schedule
	 * @param faculty
	 * 		the faculty member to add the course to
	 */
	public void resetFacultySchedule(Faculty faculty) {
		if (currentUser != null && currentUser instanceof Registrar) {
			faculty.getSchedule().resetSchedule();
		}
	}
	/**
	 * Resets the logged in student's schedule by dropping them
	 * from every course and then resetting the schedule.
	 */
	public void resetSchedule() {
	    if (currentUser == null || !(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        Schedule schedule = s.getSchedule();
	        String [][] scheduleArray = schedule.getScheduledCourses();
	        for (int i = 0; i < scheduleArray.length; i++) {
	            Course c = courseCatalog.getCourseFromCatalog(scheduleArray[i][0], scheduleArray[i][1]);
	            c.getCourseRoll().drop(s);
	        }
	        schedule.resetSchedule();
	    } catch (IllegalArgumentException e) {
	    }
	}	 
}
