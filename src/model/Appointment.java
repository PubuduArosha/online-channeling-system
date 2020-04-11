package model;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Appointment {

	// A common method to connect to the DB
	private static Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password

			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/pafdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	// Inserting Appoinment
	private String insertAppoinment1(String code, String name, String price, String desc) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			String query = " insert into items  ('itemID','itemCode','itemName','itemPrice','itemDesc')"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// Get Appoinment details
	private String GetAppoinment() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			String query = " insert into items  ('itemID','itemCode','itemName','itemPrice','itemDesc')"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// Get All Appoinment details
	public static String GetAllAppoinments() {
		String output = "";

		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for GetAll Appointments.";
			}

			output = "<table border=\"1\"><tr><th>Appointment ID</th><th>Date</th><th>Time</th><th>hospitalID</th>"
					+ "<th>patientID</th><th>doctorID</th><th>paymentID </th><th>Status</th></tr>";
			String query = "select * from appointment";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// PreparedStatement preparedStmt = con.prepareStatement(query);

			// preparedStmt.execute();

			while (rs.next()) {
				String AppID = Integer.toString(rs.getInt("appointmentID"));
				String date = rs.getString("date");
				String time = rs.getString("time");
				String hospitalID = Integer.toString(rs.getInt("hospitalID"));
				String patientID = rs.getString("patientID");
				String doctorID = rs.getString("doctorID");
				String paymentID = Integer.toString(rs.getInt("paymentID"));
				String Status = rs.getString("appointmentStatus");
				
				output += "<tr><td>" + AppID + "</td>";
				output += "<td>" + date + "</td>";
				output += "<td>" + time + "</td>";
				output += "<td>" + hospitalID + "</td>";
				output += "<td>" + patientID + "</td>";
				output += "<td>" + doctorID + "</td>";
				output += "<td>" + paymentID + "</td>";
				output += "<td>" + Status + "</td>";

//				output += "<td><input name=\"btnUpdate\" type=\"button\"   value=\"Update\" class=\"btn btn-secondary\"></td>"
//						+ "<td><form method=\"post\" action=\"items.jsp\">"
//						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\" class=\"btn btn-danger\">"
//						+ "<input name=\"itemID\" type=\"hidden\" value=\"" + itemID + "\">" + "</form></td></tr>";
			}

			con.close();
			output += "</table>";
			return output;
		} catch (Exception e) {
			output = "Error while GetAll Appointments.";
			// return output;
			System.err.println(e.getMessage());
		}
		return output;
	}

	// update Appoinment
	private String updateAppoinment(String code) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			String query = " insert into items  ('itemID','itemCode','itemName','itemPrice','itemDesc')"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// Delete Appoinment
	private String DeleteAppoinment(String code) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			String query = " insert into items  ('itemID','itemCode','itemName','itemPrice','itemDesc')"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
