package edu.ncsu.csc216.pack_scheduler.io;

import java.io.*;
import java.util.*;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.wolf_scheduler.course.Course;

public class StudentRecordIO {

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
	
	public static void writeStudentRecords(String fileName, ArrayList<Student> studentDirectory) throws FileNotFoundException {
		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < studentDirectory.size(); i++) {
		    fileWriter.println(studentDirectory.get(i).toString());
		}

		fileWriter.close();
		
	}

}
