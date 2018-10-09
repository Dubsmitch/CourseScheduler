package edu.ncsu.csc216.pack_scheduler.catalog;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.Activity;
import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.course.ConflictException;

public class CourseCatalog {
	/** Course catalog. */
	private SortedList<Course> catalog;
	
	/**
	 * creates a new catalog
	 * 
	 * @param fileName the name of the file from which to build a catalog
	 */
	public CourseCatalog(String fileName) {
		
		catalog = new SortedList<Course>();
	}
	
	/**
	 * Creates an new empty catalog directory.
	 */
	public void newStudentDirectory() {
		catalog = new SortedList<Course>();
	}
	
	public void loadCoursesFromFile(String fileName) {
		try {
			catalog = new SortedList<Course>(CourseRecordIO.readCourseRecords(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find File");
		}
	}
	
	public boolean addCourseToCatalog(String name, String title, String section, int credits, String instructorId,
			String meetingDays, int startTime, int endTime) {
		//need to create the course that will be added; 

		Course c = new Course(name, title, section, credits, instructorId, meetingDays,
				startTime, endTime);
		
		boolean inCatalog = false;

		//find out if the course is already in the catalog
		
		for (int i = 0; i < catalog.size(); i++) {
			Activity a = catalog.get(i);

			if (c.isDuplicate(a)) {
				inCatalog = true;
			}
			
		}
		
		
		//if already in schedule, throw IAE
		if (inCatalog) {
			return false;
		} else {
			catalog.add(c);
			return true;
		}
	}
	
	public boolean removeCourseFromCatalog(String name, String section) {
		
		for (int i = 0; i < catalog.size(); i++) {
            
			Course c = catalog.get(i);
            if (c.getName().equals(name) &&
                    c.getSection().equals(section)) {
                
                catalog.remove(i);
            	return true;
            }
        }
		return false;
	}
	
	public Course getCourseFromCatalog(String name, String section) {


		for (int i = 0; i < catalog.size(); i++) {
            
			Course c = catalog.get(i);
            if (c.getName().equals(name) &&
                    c.getSection().equals(section)) {
                
                return c;
            }
        }
		return null;
	}
	
	public String [][] getCourseCatalog() {
		//if catalog size is greater than zero
		if (catalog.size() > 0) {
			String [][] courseArray;
			courseArray = new String [catalog.size()][4];
			String courseName = "";
			String courseSection = "";
			String courseTitle = "";
			String courseMeetingString = "";
			//for each course, return a name, section, and title.
		
		
			for (int i = 0; i < catalog.size(); i++) {
				Course c = catalog.get(i);
				courseName = c.getName();
				courseSection = c.getSection();
				courseTitle = c.getTitle();
				courseMeetingString = c.getMeetingString();
				for (int j = 0; j <= 3; j++) {
					if (j == 0) {
						courseArray [i][j] = courseName;  
					}
            	
					if (j == 1) {
						courseArray [i][j] = courseSection; 
					}
            	
					if (j == 2) {
						courseArray [i][j] = courseTitle; 
					}
					if (j == 3) {
						courseArray[i][j] = courseMeetingString;
					}
					
				}

			}
        
			return courseArray;
		//if catalog is size zero
		} else {
			String [][] courseArray;
			courseArray = new String [0][0];
			return courseArray;
		}
	}
	public void saveCourseCatalog(String fileName) {
		try {
			CourseRecordIO.writeCourseRecords(fileName, catalog);
		} catch (IOException e) {
			throw new IllegalArgumentException ("The File Cannot be saved");

		}
	}
}

