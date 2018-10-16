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

public class RegistrationManager {
	
	private static RegistrationManager instance;
	  private CourseCatalog courseCatalog;
	 
	private StudentDirectory studentDirectory;
	  private User registrar;
	  private User currentUser;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	private static final String PROP_FILE = "registrar.properties";

	private RegistrationManager() {
		createRegistrar();
		courseCatalog = new CourseCatalog();
		studentDirectory = new StudentDirectory();
		
		//RegistrationManager manager = RegistrationManager.getInstance();
	}
	
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
	
	private String hashPW(String pw) {
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(pw.getBytes());
			return new String(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
	}
	
	public static RegistrationManager getInstance() {
		  if (instance == null) {
			instance = new RegistrationManager();
			
		}
		return instance;
	}
	
	public CourseCatalog getCourseCatalog() {
		return courseCatalog;
	}
	
	public StudentDirectory getStudentDirectory() {
		return studentDirectory;
	}

	public boolean login(String id, String password) {
		Student s = studentDirectory.getStudentById(id);
		try {
		MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
		digest.update(password.getBytes());
		String localHashPW = new String(digest.digest());
		if (s.getPassword().equals(localHashPW)) {
			currentUser = s;
				return true;
		}
		} catch (NoSuchAlgorithmException e) {
				throw new IllegalArgumentException();
		}	
		
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
			
				return false;
	}

	public void logout() {
		currentUser = registrar; 
	}
	
	/**
	 * @return 
	 */
	public User getCurrentUser() {
		return registrar; 
	}
	
	public void clearData() {
		courseCatalog = new CourseCatalog();
		
		studentDirectory = new StudentDirectory();
	}
	
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
