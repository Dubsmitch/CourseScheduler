package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;


/**
*
* Reads Student records from text files.  Can also write a set of Student records to a file.
* 
* @author William
*@version 9/6/2018
*/

public class FacultyRecordIO {
	
	/**
     * Reads Student records from a file and generates a list of valid Students.  Any duplicate or invalid
     * Students are ignored.  If the file to read cannot be found or the permissions are incorrect
     * a File NotFoundException is thrown.
     * @param fileName file to read Student records from
     * @return a sorted list of valid Students
     * @throws FileNotFoundException if the file cannot be found or read
     */
	public static LinkedList<Faculty> readStudentRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		LinkedList<Faculty> facultys = new LinkedList<Faculty>();
	    while (fileReader.hasNextLine()) {
	    	try {
	            Faculty faculty = processFaculty(fileReader.nextLine());
		        //did this statement below but it was failing because 
	            //line 41 was throwing 'null pointer'
	            	//if (student != null) {
	            		//continue;
	            	//}	
	            	boolean duplicate = false;
	            	for (int i = 0; i < facultys.size(); i++) {
	            		Faculty s = facultys.get(i);
	            		//null pointer exception was occuring so I added a null test
	            		if (faculty == null) {
	            			duplicate = true;
	            		} else if (faculty.getId().equals(s.getId())) {
	            			//it's a duplicate
	            			duplicate = true;
	            		}
	            	
	            	}
	            	if (!duplicate) {
	            		facultys.add(faculty);
	            	}
	    		} catch (IllegalArgumentException e) {
	    			break;
	    		}	
	    
	    	}
	    fileReader.close();
	    return facultys;
	}

	
	/**
	 * Reads Student information from a line and creates a Student object from 
	 * that information and passes it into the Student sorted list constructor
	 * 
	 * @param nextLine from a scanner object that contains student information
	 * @return student the student that will be added to a sorted list of students
	 */
	private static Faculty processFaculty (String line) {
		//firstName, String lastName, String id, String email, String password, int maxCredits
		Scanner lineScanner = new Scanner(line);
		lineScanner.useDelimiter(",");
		String firstName = "";
		String lastName = "";
		String id = "";
		String email = "";
		String password = "";
		int maxCourses = 0;
		
		if (lineScanner.hasNext()) {
			firstName = lineScanner.next();
		} else {
			lineScanner.close();
			return null;
			
		}
		
		if (lineScanner.hasNext()) {
			lastName = lineScanner.next();
		} else {
			lineScanner.close();
			return null;
		}
		
		if (lineScanner.hasNext()) {
			id = lineScanner.next();
		} else {
			lineScanner.close();
			return null;
		}
		
		if (lineScanner.hasNext()) {
			email = lineScanner.next();
		} else {
			lineScanner.close();
			return null;
		}
		
		if (lineScanner.hasNext()) {
			 password = lineScanner.next();
		} else {
			lineScanner.close();
			return null;
		}
		
		if (lineScanner.hasNextInt()) {
			maxCourses = lineScanner.nextInt();
			Faculty s = new Faculty (firstName, lastName, id, email, password, maxCourses);
			lineScanner.close();
			return s;
		} else {
			lineScanner.close();
			return null;
		}
		
	}
	
	/**
     * Writes the given list of Courses to a file
     * The IOException has been caught in the StudentDirectory class
     * 
     * @param fileName name of the file to which the directory will be written
     * @param studentDirectory Sorted list of Students to be written to the file
	 * @throws FileNotFoundException thrown if the file of filename cannot be found
	 * (this is thrown by StudentDirectory class)
     * 
     */
	public static void writeStudentRecords(String fileName, LinkedList<Faculty> facultyDirectory)
			throws FileNotFoundException {
		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < facultyDirectory.size(); i++) {
		    fileWriter.println(facultyDirectory.get(i).toString());
		}

		fileWriter.close();
		
	}

}

