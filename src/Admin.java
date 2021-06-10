import java.util.*;
import java.text.*;
public class Admin {

	private Admin admin;
	private String firstName, lastName, userName, passWord;
	private Student student;
	private Scanner scan = new Scanner(System.in);
	public Admin() {
		
	}
	
	public Admin(String firstname, String lastname, String username, String password) {
		this.firstName=firstname;
		this.lastName=lastname;
		this.userName=username;
		this.passWord=password;
		
	}
	public String getUsername() {
		return userName;
	}
	
	public String getPassword() {
		return passWord;
	}
	
	public void printStudentList() {
		University uni = new University();
		List<Student> studentList=uni.getStudentList();
		
		
		if(studentList.size()<1) {
			System.out.println("No Student In List.");
			return;
		}
		System.out.println("\t\t\t----------Student List----------\n");
		System.out.println("-----------------------------------------------------------------------------");
		System.out.printf("%15s %15s %15s", "Student ID", "First Name", "Last Name");
	    System.out.println();
	    System.out.println("-----------------------------------------------------------------------------");
		for(Student obj: studentList) {
			System.out.format("%15s %15s %15s", obj.getIDNumber(), obj.getFirstName(),obj.getLastName());
			System.out.println();
			//System.out.println(i++ +". "+obj.toString());
		}
		System.out.println("-----------------------------------------------------------------------------");
	}
	
	public void viewStudentInformation() {
		int studentID;
		University uni= new University();
		//Student student= new Student();
		List<Student> studentList= uni.getStudentList();
		//System.out.println("StudengList size: "+studentList.size());
		System.out.println("Enter Student ID Number: ");
		studentID=scan.nextInt();
		
		int i=0;
		for(Student s: studentList) {
			//if(studentList.contains(studentID)) {
			if(s.getIDNumber()==studentID) {
				this.student=s;
				System.out.println("Student: "+s.getName()+ " Found...");
				boolean re=true;
				while(re) {
					System.out.println("\t\t\t----------Student Information Page----------");
					int adminOption;
					System.out.println("\nOption 1: View Student Personal Information\nOption 2: View Student Schedule/Fee\nOption 3: Back To Admin Page");
					System.out.println("\nEnter Your Option:");
					adminOption=scan.nextInt();
					
					switch(adminOption) {
					case 1:
						System.out.println("\nStudent Info----------\n");
						System.out.println("Name: \t"+student.getName());
						System.out.println("ID Number: \t"+student.getIDNumber()+"\n");
						System.out.println("Username: \t"+student.getUserName());
						System.out.println("Password: \t"+student.getPassWord());
						break;
					case 2:
						viewStudentSchedule();
						viewStudentFee();
						break;
					case 3:
						this.student=null;
						re=false;
						System.out.println("Exiting to Admin Page...");
						break;
					default:
						System.out.println("Incorrect Selection...");
					}
				}
			}
			i++;
			if(i==studentList.size()) {
				System.out.println("Cannot Find Student.");
			}
		}//end of enhanced for loop.
	}

	public void viewStudentFee() {
		System.out.println("\nStudent Fee----------\n");
		student.getCourseFee();
		System.out.println();
	}
	
	public void viewStudentSchedule() {
		System.out.println("\nStudent Schedule----------\n");
		student.getCourseSchedule();
		System.out.println();
	}
	public String toString() {
		return this.firstName+"\t\t"+this.lastName;
	}
}


