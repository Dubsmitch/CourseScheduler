package edu.ncsu.csc216.pack_scheduler.catalog;

import java.io.FileNotFoundException;
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
	
	
}

