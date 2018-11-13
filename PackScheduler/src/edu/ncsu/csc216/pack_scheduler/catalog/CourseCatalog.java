package edu.ncsu.csc216.pack_scheduler.catalog;

import java.io.FileNotFoundException;

import java.io.IOException;

import edu.ncsu.csc216.collections.list.SortedList;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;
/**
 * This class creates a catalog of courses
 * 
 * @author William
 *
 */

public class CourseCatalog {
	/** Course catalog. */
	private SortedList<Course> catalog;
	
	/**
	 * creates a new catalog
	 * 
	 */
	public CourseCatalog() {
		
		catalog = new SortedList<Course>();
	}
	
	/**
	 * Creates an new empty catalog directory.
	 */
	public void newCourseCatalog() {
		catalog = new SortedList<Course>();
	}
	/**
	 * loads a course catalog from a file
	 * 
	 * @param fileName
	 * 			the name of the file to be loaded

	 * @throws IllegalArgumentException
	 * 			throws an IAE if the compiler is not able to read the file
	 */
	public void loadCoursesFromFile(String fileName){
		try {
			catalog = CourseRecordIO.readCourseRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}
	}
	/**
	 * adds a course to the catalog if it is not a duplicate
	 * 
	 * @param name
	 * 			the name of the course
	 * @param title
	 * 			the title of the course
	 * @param section
	 * 			the section of the course
	 * @param credits
	 * 			the number of credits of the course
	 * @param instructorId
	 * 			the instructor of the course
	 * @param enrollmentCap
	 * 			the enrollment cap for the course
	 * @param meetingDays
	 * 			the days the course meets
	 * @param startTime
	 * 			the start time of the course
	 * @param endTime
	 * 			the end time of the course
	 * @return boolean
	 * 		if a course can be added or not

	 */
	public boolean addCourseToCatalog(String name, String title, String section, int credits, String instructorId,
			int enrollmentCap, String meetingDays, int startTime, int endTime) {
		//need to create the course that will be added; 

		Course c = new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays,
				startTime, endTime);
		
		boolean inCatalog = false;

		//find out if the course is already in the catalog
		
		for (int i = 0; i < catalog.size(); i++) {
			Course a = catalog.get(i);

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
	/**
	 * returns whether or not a corse can be removed, if it can it is
	 * if it cannot then false is returned
	 * @param name
	 * 			the name of the course the be removed
	 * @param section
	 * 			the section of the course to be removed
	 * @return boolean
	 * 			if the course was removed or not
	 */
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
	
	/**
	 * gets a course from the catalog
	 * @param name
	 * 			the name of the course
	 * @param section
	 * 			the section of the course
	 * @return Course
	 * 			the course to be fetched
	 */
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
	/**
	 * provides a string representation of the course catalog
	 * @return String [][]
	 * 			the String array of the course fields
	 */
	public String [][] getCourseCatalog() {
		//if catalog size is greater than zero
		if (catalog.size() > 0) {
			String [][] courseArray;
			courseArray = new String [catalog.size()][5];
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
					//is this why I'm failing?
					if (j == 4) {
						courseArray[i][j] = Integer.toString(c.getCourseRoll().getOpenSeats());
					}
					
					
				}

			}
        
			return courseArray;
		//if catalog is size zero
		} else {
			//String [][] courseArray;
			//courseArray = new String [0][0];
			return null;
		}
	}
	
	/**
	 * saves a course catalog to a file
	 * @param fileName
	 * 			the file to be saved to
	 * 
	 * @throws IllegalArgumentException
	 * 			throws IAE if the file cannot be saved
	 */
	public void saveCourseCatalog(String fileName) {
		try {
			CourseRecordIO.writeCourseRecords(fileName, catalog);
		} catch (IOException e) {
			throw new IllegalArgumentException ("The File Cannot be saved");

		}
	}
}

