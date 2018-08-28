package edu.ncsu.csc216.pack_scheduler.io;

import java.io.*;
import java.util.ArrayList;
import java.util.*;

import edu.ncsu.csc216.pack_scheduler.user.Student;

public class StudentRecordIO {

	public static ArrayList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
	    ArrayList<Student> students = new ArrayList<Student>();
	    while (fileReader.hasNextLine()) {
	        try {
	            Student student = processStudent(fileReader.nextLine());
	            boolean duplicate = false;
	            for (int i = 0; i < students.size(); i++) {
	                Student c = students.get(i);
	                if (student.getId().equals(c.getId())) {
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

	private Student processStudent (String line) {
		Student = 
		return Student;	
	}
	
	public static void writeStudentRecords(String fileName, ArrayList<Student> studentDirectory) throws IOException {
		// TODO Auto-generated method stub
		
	}

}
