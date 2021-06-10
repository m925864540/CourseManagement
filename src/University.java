import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
public class University {
	private Scanner scan = new Scanner(System.in);
	private static Map<String, String> userNamePassList= new HashMap<>();
	private static List<Student> studentList= new ArrayList<>();
	private List<Course> courseList= new ArrayList<>();
	private static List<Integer> studentIDList= new ArrayList<>();
	
	private static List<Admin> adminList= new ArrayList<>();
	private static Map<String, String> adminUserPassList= new HashMap<>();
	

	private String userName, passWord;
	private String firstName, lastName;
	private int IDNumber;
	private Student s;
	private Account a;
	private Admin admin;
	private Random rand= new Random();
	
	public boolean register() {
		System.out.println("\t\t\t----------Register----------");
		System.out.println("Enter Your First Name:");
		this.firstName=scan.next();
		System.out.println("Enter Your Last Name:");
		this.lastName=scan.next();
		
		//Ask for username and password inorder to register an account for the student.
		System.out.println("Enter Your Username:");		
		this.userName=scan.next();
		System.out.println("Enter Your Password:");
		this.passWord=scan.next();
		
		if(validateUsernameAndPassword(userName, passWord)!=true) {		//check if user name and password meet the requirement.
			return false;
		}
		if(userNamePassList.containsKey(userName)) {		//check if user name already exist.
			System.out.println("Username already existed.");
			return false;
		}else {
			//University uni= new University();
			//List<Integer> IDList= uni.getStudentIDList();
			while(true) {
				int idTest=generateID();
				if(studentIDList.contains(idTest)) {
					continue;
				}else {
					this.IDNumber=idTest;
					studentIDList.add(IDNumber);
					break;
				}
			}
			this.s=new Student(firstName, lastName, userName, passWord, IDNumber);
			studentList.add(this.s);
			//a= new Student(firstName, lastName, userName, passWord);//create the account for student.
			userNamePassList.put(userName, passWord);	//put user name and password to the list.
			System.out.println("\nAccount: "+userName+" successfully created.\n");
			BufferedWriter bw=null;
			try {
				FileWriter fw= new FileWriter("student.txt",true);
				bw= new BufferedWriter(fw);
				bw.write(this.firstName+"\t"+this.lastName+"\t"+this.userName+"\t"+this.passWord+"\t"+this.IDNumber);
				//fw.write(this.firstName+"\t"+this.lastName+"\t"+this.userName+"\t"+this.passWord);
				bw.newLine();
				bw.close();
			}catch(Exception e) {
				System.out.println("An Error Occured.");
				e.printStackTrace();
			}
		}
		return true;
	}
	public boolean register(int idnumber) {
		System.out.println("\t\t\t----------Register----------");
		System.out.println("Enter Your First Name:");
		this.firstName=scan.next();
		System.out.println("Enter Your Last Name:");
		this.lastName=scan.next();
		
		//Ask for username and password inorder to register an account for the student.
		System.out.println("Enter Your Username:");		
		this.userName=scan.next();
		System.out.println("Enter Your Password:");
		this.passWord=scan.next();
		
		if(validateUsernameAndPassword(userName, passWord)!=true) {		//check if user name and password meet the requirement.
			return false;
		}
		if(userNamePassList.containsKey(userName)) {		//check if user name already exist.
			System.out.println("Username already existed.");
			return false;
		}else {
			this.s=new Student(firstName, lastName, userName, passWord, IDNumber);
			studentList.add(this.s);
			//a= new Student(firstName, lastName, userName, passWord);//create the account for student.
			userNamePassList.put(userName, passWord);	//put user name and password to the list.
			System.out.println("\nAccount: "+userName+" successfully created.\n");
			BufferedWriter bw=null;
			try {
				FileWriter fw= new FileWriter("student.txt",true);
				bw= new BufferedWriter(fw);
				bw.write(this.firstName+"\t"+this.lastName+"\t"+this.userName+"\t"+this.passWord+"\t"+this.IDNumber);
				//fw.write(this.firstName+"\t"+this.lastName+"\t"+this.userName+"\t"+this.passWord);
				bw.newLine();
				bw.close();
			}catch(Exception e) {
				System.out.println("An Error Occured.");
				e.printStackTrace();
			}
		}
		return true;
	}
	public int generateID() {
		return rand.nextInt(90000)+10000;
	}

	public boolean login(String username, String password) {
		
		if(userNamePassList.containsKey(username)==false) {	//if user name doesn't match to anything, the account doesn't exist..
			System.out.println("No username existed.\n");
			return false;
		}else if(userNamePassList.containsKey(username) && !userNamePassList.get(username).equals(password)) {	//user name existed but password is incorrect.
			System.out.println("Incorrect password.\n");
			return false;
		}else {			//user name exist and it matches with the corresponding value(password) of the user name.
			System.out.println("\nLogin successful.");
		}
		return true;
	}
	
	public Student currentStudent(String username, String password) {
		for(Student obj: studentList) {
			if(obj.getUserName().equals(username)) {
				this.s=obj;
			}
		}
		return this.s;
	}
	
	public void logout() {
		this.s=null;
		System.out.println("Logout successful.");
	}
	
	public void printuserNamePassList() {
		System.out.println("Username and Password List: ");
		for(Map.Entry<String,String> e: userNamePassList.entrySet()) {
			System.out.println("Username: "+e.getKey().toString()+"\tPassword: "+e.getValue().toString());
		}
	}
	public Map getUserNamePassList() {
		return userNamePassList;
	}
	
	public List getStudentIDList() {
		return studentIDList;
	}
	

	public List getStudentList(){
		return studentList;
	}

	public boolean adminInternalRegister(String firstname,String lastname, String username, String password) {
		this.firstName=firstname;
		this.lastName=lastname;
		this.userName=username;
		this.passWord=password;
		
		if(validateUsernameAndPassword(userName, passWord)!=true) {		//check if user name and password meet the requirement.
			return false;
		}
		if(adminUserPassList.containsKey(userName)) {		//check if user name already exist.
			System.out.println("Username already existed.");
			return false;
		}else {
			this.admin=new Admin(firstName,lastName, userName, passWord);
			adminList.add(this.admin);
			adminUserPassList.put(userName, passWord);	//put user name and password to the list.
			//System.out.println("\nAdmin Account: "+userName+" successfully created.");
		}
		return true;
	}
	
	public boolean AdminLogin(String username, String password){
		if(adminUserPassList.containsKey(username)==false) {	//if user name doesn't match to anything, the account doesn't exist..
			System.out.println("No username existed.\n");
			return false;
		}else if(adminUserPassList.containsKey(username) && !adminUserPassList.get(username).equals(password)) {	//user name existed but password is incorrect.
			System.out.println("Incorrect password.\n");
			return false;
		}else {			//user name exist and it matches with the corresponding value(password) of the user name.
			System.out.println("\nLogin successful.");
		}
		return true;
	}
	
	public Admin currentAdmin(String username, String password) {
		for(Admin adm: adminList) {
			if(adm.getUsername().equals(username)) {
				this.admin=adm;
			}
		}
		return this.admin;
	}

	public void adminLogout() {
		this.admin=null;
		System.out.println("Logout Successful.\n");
	}
	
	public Map getAdminUserPassList() {
		return adminUserPassList;
	}
	public List getAdminList() {
		return adminList;
	}
	public boolean validateUsernameAndPassword(String username, String password) {
		if(username.equals(null)) {
			System.out.println("Username cannot be null.");
			return false;
		}
		if(password.equals(null)) {
			System.out.println("Password cannot be null.");
			return false;
		}
		if(username.length()<6) {
			System.out.println("Username needs to be at least length 6.");
			return false;
		}

		if(password.length()<6) {
			System.out.println("Password needs to be at least length 6.");
			return false;
		}
		return true;
	}
	
	
}
