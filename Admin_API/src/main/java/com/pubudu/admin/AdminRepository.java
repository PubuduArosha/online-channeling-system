package com.pubudu.admin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminRepository {
	
	Connection con = null; 
	
	public AdminRepository(){
		
		String db = "jdbc:mysql://localhost:3306/pafdb?serverTimezone=UTC";
		String username ="root";
		String password ="";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(db, username, password);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

/*--------------------------------------------------------------------hospital------------------------------------------*/
	public List<Admin> getHospitals(){
		List<Admin> hospitals = new ArrayList<>();
		String sql = "select * from hospital";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				Admin h = new Admin();
				
				h.setHospitalID(rs.getInt(1));
				h.setHospitalName(rs.getString(2));
				h.setLocation(rs.getString(3));
				
				hospitals.add(h);
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		
		return hospitals;
	}
	
	public Admin getHospital(int hospitalID) {
		String sql = "select * from hospital where hospitalID="+hospitalID;
		Admin h = new Admin();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()) {
				
				h.setHospitalID(rs.getInt(1));
				h.setHospitalName(rs.getString(2));
				h.setLocation(rs.getString(3));
				
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		return h;
	}
	
	public void add(Admin h1) {
		String sql = "insert into hospital (hospitalID, hospitalName, location, adminID) values (?,?,?,?)";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setInt(1,  h1.getHospitalID());
			st.setString(2,  h1.getHospitalName());
			st.setString(3, h1.getLocation());
			st.setInt(4, h1.getAdminID());
			st.executeUpdate();
		} 
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	public void update(Admin h1) {
		String sql = "update hospital set hospitalName=?, location=? where hospitalID=?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1,  h1.getHospitalName());
			st.setString(2, h1.getLocation());
			st.setInt(3,  h1.getHospitalID());
			st.executeUpdate();
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void delete(int hospitalID) {
		String sql = "delete from hospital where hospitalID=? ";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, hospitalID);
			st.executeUpdate();
		}catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
/*--------------------------------------------------------------------Doctor----------------------------------------*/
	public List<Admin> getDoctors(){
		List<Admin> doctors = new ArrayList<>();
		String sql = "select * from doctor where valid = 1";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				Admin d = new Admin();
				
				d.setDoctorID(rs.getInt(1));
				d.setDNIC(rs.getString(2));
				d.setDgender(rs.getString(3));
				d.setDgender(rs.getString(3));
				d.setDfirstName(rs.getString(4));
				d.setDlastName(rs.getString(5));
				d.setDemail(rs.getString(6));
				d.setSpecification(rs.getString(7));
				d.setDcontact(rs.getInt(8));
				d.setWorkDate(rs.getString(9));
				d.setWorkTime(rs.getString(10));
				d.setValid(rs.getBoolean(11));
				
				doctors.add(d);
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		
		return doctors;
	}

	public List<Admin> getNewDoctors(){
		List<Admin> doctors = new ArrayList<>();
		String sql = "select * from doctor where valid = 0";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				Admin d = new Admin();
				
				d.setDoctorID(rs.getInt(1));
				d.setDNIC(rs.getString(2));
				d.setDgender(rs.getString(3));
				d.setDgender(rs.getString(3));
				d.setDfirstName(rs.getString(4));
				d.setDlastName(rs.getString(5));
				d.setDemail(rs.getString(6));
				d.setSpecification(rs.getString(7));
				d.setDcontact(rs.getInt(8));
				d.setWorkDate(rs.getString(9));
				d.setWorkTime(rs.getString(10));
				d.setValid(rs.getBoolean(11));
				
				doctors.add(d);
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		
		return doctors;
	}
	
	public void doctorValidate(Admin d1) {
		String sql = "update doctor set valid=? where DoctorID=?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setBoolean(1,  d1.getValid());
			
			st.executeUpdate();
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
/*--------------------------------------------------------------------Appointment---------------------------- ----------*/
	public List<Admin> getAppointments(){
		List<Admin> appointments = new ArrayList<>();
		String sql = "select * from appointment";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				Admin a = new Admin();
				
				a.setAppointmentID(rs.getInt(1));
				a.setAPdate(rs.getString(2));
				a.setAPtime(rs.getString(3));
				
				appointments.add(a);
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		
		return appointments;
	}
	
/*------------------------------------------------------------------Patients--------------------------------------*/
	public List<Admin> getPatientDetails(){
		List<Admin> patients = new ArrayList<>();
		String sql = "select * from patient";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				Admin p = new Admin();
				
				p.setPatientID(rs.getInt(1));
				p.setPNIC(rs.getString(2));
				p.setPfirstName(rs.getString(3));
				p.setPlastName(rs.getString(4));
				p.setPemail(rs.getString(5));
				p.setPaddress(rs.getString(6));
				p.setPcity(rs.getString(7));
				p.setPcontact(rs.getInt(8));
				
				patients.add(p);
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		
		return patients;
	}

	
/*------------------------------------------------------------------Payment--------------------------------------*/
	public List<Admin> getPayemtDetails(){
		List<Admin> payment = new ArrayList<>();
		String sql = "select * from payment";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				Admin p = new Admin();
				
				p.setPaymentID(rs.getInt(1));
				p.setType(rs.getString(2));
				p.setDateAndTime(rs.getString(3));
				p.setPamount(rs.getDouble(4));
				p.setPaymentStatus(rs.getString(5));
				
				payment.add(p);
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		
		return payment;
	}

}
