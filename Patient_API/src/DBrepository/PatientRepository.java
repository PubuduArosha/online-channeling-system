package DBrepository;

import java.util.ArrayList;
import java.util.List;

import java.sql.*;

import model.Patient;

/**
 * @author Malidi
 *
 */
public class PatientRepository{
	Connection con=null;
	public PatientRepository()
	{
		String url="jdbc:mysql://localhost:3306/pafdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String username="root";
		String password="";
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,username,password);
			System.out.println("successfully connected to DB");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Unanel to make connection");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to make connection");
			e.printStackTrace();
		}
		
	}
	
	public String getAllPatients()
	{ String output="";
	output = "<table border=\"1\"><tr><th>Patient ID</th><th>NIC</th><th>First Name</th><th>Last Name</th>"
			+ "<th>Email</th><th>Gender</th><th>Address</th><th>Password</th><th>City</th><th>Contact</th></tr>";
		List<Patient> patient=new ArrayList<>(); 
		String sql="select * from patient";
		
		try {
			java.sql.Statement st =con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			
			while(rs.next())
			{
				String patientID=Integer.toString(rs.getInt("patientID"));
				String NIC=rs.getString("NIC");
				String firstName=rs.getString("firstName");
				String lastName=rs.getString("lastName");
				String email=rs.getString("email");
				String gender=rs.getString("gender");
				String address=rs.getString("address");
				String password=rs.getString("password");
				String city=rs.getString("city");
				String contact=rs.getString("contact");
				
				output += "<tr><td>" + patientID + "</td>";
				output += "<td>" + NIC + "</td>";
				output += "<td>" + firstName + "</td>";
				output += "<td>" + lastName + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + gender + "</td>";
				output += "<td>" + address + "</td>";
				output += "<td>" + password + "</td>";
				output += "<td>" + city + "</td>";
				output += "<td>" + contact + "</td>";
//				Patient p=new Patient();
//				
//				p.setPatientID(rs.getInt(1));
//				p.setNIC(rs.getString(2));
//				p.setFirstName(rs.getString(3));
//				p.setLastName(rs.getString(4));
//				p.setEmail(rs.getString(5));
//				p.setGender(rs.getString(6));
//				p.setAddress(rs.getString(7));
//				p.setPassword(rs.getString(8));
//				p.setCity(rs.getString(9));
//				p.setContact(rs.getString(10));
//				
//				patient.add(p);
			}
			con.close();
			output += "</table>";
			return output;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			output="invalid";
		}
		
		
		return output;
	}
	
	
	
	public String getPatient(String patID) {
		String output="";
		output = "<table border=\"1\"><tr><th>Patient ID</th><th>NIC</th><th>First Name</th><th>Last Name</th>"
				+ "<th>Email</th><th>Gender</th><th>Address</th><th>Password</th><th>City</th><th>Contact</th></tr>";
		
		int val=Integer.parseInt(patID);
		String sql = "Select * from patient where `patientID`="+val;
		
		
		
		
		try {
			PreparedStatement preparedStmt = con.prepareStatement(sql);
			System.out.println(preparedStmt);
			//int val=Integer.parseInt(patID);
			//preparedStmt.setInt(1,val); 
			System.out.println(val);
			ResultSet rs = preparedStmt.executeQuery(sql);
			if(rs.next())
			{
				
				String patientID=Integer.toString(rs.getInt("patientID"));
				String NIC=rs.getString("NIC");
				String firstName=rs.getString("firstName");
				String lastName=rs.getString("lastName");
				String email=rs.getString("email");
				String gender=rs.getString("gender");
				String address=rs.getString("address");
				String password=rs.getString("password");
				String city=rs.getString("city");
				String contact=rs.getString("contact");
				
				output += "<tr><td>" + patientID + "</td>";
				output += "<td>" + NIC + "</td>";
				output += "<td>" + firstName + "</td>";
				output += "<td>" + lastName + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + gender + "</td>";
				output += "<td>" + address + "</td>";
				output += "<td>" + password + "</td>";
				output += "<td>" + city + "</td>";
				output += "<td>" + contact + "</td>";
				
			}
			con.close();
			output += "</table>";
			
			return output;
		} catch (SQLException e) {
			output = "Error while Get patient data.";
			e.printStackTrace();
		}
		return output;
	}
	
	
	
	
	public Patient getPatientByLogins(String username,String password)
	{
		System.out.println("Started to execute");

		String sql=new String("SELECT * FROM patient WHERE userName=? And password=?");
		
		
		System.out.println(sql);
		Patient p=new Patient();
		try {
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, username);
			stm.setString(2, password);
			
			ResultSet rs=stm.executeQuery();
			System.out.println(rs);
			if(rs.next())
			{
				
				p.setPatientID(rs.getInt(1));
				p.setNIC(rs.getString(2));
				p.setFirstName(rs.getString(3));
				p.setLastName(rs.getString(4));
				p.setEmail(rs.getString(5));
				p.setGender(rs.getString(6));
				p.setAddress(rs.getString(7));
				p.setPassword(rs.getString(8));
				p.setCity(rs.getString(9));
				p.setContact(rs.getString(10));
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return p;
	}
	
	
	/**
	 * @param p1
	 */
	public String createPatient(Patient p1)
	{
		int count=0;
		String sql="insert into patient (NIC, firstName, lastName, email , gender , address, password, city, contact) values (?,?,?,?,?,?,?,?,?)";
		
		String sql2=new String("select * from patient where email=?");
		
		try {
			PreparedStatement stm = con.prepareStatement(sql2);
			stm.setString(1,p1.getEmail());
			
			ResultSet rs=stm.executeQuery();
			if(rs.next())
			{
				return "Sorry there is already a registered user with this email!!";
				
			}else {

			
			
			
			PreparedStatement st =con.prepareStatement(sql);
			System.out.println(st);
		
				
			
			st.setString(1,p1.getNIC());
			st.setString(2,p1.getFirstName());
			st.setString(3,p1.getLastName());
			st.setString(4,p1.getEmail());
			st.setString(5,p1.getGender());
			st.setString(6,p1.getAddress());
			st.setString(7,p1.getPassword());
			st.setString(8,p1.getCity());
			st.setString(9,p1.getContact());
			
			count=st.executeUpdate();
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("connection value"+e);
		}
		
		
		if(count>0)
		{
			return "congratulations register success!!";
		}else {
			
			return "register failure";
		}
		
	}

	
	
	public String createPatientAsForm(String NIC, String firstName, String lastName, String email , String gender , String address, String password, String city, String contact)
	{
		int count=0;
		String sql="insert into patient (NIC, firstName, lastName, email , gender , address, password, city, contact) values (?,?,?,?,?,?,?,?,?)";
		System.out.println(sql);
		String sql2=new String("select * from patient where email=?");
		
		try {
			PreparedStatement stm = con.prepareStatement(sql2);
			stm.setString(1,email);
			
			ResultSet rs=stm.executeQuery();
			if(rs.next())
			{
				return "Sorry there is already a registered user with this email!!";
			}else {

			
			
			
			PreparedStatement st =con.prepareStatement(sql);
			System.out.println(st);
		
				
			
			st.setString(1,NIC);
			st.setString(2,firstName);
			st.setString(3,lastName);
			st.setString(4,email);
			st.setString(5,gender);
			st.setString(6,address);
			st.setString(7,password);
			st.setString(8,city);
			st.setString(9,contact);
			
			count=st.executeUpdate();
			con.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("connection value"+e);
		}
		
		
		if(count>0)
		{
			return "congratulations register success!!";
		}else {
			
			return "register failure";
		}
		
	}


	/**
	 * @param p1
	 * @return
	 */
	public String UpdatePatient(int patientID,String NIC, String firstName, String lastName, String email , String gender , String address, String password, String city, String contact)
	{
		int count=0;
		String sql = "update patient set NIC = ? , firstName = ? , lastName = ?,email = ? , gender = ? , address = ?,password = ? , city = ? , contact = ?  where patientID = ?";
		
		try {
			PreparedStatement st = con.prepareStatement(sql);

			
			st.setString(1,NIC);
			st.setString(2,firstName);
			st.setString(3,lastName);
			st.setString(4,email);
			st.setString(5,gender);
			st.setString(6,address);
			st.setString(7,password);
			st.setString(8,city);
			st.setString(9,contact);
			st.setInt(10,patientID);
			count=st.executeUpdate();
			
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		
		if(count>0)
		{
			return "User successfully updated";
		}else {
			
			return "Update unsuccessful";
		}
	}
	
	
	
	public String DeletePatient(int id) {
		
		int count=0;
		String sql = "delete from patient where patientID=?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			
				stmt.setInt(1,id);				
				count=stmt.executeUpdate();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(count>0)
		{
			return "Patient with id "+id+" deleted";
		}else {
			
			return "delete unsuccesful";
		}
		

	}
	
	
	
}

