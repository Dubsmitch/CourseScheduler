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

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

public class FacultyRecordIOTest {

	/** Valid Faculty records **/
	private final String validTestFile = "test-files/faculty_records.txt";
	/** Invalid Faculty records **/
	private final String invalidTestFile = "test-files/invalid_faculty_records.txt";
	
	/** strings for Facultys **/
	private String validFaculty0 = "Ashely,Witt,awitt,mollis@Fuscealiquetmagna.net,0ÉRú±\"ÃùuŸ¦Ù\\7X²F´þâ9•{-OîFâapÄ,2";
	private String validFaculty1 = "Fiona,Meadows,fmeadow,pharetra.sed@et.org,0ÉRú±\"ÃùuŸ¦Ù\\7X²F´þâ9•{-OîFâapÄ,3";
	private String validFaculty2 = "Brent,Brewer,bbrewer,sem.semper@orcisem.co.uk,0ÉRú±\"ÃùuŸ¦Ù\\7X²F´þâ9•{-OîFâapÄ,1";
	private String validFaculty3 = "Halla,Aguirre,haguirr,Fusce.dolor.quam@amalesuadaid.net,0ÉRú±\"ÃùuŸ¦Ù\\7X²F´þâ9•{-OîFâapÄ,3";
	private String validFaculty4 = "Kevyn,Patel,kpatel,risus@pellentesque.ca,0ÉRú±\"ÃùuŸ¦Ù\\7X²F´þâ9•{-OîFâapÄ,1";
	private String validFaculty5 = "Elton,Briggs,ebriggs,arcu.ac@ipsumsodalespurus.edu,0ÉRú±\"ÃùuŸ¦Ù\\7X²F´þâ9•{-OîFâapÄ,3";
	private String validFaculty6 = "Norman,Brady,nbrady,pede.nonummy@elitfermentum.co.uk,0ÉRú±\"ÃùuŸ¦Ù\\7X²F´þâ9•{-OîFâapÄ,1";
	private String validFaculty7 = "Lacey,Walls,lwalls,nascetur.ridiculus.mus@fermentum.net,0ÉRú±\"ÃùuŸ¦Ù\\7X²F´þâ9•{-OîFâapÄ,2";
	
	/** creating an array from those string **/
	private String [] validFacultys = {validFaculty0, validFaculty1, validFaculty2, validFaculty3, validFaculty4, validFaculty5,
	        validFaculty6, validFaculty7};
	
	/** create an array from those strings **/
	//private String [] validFacultys = {validFaculty0, validFaculty1, validFaculty2, validFaculty3, validFaculty4, validFaculty5, validFaculty6,
	//		validFaculty7, validFaculty8, validFaculty9};

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
	        
	        for (int i = 0; i < validFacultys.length; i++) {
	            validFacultys[i] = validFacultys[i].replace(",pw,", "," + hashPW + ",");
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
	 * tests readFacultyRecords method
	 */
	@Test
	public void testReadFacultyRecords() {
		try {
			LinkedList<Faculty> Facultys = FacultyRecordIO.readFacultyRecords(validTestFile);
			assertEquals(8, Facultys.size());
			//assertEquals("asdf",Facultys.get(2).toString());
			
			for (int i = 0; i < validFacultys.length; i++) {
				assertEquals(validFacultys[i], Facultys.get(i).toString());
			}
		} catch (FileNotFoundException e) {
			fail("Unexpected error reading " + validTestFile);
		}

	}
	/**
	 * test to pass in a null first name
	 */
	@Test
	public void testFacultyRecordsHelper() {
		LinkedList<Faculty> Facultys; 
	try { 
		Facultys = FacultyRecordIO.readFacultyRecords("test-files/null.txt");
		assertEquals (0, Facultys.size());
	} catch (FileNotFoundException e) {
		fail("Unexpected error reading duplicate_Faculty_records.txt");
	}
}
	
	/**
	 * Tests readFacultyRecords() by providing invalid Faculty records
	 */
	@Test
	public void testReadInvalidFacultyRecords() {
		LinkedList<Faculty> Facultys;
		try {
			Facultys = FacultyRecordIO.readFacultyRecords(invalidTestFile);
			assertEquals(0, Facultys.size());
		} catch (FileNotFoundException e) {
			fail("Unexpected FileNotFoundException");
		} catch (NullPointerException e) {
		}
	}
	
	/**
	 * tests writeFacultyRecords() by providing a pathway to which
	 * I do not have permission (nothing should be attempted to be written)
	 */
	@Test
	public void testWriteFacultyRecordsNoPermissions() {
		LinkedList<Faculty> Facultys = new LinkedList<Faculty>();
	    Facultys.add(new Faculty("Ashely", "Witt", "awitt", "mollis@Fuscealiquetmagna.net", "0ÉRú±\"ÃùuŸ¦Ù\\7X²F´þâ9•{-OîFâapÄ", 2));
	    //Assumption that you are using a hash of "pw" stored in hashPW
	    
	    try {
	        FacultyRecordIO.writeFacultyRecords("/home/sesmith5/actual_faculty_records.txt", Facultys);
	        fail("Attempted to write to a directory location that doesn't exist or without the appropriate permissions and the write happened.");
	    } catch (IOException e) {
	        //assertEquals("/home/sesmith5/actual_faculty_records.txt (Permission denied)", e.getMessage());
	        //The actual error message on Jenkins!
	    }
	    
	}
	
	/**
	 * test writeFacultyRecords; compares written file to
	 * a provided test file
	 */
	@Test
	public void testWriteFacultyRecords() {
		LinkedList<Faculty> Facultys = new LinkedList<Faculty>();
	    Facultys.add(new Faculty("Ashely", "Witt", "awitt", "mollis@Fuscealiquetmagna.net", "0ÉRú±\"ÃùuŸ¦Ù\\7X²F´þâ9•{-OîFâapÄ", 2));

		
		try {
			FacultyRecordIO.writeFacultyRecords("test-files/actual_facultyy_records.txt", Facultys);
		} catch (IOException e) {
			fail("Cannot write to course records file");
		}
		
		checkFiles("test-files/expected_faculty_records.txt", "test-files/actual_facultyy_records.txt");
	}

}

