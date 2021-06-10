import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.text.html.HTMLDocument.Iterator;

public class Account {
	private String userName, passWord;
	private String firstName, lastName;
	private LocalDate DOB;
	private int IDNumber;
	private boolean loginStatus;
	private Student student;

	Account(){
		
	}
	public Account(String firstname, String lastname) {
		this.firstName=firstname;
		this.lastName=lastname;
	}

	public void setName(String firstname, String lastName) {
		this.firstName=firstname;
		this.lastName=lastName;
	}
	public void setIDNumber(int IDnumber) {
		this.IDNumber=IDnumber;
	}
	
	public String getName() {
		return this.firstName +" "+this.lastName;
	}
	public int getIDNumber() {
		return this.IDNumber;
	}
	public boolean login(String username, String password) {
		University uni= new University();
		Map<String, String> userNamePassList= uni.getUserNamePassList();
		if(userNamePassList.containsKey(username)==false) {	//if user name doesn't match to anything, the account doesn't exist..
			System.out.println("No username existed.");
			return false;
		}else if(userNamePassList.containsKey(username) && !userNamePassList.get(username).equals(password)) {	//user name existed but password is incorrect.
			System.out.println("Incorrect password.");
			return false;
		}else {			//user name exist and it matches with the corresponding value(password) of the user name.
			System.out.println("Login successful.");
		}
		return true;
	}
		
	public void logout() {
		System.out.println("Logout successful.");
	}

}
