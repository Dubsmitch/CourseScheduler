package edu.ncsu.csc216.pack_scheduler.io;

import java.io.*;
import java.util.*;

import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
*
* Reads Student records from text files.  Can also write a set of Student records to a file.
* 
* @author William
*@version 9/6/2018
*/

public class StudentRecordIO {
	
	/**
     * Reads Student records from a file and generates a list of valid Students.  Any duplicate or invalid
     * Students are ignored.  If the file to read cannot be found or the permissions are incorrect
     * a File NotFoundException is thrown.
     * @param fileName file to read Student records from
     * @return a list of valid Students
     * @throws FileNotFoundException if the file cannot be found or read
     */
	public static ArrayList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
	    ArrayList<Student> students = new ArrayList<Student>();
	    while (fileReader.hasNextLine()) {
	        try {
	            Student student = processStudent(fileReader.nextLine());
	            boolean duplicate = false;
	            for (int i = 0; i < students.size(); i++) {
	                Student s = students.get(i);
	                if (student.getId().equals(s.getId())) {
	                    //it's a duplicate
	                    duplicate = true;
	                }
	            }
	            if (!duplicate) {
	                students.add(student);
	            }
	        } catch (IllegalArgumentException e) {
	            //skip the line
	        }
	    }
	    fileReader.close();
	    return students;
	}

	
	/**
	 * Reads Student information from a line and creates a Student object from 
	 * that information and passes it into the Student array list constructor
	 * 
	 * @param nextLine from a scanner object that contains student information
	 * @return student the student that will be added to an array of students
	 * @throws IllegalArgumentExpression if all fields are not entered correctly.
	 */
	private static Student processStudent (String line) {
		//firstName, String lastName, String id, String email, String password, int maxCredits
		Scanner lineScanner = new Scanner(line);
		lineScanner.useDelimiter(",");
		String firstName = "";
		String lastName = "";
		String id = "";
		String email = "";
		String password = "";
		int maxCredits = 0;
		
		if (lineScanner.hasNext()) {
			firstName = lineScanner.next();
		} else {
			lineScanner.close();
			throw new IllegalArgumentException();
			
		}
		
		if (lineScanner.hasNext()) {
			lastName = lineScanner.next();
		} else {
			lineScanner.close();
			throw new IllegalArgumentException();
		}
		
		if (lineScanner.hasNext()) {
			id = lineScanner.next();
		} else {
			lineScanner.close();
			throw new IllegalArgumentException();
		}
		
		if (lineScanner.hasNext()) {
			email = lineScanner.next();
		} else {
			lineScanner.close();
			throw new IllegalArgumentException();
		}
		
		if (lineScanner.hasNext()) {
			 password = lineScanner.next();
		} else {
			lineScanner.close();
			throw new IllegalArgumentException();
		}
		
		if (lineScanner.hasNextInt()) {
			maxCredits = lineScanner.nextInt();
			Student s = new Student (firstName, lastName, id, email, password, maxCredits);
			lineScanner.close();
			return s;
		} else {
			Student s = new Student (firstName, lastName, id, email, password);
			lineScanner.close();
			return s;
		}
		
	}
	
	/**
     * Writes the given list of Courses to a file
     * The IOException has been caught in the StudentDirectory class
     * 
     * @param fileName name of the file to which the directory will be written
     * @param studentDirectory Array list of Students to be written to the file
	 * @throws FileNotFoundException thrown if the file of filename cannot be found
	 * (this is thrown by StudentDirectory class)
     * 
     */
	public static void writeStudentRecords(String fileName, ArrayList<Student> studentDirectory) throws FileNotFoundException {
		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < studentDirectory.size(); i++) {
		    fileWriter.println(studentDirectory.get(i).toString());
		}

		fileWriter.close();
		
	}

}
