import java.util.*;
import java.util.Map.Entry;

public class Course {

	public static Map<String, Integer> course = new HashMap<>();
	public static Map<String, Double> courseFee= new HashMap<>();

	
	public void predefineCourse(Course c) {
		c.addCourseAndFee("CHEM1101", 2101, 1200.00);
		c.addCourseAndFee("Calculus2", 2102, 1200.00);
		c.addCourseAndFee("TCOM", 1201, 900.00);
		c.addCourseAndFee("Probability", 4200, 1200.00);
		c.addCourseAndFee("CS1101", 1100, 1500.00);
		c.addCourseAndFee("CS1102", 1101, 1500.00);
	}

	public void addCourseAndFee(String coursename, int coursenumber, double coursefee) {
		if(course.size()<1) {
			course.put(coursename, coursenumber);
			courseFee.put(coursename, coursefee);
			return;
		}

		if(course.containsKey(coursename)) {
			System.out.println("Duplicate course name.");
			return;
		}else if(course.containsKey(coursename)==false && course.containsValue(coursenumber)){
			System.out.println("Duplicate course number.");
		}else {
			course.put(coursename, coursenumber);
			courseFee.put(coursename, coursefee);
		}
	}
	public void deleteCourse(String coursename) {
		if(course.containsKey(coursename)) {
			int removedCourse= course.get(coursename);
			course.remove(coursename);
			System.out.println("Course : "+coursename+" "+removedCourse+" removed.");
		}else {
			System.out.println("Can not find course.");
		}
	}
	public int getCourse(String coursename) {
		if(course.containsKey(coursename)) {
			return course.get(coursename);
		}
		return -1;
	}
	public Map<String, Double> getFeeList(){	//return the courseFee hash map
		return courseFee;
	}
	public void courseList() {		//return all  available courses with fees.
		for(Map.Entry<String,Integer> e: course.entrySet()) {
			System.out.println("Course name: "+e.getKey().toString()+"\t\tCourse Number: "+e.getValue().toString()+"\tFee: "+ courseFee.get(e.getKey().toString()));
		}
	}
}
