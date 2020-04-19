package model;

import java.sql.*;
import java.sql.Connection;

public class Doctor {
	
	private int doctorID;
	private String NIC;
	private String gender;
	private String firstName;
	private String lastName;
	private String email;
	private String specification;
	private String contact;
	private String workDate;
	private String workTime;
	private String password;
	private int adminID;
	private String doctorStatus;
	private boolean valid;
	
	public int getDoctorID() {
		return doctorID;
	}

	public void setDoctorID(int doctorID) {
		this.doctorID = doctorID;
	}

	public String getNIC() {
		return NIC;
	}

	public void setNIC(String nIC) {
		NIC = nIC;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}

	public String getWorkTime() {
		return workTime;
	}

	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAdminID() {
		return adminID;
	}

	public void setAdminID(int adminID) {
		this.adminID = adminID;
	}

	public String getDoctorStatus() {
		return doctorStatus;
	}

	public void setDoctorStatus(String doctorStatus) {
		this.doctorStatus = doctorStatus;
	}
	
	

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	//A common connection to connect to the DB
	private Connection connect()
	{
		Connection con = null;
		
		try
		{
			  Class.forName("com.mysql.jdbc.Driver"); 
			  
			  //Provide the correct details: DBServer/DBName, username, password    
			  con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pafdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					  "root", ""); 
		}
		catch (Exception e)   
		{
			e.printStackTrace();
		} 
		 
		  return con;  
	}
	
	public String readDoctors()
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if(con == null)
			{
				return "Error while connecting to the database for reading";
			}
				
				// Prepare the html table to be displayed
				output =  "<table border=\"1\"><tr><th>NIC</th><th>gender</th><th>firstName</th><th>lastname</th><th>email</th><th>specification</th><th>contact</th><th>workDate</th><th>workTime</th><th>password</th><th>adminID</th><th>doctorStatus</th><th>Valid</th></tr>";
				
				String query = "select * from doctor";
				Statement stmt = con.createStatement(); 
				ResultSet rs = stmt.executeQuery(query); 
				
				// iterate through the rows in the result set 
				while(rs.next())
				{
					String doctorID = Integer.toString(rs.getInt("doctorID"));
					String NIC = rs.getString("NIC");
					String gender = rs.getString("gender");
					String firstName = rs.getString("firstName");
					String lastName = rs.getString("lastName");
					String email = rs.getString("email");
					String specification = rs.getString("specification");
					String contact = rs.getString("contact");
					String workDate = rs.getString("workDate");
					String workTime = rs.getString("workTime");
					String password = rs.getString("password");
					String adminID = Integer.toString(rs.getInt("adminID"));
					String doctorStatus = rs.getString("doctorStatus");
					String valid = Boolean.toString(rs.getBoolean("valid"));
					
					// Add into the html table     
					output += "<tr><td>" + NIC + "</td>"; 
					output += "<td>" + gender + "</td>"; 
					output += "<td>" + firstName + "</td>";  
					output += "<td>" + lastName + "</td>"; 
					output += "<td>" + email + "</td>";
					output += "<td>" + specification + "</td>";
					output += "<td>" + contact + "</td>";
					output += "<td>" + workDate + "</td>";
					output += "<td>" + workTime + "</td>";
					output += "<td>" + password + "</td>";
					output += "<td>" + adminID + "</td>";
					output += "<td>" + doctorStatus + "</td>";	
					output += "<td>" + valid + "</td></tr>";	
				}
				con.close();
				
				//complete the table
				output += "</table>";
			}
			catch (Exception e)
			{
				output = "Error while reading the doctors.";    
				System.err.println(e.getMessage()); 
			}
			
			return output;
		}
	
	public String insertDoctor(String NIC,String gender, String firstName, String lastName,String email,String specification, String contact, String workDate,String workTime,String password, String adminID,String doctorStatus, String valid)
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if(con == null)
			{
				return "Error while connecting to the database for inserting";
			}
			
			// create a prepared statement
			String query = "insert into doctor (`doctorID`, `NIC`, `gender`,  `firstName`, `lastname`, `email`,`specification`,  `contact`, `workDate`,`workTime`,`password`, `adminID`, `doctorStatus`, `valid`)" + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			
			//binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2,NIC );
			preparedStmt.setString(3, gender);
			preparedStmt.setString(4, firstName);
			preparedStmt.setString(5, lastName);
			preparedStmt.setString(6, email);
			preparedStmt.setString(7, specification);
			preparedStmt.setString(8, contact);
			preparedStmt.setString(9, workDate);
			preparedStmt.setString(10, workTime);
			preparedStmt.setString(11, password);
			preparedStmt.setInt(12, Integer.parseInt(adminID));
			preparedStmt.setString(13, doctorStatus );
			preparedStmt.setBoolean(14, Boolean.parseBoolean(valid));
			
			//excute the statement
			preparedStmt.execute();
			con.close();
			
			output = "Inserted Successfully";
		}
		catch (Exception e)
		{
			output = "Error while insering the doctor.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	public String updateDoctor(String doctorID,String NIC,String gender, String firstName, String lastName,String email,String specification, String contact, String workDate,String workTime,String password, String adminID,String doctorStatus, String valid)
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if(con == null)
			{
				return "Error while connecting to the database for updating";
			}
			
			//create a prepared statement
			String query = "UPDATE doctor SET NIC=?, gender=?,firstName=?, lastName=?, email=?,specification=?,  contact=?, workDate=?,workTime=?,password=?, adminID=?, doctorStatus=?, valid=? WHERE doctorID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			
			//binding values
			preparedStmt.setString(1, NIC);
			preparedStmt.setString(2, gender);
			preparedStmt.setString(3, firstName);
			preparedStmt.setString(4, lastName);
			preparedStmt.setString(5, email);
			preparedStmt.setString(6, specification);
			preparedStmt.setString(7, contact);
			preparedStmt.setString(8, workDate);
			preparedStmt.setString(9, workTime);
			preparedStmt.setString(10, password);
			preparedStmt.setInt(11, Integer.parseInt(adminID));
			preparedStmt.setString(12, doctorStatus);
			preparedStmt.setInt(14, Integer.parseInt(doctorID));
			preparedStmt.setBoolean(13, Boolean.getBoolean(valid));
			
			//execute the statement
			preparedStmt.execute();
			con.close();
			
			output = "Updated successfully";
		}
		catch (Exception e)
		{
			output = "Error while updating doctor";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	//deleting doctor
	public String deleteDoctor(String doctorID)
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if(con == null)
			{
				return "Error while connecting to the database for deleting";
			}
			
			//create prepared statement
			String query = "delete from doctor where doctorID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			//binding values
			preparedStmt.setInt(1, Integer.parseInt(doctorID));
			
			//executing the statement
			preparedStmt.execute();
			con.close();
			
			output = "Deleted successfully";
		}
		catch (Exception e)
		{
			output = "Error while deleting the doctor";
			System.err.println(e.getMessage());
		}
		
		return output;
	}

}
