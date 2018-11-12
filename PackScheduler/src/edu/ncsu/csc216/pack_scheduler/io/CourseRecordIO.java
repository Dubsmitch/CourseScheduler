/**
 * @author William
 *
 */
package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import edu.ncsu.csc216.collections.list.SortedList;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 *
 * Reads Course records from text files.  Writes a set of CourseRecords to a file.
 * 
 * @author Sarah Heckman
 * @author William
 *
 */
public class CourseRecordIO {

    /**
     * Reads course records from a file and generates a list of valid Courses.  Any invalid
     * Courses are ignored.  If the file to read cannot be found or the permissions are incorrect
     * a File NotFoundException is thrown.
     * @param fileName 
     * 			file to read Course records from
     * @return SortedList<Course>
     * 			a list of valid Courses
     * @throws FileNotFoundException 
     * 			if the file cannot be found or read
     * 			
     * @throws InvalidTransitionException 
     * 			Throws an invalid transition exception if the course name is not valid
     */
	public static SortedList<Course> readCourseRecords(String fileName) throws FileNotFoundException, InvalidTransitionException {
	    Scanner fileReader = new Scanner(new FileInputStream(fileName));
	    SortedList<Course> courses = new SortedList<Course>();
	    while (fileReader.hasNextLine()) {
            try{
            	Course course = readCourse(fileReader.nextLine());
            	
            	if (course == null) {
            		continue;
            	}
            	
            	boolean duplicate = false;
	            for (int i = 0; i < courses.size(); i++) {
	                Course c = courses.get(i);
	                if (course.getName().equals(c.getName()) &&
	                        course.getSection().equals(c.getSection())) {
	                	//it's a duplicate
	                	duplicate = true;
	                }
	            }
	            if (!duplicate) {
	            	courses.add(course);
	            }
            
            } catch (IllegalArgumentException e) {
            	break;
            }
	    }
	    fileReader.close();
	    return courses;
	}
    
    
	/**
	 * Reads course information from a line and creates a Course object from 
	 * that information
	 * 
	 * @param nextLine from a scanner object that contains course information
	 * @return course the course that will be added to a sorted list of courses
	 * @throws InvalidTransitionException 
	 */
	private static Course readCourse(String nextLine) throws InvalidTransitionException {

		Scanner lineScanner = new Scanner(nextLine);
		lineScanner.useDelimiter(",");
		String name = "";
		String title = "";
		String section = "";
		int credits = -1;
		String instructorId = "";
		int enrollmentCap = -1;
		String meetingDays = "";
		int startTime = 0;
		int endTime = 0;

		if (lineScanner.hasNext()) {
			name = lineScanner.next();
		} else {
			lineScanner.close();
			return null;
			
		}
		
		if (lineScanner.hasNext()) {
			title = lineScanner.next();
		} else {
			lineScanner.close();
			return null;
		}
		
		if (lineScanner.hasNext()) {
			section = lineScanner.next();
		} else {
			lineScanner.close();
			return null;
		}
		
		if (lineScanner.hasNextInt()) {
			credits = lineScanner.nextInt();
		} else {
			lineScanner.close();

			return null;
		}
		
		if (lineScanner.hasNext()) {
			 instructorId = lineScanner.next();
		} else {
			lineScanner.close();
			return null;
		}
		
		if (lineScanner.hasNextInt()) {
			enrollmentCap = lineScanner.nextInt();
		} else {
			lineScanner.close();

			return null;
		}
		
		if (lineScanner.hasNext()) {
			meetingDays = lineScanner.next();
		} else {
			lineScanner.close();
			return null;
		}
		
		if (lineScanner.hasNext()) {
			if (lineScanner.hasNextInt()) {
				startTime = lineScanner.nextInt();
			} else {
				lineScanner.close();
				return null;
			}
			if (lineScanner.hasNextInt()) {
				endTime = lineScanner.nextInt();
			} else {
				lineScanner.close();
				return null;
			}
			Course c = new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays, startTime, endTime);
			lineScanner.close();
			return c;
		} else {
			Course c = new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays);
			lineScanner.close();
			return c;
		}
	}
	/**
     * Writes the given list of Courses to a file
     * The IOException has been caught in the WolfScheduler class
     * 
     * @param fileName name of the file to which the files will be written
     * @param courses courses to be written to the file
	 * @throws FileNotFoundException thrown if the file of filename cannot be found
	 * (this is thrown by wolfScheduler class)
     * 
     */
	public static void writeCourseRecords(String fileName, SortedList<Course> courses) throws FileNotFoundException {
		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < courses.size(); i++) {
		    fileWriter.println(courses.get(i).toString());
		}

		fileWriter.close();
		
	}

}