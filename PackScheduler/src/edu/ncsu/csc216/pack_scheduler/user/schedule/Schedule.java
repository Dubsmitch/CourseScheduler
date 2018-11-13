package edu.ncsu.csc216.pack_scheduler.user.schedule;



import edu.ncsu.csc216.pack_scheduler.course.ConflictException;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.util.ArrayList;



public class Schedule {

	/** custom array of students **/
	private ArrayList<Course> schedule;
	
	/** title of the schedule **/
	private String title;
	
	public Schedule() {
		schedule = new ArrayList<Course>();
		
		title = "My Schedule";
	}
	/**
	 * adds course to the end of the schedule
	 * 
	 * @param course
	 * 			course to be added
	 * @return boolean
	 * 			if a course can be added or not
	 */
	public boolean addCourseToSchedule (Course course) {
		for (int i = 0; i < schedule.size(); i++) {
			if (schedule.get(i).equals(course)) {
				throw new IllegalArgumentException ("You are alread enrolled in " + course.getName());
			}
			
			try {
				schedule.get(i).checkConflict(course);
			} catch (ConflictException e) {
				return false;
			}
				
		}
		
		
		schedule.add(schedule.size(), course);
		return true;
	}

	public boolean removeCourseFromSchedule(Course course) {
		boolean wasRemoved = false;
		
		if (schedule.size() == 0) {
			return false;
		}
		
		for (int i = 0; i <schedule.size(); i++) {
			if (course.equals(schedule.get(i))) {
				schedule.remove(i);
				wasRemoved = true;
				return true;
			}
		}
		
		return wasRemoved;
	}
	/**
	 * resets the schedule to empty and "My Schedule"
	 */
	public void resetSchedule() {
		schedule = new ArrayList<Course>();
				
		title = "My Schedule";
	}

	/**
	 * get Schedule Courses
	 * @return String [] []
	 * 			an Array of strings that represent different
	 * 			fields in each course in the schedule
	 */
	public String[][] getScheduledCourses(){
		String [][] scheduleCourse = new String[schedule.size()][5];
		for (int i = 0; i < schedule.size(); i++) {
			Course aa = schedule.get(i);
			scheduleCourse[i][0] = aa.getName();
			scheduleCourse[i][1] = aa.getSection();
			scheduleCourse[i][2] = aa.getTitle();
			scheduleCourse[i][3] = aa.getMeetingString();
			scheduleCourse[i][4] = Integer.toString(aa.getCourseRoll().getEnrollmentCap());
		}
		return scheduleCourse;
	}
	
	public void setTitle(String string) {
		title = string;
	}
	
		
	public ArrayList<Course> getSchedule() {
		return schedule;
	}

	public String getTitle() {
		return title;
	}
	
	public int getScheduleCredits() {
		int total = 0;
		System.out.println("Size of array is " + this.getSchedule().size());
		if (this.getSchedule().size() == 0)
			return 0;
		for (int i = 0; i < this.getSchedule().size(); i++) {
			System.out.println("This executed " + (i + 1) + " times");
			total = total + this.getSchedule().get(i).getCredits();
		}
		return total;
	}
	/**
	 * 
	 * @param course
	 * 			the course to be added to the schedule
	 * @return boolean
	 * 			whether or not the course can be added to the schedule
	 */
	public boolean canAdd(Course course) {
		if (course == null) {
			return false;
		}
		
		for (int i = 0; i < this.getSchedule().size(); i++) {
			if (this.getSchedule().get(i).equals(course)) {
				try{
					this.getSchedule().get(i).checkConflict(course);
				} catch (ConflictException e) {
					return false;
				}
				
			}
		}
		
		return true;
		
	}
}
