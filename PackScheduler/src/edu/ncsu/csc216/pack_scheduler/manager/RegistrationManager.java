package edu.ncsu.csc216.pack_scheduler.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
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
				throw new IllegalArgumentException ("User doesn't exist");
			}
			if (s.getPassword().equals(localHashPW)) {
				currentUser = s;
				return true;
			}
		} catch (NoSuchAlgorithmException e) {
				throw new IllegalArgumentException("user doesn't exist");
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
}
