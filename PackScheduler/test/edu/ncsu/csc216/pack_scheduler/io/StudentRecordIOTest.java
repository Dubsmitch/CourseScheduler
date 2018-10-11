package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.io.StudentRecordIO;
import edu.ncsu.csc216.collections.list.SortedList;


/**
 * This tests the StudentRecordIO.java class and interacts
 * with testing files
 * 
 * @author William Mitchell
 *
 */
public class StudentRecordIOTest {

	/** Valid Student records **/
	private final String validTestFile = "test-files/student_records.txt";
	/** Invalid Student records **/
	private final String invalidTestFile = "test-files/invalid_student_records.txt";
	
	/** strings for students **/
	private String validStudent0 = "Zahir,King,zking,orci.Donec@ametmassaQuisque.com,pw,15";
	private String validStudent1 = "Cassandra,Schwartz,cschwartz,semper@imperdietornare.co.uk,pw,4";
	private String validStudent2 = "Shannon,Hansen,shansen,convallis.est.vitae@arcu.ca,pw,14";
	private String validStudent3 = "Demetrius,Austin,daustin,Curabitur.egestas.nunc@placeratorcilacus.co.uk,pw,18";
	private String validStudent4 = "Raymond,Brennan,rbrennan,litora.torquent@pellentesquemassalobortis.ca,pw,12";
	private String validStudent5 = "Emerald,Frost,efrost,adipiscing@acipsumPhasellus.edu,pw,3";
	private String validStudent6 = "Lane,Berg,lberg,sociis@non.org,pw,14";
	private String validStudent7 = "Griffith,Stone,gstone,porta@magnamalesuadavel.net,pw,17";
	private String validStudent8 = "Althea,Hicks,ahicks,Phasellus.dapibus@luctusfelis.com,pw,11";
	private String validStudent9 = "Dylan,Nolan,dnolan,placerat.Cras.dictum@dictum.net,pw,5";
	
	/** creating an array from those string **/
	private String [] validStudents = {validStudent3, validStudent6, validStudent4, validStudent5, validStudent2, validStudent8, validStudent0,
	        validStudent9, validStudent1, validStudent7};
	
	/** create an array from those strings **/
	//private String [] validStudents = {validStudent0, validStudent1, validStudent2, validStudent3, validStudent4, validStudent5, validStudent6,
	//		validStudent7, validStudent8, validStudent9};

	private String hashPW;
	/** used to make hashed passwords from the strings provided **/
	private static final String HASH_ALGORITHM = "SHA-256";
	
	/**
	 * helper method to read from the two files
	 * one I created and one that is expected
	 */
	@Before
	public void setUp() {
	    try {
	        String password = "pw";
	        MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
	        digest.update(password.getBytes());
	        hashPW = new String(digest.digest());
	        
	        for (int i = 0; i < validStudents.length; i++) {
	            validStudents[i] = validStudents[i].replace(",pw,", "," + hashPW + ",");
	        }
	    } catch (NoSuchAlgorithmException e) {
	        fail("Unable to create hash during setup");
	    }
	}
	
	/**
	 * helper method to read from the two files
	 * one I created and one that is expected
	 * @param expFile
	 * 			expFile is the expected File that is provided
	 * @param actFile
	 * 			actFile is the file that my test will create, to be checked
	 */
	private void checkFiles(String expFile, String actFile) {
	    try {
	        Scanner expScanner = new Scanner(new FileInputStream(expFile));
	        Scanner actScanner = new Scanner(new FileInputStream(actFile));
	        
	        while (expScanner.hasNextLine()  && actScanner.hasNextLine()) {
	            String exp = expScanner.nextLine();
	            String act = actScanner.nextLine();
	            assertEquals("Expected: " + exp + " Actual: " + act, exp, act);
	        }
	        if (expScanner.hasNextLine()) {
	            fail("The expected results expect another line " + expScanner.nextLine());
	        }
	        if (actScanner.hasNextLine()) {
	            fail("The actual results has an extra, unexpected line: " + actScanner.nextLine());
	        }
	        
	        expScanner.close();
	        actScanner.close();
	    } catch (IOException e) {
	        fail("Error reading files.");
	    }
	}
	
	/**
	 * tests readStudentRecords method
	 */
	@Test
	public void testReadStudentRecords() {
		try {
			SortedList<Student> students = StudentRecordIO.readStudentRecords(validTestFile);
			assertEquals(10, students.size());
			//assertEquals("asdf",students.get(2).toString());
			
			for (int i = 0; i < validStudents.length; i++) {
				assertEquals(validStudents[i], students.get(i).toString());
			}
		} catch (FileNotFoundException e) {
			fail("Unexpected error reading " + validTestFile);
		}

	}
	/**
	 * test to pass in a null first name
	 */
	@Test
	public void testStudentRecordsHelper() {
	SortedList<Student> students; 
	try { 
		students = StudentRecordIO.readStudentRecords("test-files/null.txt");
		assertEquals (2, students.size());
	} catch (FileNotFoundException e) {
		fail("Unexpected error reading duplicate_student_records.txt");
	}
}
	/**
	 * Tests readStudentRecords) by failing duplicate test
	 */
	@Test
	public void testReadStudentRecordsDuplicate() {
		SortedList<Student> students; 
		try { 
			students = StudentRecordIO.readStudentRecords("test-files/duplicate_student_records.txt");
			assertEquals (1, students.size());
		} catch (FileNotFoundException e) {
			fail("Unexpected error reading duplicate_student_records.txt");
		}
	}
	/**
	 * Tests readStudentRecords() by providing invalid student records
	 */
	@Test
	public void testReadInvalidStudentRecords() {
		SortedList<Student> students;
		try {
			students = StudentRecordIO.readStudentRecords(invalidTestFile);
			assertEquals(0, students.size());
		} catch (FileNotFoundException e) {
			fail("Unexpected FileNotFoundException");
		}
	}
	
	/**
	 * tests writeStudentRecords() by providing a pathway to which
	 * I do not have permission (nothing should be attempted to be written)
	 */
	@Test
	public void testWriteStudentRecordsNoPermissions() {
	    SortedList<Student> students = new SortedList<Student>();
	    students.add(new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 15));
	    //Assumption that you are using a hash of "pw" stored in hashPW
	    
	    try {
	        StudentRecordIO.writeStudentRecords("/home/sesmith5/actual_student_records.txt", students);
	        fail("Attempted to write to a directory location that doesn't exist or without the appropriate permissions and the write happened.");
	    } catch (IOException e) {
	        assertEquals("/home/sesmith5/actual_student_records.txt (Permission denied)", e.getMessage());
	        //The actual error message on Jenkins!
	    }
	    
	}
	
	/**
	 * test writeStudentRecords; compares written file to
	 * a provided test file
	 */
	@Test
	public void testWriteStudentRecords() {
		SortedList<Student> students = new SortedList<Student>();
	    students.add(new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 15));

		
		try {
			StudentRecordIO.writeStudentRecords("test-files/actual_course_records.txt", students);
		} catch (IOException e) {
			fail("Cannot write to course records file");
		}
		
		checkFiles("test-files/expected_student_records.txt", "test-files/actual_student_records.txt");
	}

}
