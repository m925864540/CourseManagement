import java.util.*;

public class Main {
	public static Scanner scan = new Scanner(System.in);
	public static String username, password;
	public static Student student;
	public static University university = new University();
	public static Admin admin= new Admin();
	public static void main(String[] args) {

		predefineCourse();
		university.adminInternalRegister("Mike","Lin", "admin1", "123456");
		university.adminInternalRegister("Jason","Ni", "admin2", "123456");
		
		
		boolean control=true;
		while(control) {
			mainPage();
			int userOption=option();

			switch(userOption) {
			case 1:
				option1();
				//system.printUserPassList();
				break;
			case 2:
				option2();
				break;
			case 3:
				option3();
				break;
			case 4:
				System.out.println("Closing...");
				control=false;
				break;
			default:
				System.out.println("Invalid Input");
			}
		}
		System.out.println("System Exited. Thank You.");
		
	}
	public static void predefineCourse() {
		Course c= new Course();			//add the predefine courses to course hash map.
		c.predefineCourse(c);
	}
	public static void mainPage() {
		System.out.println("*---------------------COURSE MANAGEMENT SYSTEM---------------------*");
		System.out.println("*-------------------------------------------------------------------*");
		System.out.println("*-------------------------------------------------------------------*");
		System.out.println("*-------------------------------------------------------------------*\n");
		System.out.println("\t\t\t----------Main Page----------");
		System.out.println("Option 1: Student LogIn\nOption 2: Admin LogIn\nOption 3: Register\nOption 4: Exit");
	}
	public static int option() {
		int userOption;
		System.out.println("\nEnter Your Option Number:\n");
		userOption=scan.nextInt();
		return userOption;
	}
	public static void option1() {
		System.out.println("\t\t\t----------Student LogIn----------");
		System.out.println("Username:");
		username=scan.next();
		System.out.println("Password:");
		password=scan.next(); 
		if(log(username, password)) {
			student=university.currentStudent(username, password);
			studentPageOption();
		}
		
	}
	public static boolean log(String username, String password) {
		return university.login(username, password);
	}
	public static void studentPageOption() {
		boolean re=true;
		while(re) {
			System.out.println("\n\t\t\t----------Student Page----------");
			System.out.println("Option 1: Add Course\nOption 2: Remove Course\nOption 3: My Course Schedule\nOption 4: Get Fee\nOption 5: LogOut");
			int userOption=option();
			switch(userOption) {
			case 1:
				student.addCourse();
				break;		//restart while loop.
			case 2:
				student.removeCourse(userRemoveCourse());
				break;
			case 3:
				student.myCourseSchedule();
				break;
			case 4:
				student.getFee();
				break;
			case 5:
				university.logout();		//log out the users.
				re=false;
				break;
			default:
				System.out.println("Incorrect Selection...");
			}
		}
	}

	public static void option2() {
		System.out.println("\t\t\t----------Admin LogIn----------");
		System.out.println("Username:");
		username=scan.next();
		System.out.println("Password:");
		password=scan.next(); 
		//university.AdminLogin(username, password);
		//admin=university.currentAdmin(username, password);
		if(adminLog(username,password)) {
			admin=university.currentAdmin(username, password);
			adminPageOption();
		}
	}

	public static boolean adminLog(String username, String password) {
		return university.AdminLogin(username, password);
	}
	
	public static void adminPageOption() {
		boolean re=true;
		while(re) {
			System.out.println("\n\t\t\t----------Admin Page----------\n");
			System.out.println("Option 1: Print Student List\nOption 2: View Student Information\nOption 3: LogOut");
			int userOption=option();
			switch(userOption) {
			case 1:
				admin.printStudentList();
				break;		//restart while loop.
			case 2:
				admin.viewStudentInformation();
				break;
			case 3:
				university.logout();		//log out the admin.
				re=false;
				break;
			default:
				System.out.println("Incorrect Selection...");
			}
		}
	}
	public static void option3() {
		university.register();
	}
	private static String userAddCourse() {
		String addCourse;
		System.out.println("Enter Course You Want To Add:");
		addCourse=scan.next();
		return addCourse;
	}
	private static String userRemoveCourse() {
		String removeCourse;
		System.out.println("Enter Course You Want To Remove:");
		removeCourse=scan.next();
		return removeCourse;
	}

}

		
