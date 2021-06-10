import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;

public class Student{

	private Map<String, Integer> schedule= new HashMap<>();	//store individual student schedule courses.
	public List<String> schedule2= new ArrayList<>();
	private Student student;
	private String firstName, lastName, userName, passWord;
	private int IDNumber;
	
	public Student() {
		
	}

	public Student(String firstname, String lastname,String username, String password, int IDnumber) {
		this.firstName=firstname;
		this.lastName=lastname;
		this.userName=username;
		this.passWord=password;
		this.IDNumber=IDnumber;
	}
	
	public void setName(String firstname, String lastName) {
		this.firstName=firstname;
		this.lastName=lastName;
	}
	
	public String getUserName() {
		return userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public String getName() {
		return this.firstName +" "+this.lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public int getIDNumber() {
		return IDNumber;
	}
		
	public void getCourseList() {
		System.out.println("Course List:");
		Course c= new Course();
		c.courseList();
	}
	
	public void addCourse() {
		Scanner scan = new Scanner(System.in);
		String courseName;
		Course c= new Course();
		getCourseList();
		while(true) {
			System.out.println("\nEnter Course Name(0 To Exit): ");
			courseName=scan.next();
			if(courseName.equals("0")) {
				break;
			}
			if(c.getCourse(courseName)==-1) {
				System.out.println("No course found.");
			}else {
			schedule.put(courseName, c.getCourse(courseName));
			schedule2.add(courseName+" "+c.getCourse(courseName));	//schedule2
			System.out.println("Course: "+ courseName+" "+c.getCourse(courseName)+" added to your schedule.");
			
			//below are modify code. might delete.
			BufferedWriter bw=null;
			try {
				FileWriter fw= new FileWriter("studentSchedule.txt",true);
				bw= new BufferedWriter(fw);
				bw.write(this.firstName+"\t"+this.lastName+"\t"+this.IDNumber+"\t"+schedule.keySet());
				bw.newLine();
				bw.close();
			}catch(Exception e) {
				System.out.println("An Error Occured.");
				e.printStackTrace();
			}
			}
		}
		System.out.println("\nReturn to Student Page.");
	}
	
	public void removeCourse(String coursename) {
		if(schedule.size()<1) {
			System.out.println("Course schedule list is empty.");
			return;
		}
		if(schedule.containsKey(coursename)) {
			int removedCourse= schedule.get(coursename);
			schedule.remove(coursename);
			schedule2.remove(coursename);	//schedule2
			System.out.println("Course: "+ coursename+" "+removedCourse+" removed.\n");
		}else {
			System.out.println("Course schedule list does not contain course: "+coursename);
		}
	}
	public void getFee() {
		Course c= new Course();
		Map<String, Double> fee = c.getFeeList();
		Double value=0.00;
		System.out.println("\t\t\t----------My Course Fee----------");
		for(Map.Entry<String,Integer> e: schedule.entrySet()) {
				System.out.println("Course Name: "+ e.getKey().toString()+"\t Course Fee: "+ fee.get(e.getKey()));
				value+=fee.get(e.getKey());
		}
		System.out.println("Total Fee is : "+value);
	}
	public void myCourseSchedule() {
		System.out.println("\t\t\t----------My Schedule----------");
		for(Map.Entry<String,Integer> e: schedule.entrySet()) {
			System.out.println("Course Name: "+e.getKey().toString()+"\tCourse Number: "+e.getValue().toString());
		}
		
	}
	
	public void getCourseSchedule() {
		for(Map.Entry<String,Integer> e: schedule.entrySet()) {
			System.out.println("Course Name: "+e.getKey().toString()+"\tCourse Number: "+e.getValue().toString());
		}
	}
	
	public void getCourseFee() {
		Course c= new Course();
		Map<String, Double> fee = c.getFeeList();
		Double value=0.0;
		for(Map.Entry<String,Integer> e: schedule.entrySet()) {
			System.out.println("Course Name: "+ e.getKey().toString()+"\t Course Fee: "+ fee.get(e.getKey()));
			value+=fee.get(e.getKey());
		}
		System.out.println("Total Fee is : "+value);
	}
	public Map getSchedule() {
		return schedule;
	}

	public String toString() {
		return this.firstName+"        "+this.lastName+"        "+this.IDNumber;
	}
}
