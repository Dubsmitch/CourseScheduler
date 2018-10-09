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
}

