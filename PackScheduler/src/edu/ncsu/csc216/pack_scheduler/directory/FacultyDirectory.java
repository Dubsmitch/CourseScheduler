package edu.ncsu.csc216.pack_scheduler.directory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import edu.ncsu.csc216.pack_scheduler.io.FacultyRecordIO;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

public class FacultyDirectory {
	/** the hash algorithm **/
	private static final String HASH_ALGORITHM = "SHA-256";

	/** facultyDirectory that contains faculty **/
	private LinkedList<Faculty> facultyDirectory;
	
	/**
	 * creates a new FacultyDirectory
	 */
	public FacultyDirectory() {
		this.facultyDirectory = new LinkedList<Faculty>();
	}
	
	/**
	 * creates a new facultyDirectory to replace the current one
	 */
	public void newFacultyDirectory() {
		this.facultyDirectory = new LinkedList<Faculty>();
	}
	/**
	 * loads a list of faculty from a file
	 * the filename is given as a parameter.
	 * 
	 * @param fileName
	 * 		the name of the file to load from
	 */
	public void loadFacultyFromFile(String fileName) {
		try {
			facultyDirectory = FacultyRecordIO.readFacultyRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}
	}
	/**
	 * adds a faculty member to the directory
	 * @param firstName
	 * 		the first name of the faculty member
	 * @param lastName
	 * 		the last name of the faculty member
	 * @param id
	 * 		the id of the faculty member
	 * @param email
	 * 		the email of the faculty member
	 * @param password
	 * 		the password of the faculty member
	 * @param rePassword
	 * 		the repeated password of the faculty member
	 * @param maxCourses
	 * 		the number of courses taught by the faculty member
	 * @return boolean
	 * 		whether or not a faculty member can be added to the directory
	 */
	public boolean addFaculty(String firstName, String lastName, String id, String email,
			String password, String rePassword, int maxCourses) {
		//boolean canI = true;
		String hashPW = "";
		String repeatHashPW = "";
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(password.getBytes());
			hashPW = new String(digest1.digest());
			
			MessageDigest digest2 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest2.update(rePassword.getBytes());
			repeatHashPW = new String(digest2.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
		
		if (!hashPW.equals(repeatHashPW)) {
			throw new IllegalArgumentException("Passwords do not match");
		}
		
		Faculty newFaculty = new Faculty(firstName, lastName, id, email, password, maxCourses);
		
		for (int i = 0; i < facultyDirectory.size(); i++) {
			Faculty a = facultyDirectory.get(i);
			if (a.getId().equals(newFaculty.getId())) {
				//throw new IllegalArgumentException ("Faculty already in system.");
				return false;
			}
		}
		
		facultyDirectory.add(0, newFaculty);
		
		return false;
	}
	/**
	 * removes a faculty member from the directory
	 * @param facultyId
	 * 		the faculty member's id
	 * @return boolean
	 * 		whether or not a faculty member can be removed
	 */
	public boolean removeFaculty(String facultyId) {
		for (int i = 0; i <facultyDirectory.size(); i++) {
			Faculty a = facultyDirectory.get(i);
			if (a.getId().equals(facultyId)) {
				facultyDirectory.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * returns all faculty in the directory with a column
	 * for the first name, last name and id
	 * 
	 * @return String[][]
	 * 		the 2D array containing firstname, lastName and id
	 */
	public String[][] getFacultyDirectory() {
		String [][] directory = new String[facultyDirectory.size()][3];
		for (int i = 0; i < facultyDirectory.size(); i++) {
			Faculty s = facultyDirectory.get(i);
			directory[i][0] = s.getFirstName();
			directory[i][1] = s.getLastName();
			directory[i][2] = s.getId();
		}
		return directory;
	}
	
	public void saveFacultyDirectory(String fileName) {
		try {
			FacultyRecordIO.writeFacultyRecords(fileName, facultyDirectory);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to write to file " + fileName);
		}
	}
	
	public Faculty getFacultyById(String facultyId) {
		for (int i = 0; i <facultyDirectory.size(); i++) {
			Faculty a = facultyDirectory.get(i);
			if (a.getId().equals(facultyId)) {
				return a;	
			}
		}
		
		return null;
	}
}
