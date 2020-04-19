package com.api.ApiAuthTester;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.*;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
public class SecurityFilter implements ContainerRequestFilter {

	private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
	private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";
	
//	private static final String SECURED_URL_PREFIX_app_Ins = "http://localhost:5000/AppointmentAPI/webService/appointment/add";
//	private static final String SECURED_URL_PREFIX_app_del = "http://localhost:5000/AppointmentAPI/webService/appointment/delete";
//	private static final String SECURED_URL_PREFIX_app_upd = "http://localhost:5000/AppointmentAPI/webService/appointment/update";
//	private static final String SECURED_URL_PREFIX_app_get = "http://localhost:5000/AppointmentAPI/webService/appointment/app";
//	private static final String SECURED_URL_PREFIX_app_getall = "http://localhost:5000/AppointmentAPI/webService/appointment/";
	

//	private static final String SECURED_URL_PREFIX_doc_del = "http://localhost:6000/DoctorAPI/WebServices/doctor/delete";
//	private static final String SECURED_URL_PREFIX_doc_upd ="http://localhost:6000/DoctorAPI/WebServices/doctor/update";
//	private static final String SECURED_URL_PREFIX_doc_getall = "http://localhost:6000/DoctorAPI/WebServices/doctor/";
	
	
//	private static final String SECURED_URL_PREFIX_pay_Ins = "http://localhost:7000/Payment_API/payAPI/Payment/add";
//	private static final String SECURED_URL_PREFIX_pay_upd ="http://localhost:7000/Payment_API/payAPI/Payment/update";
//	private static final String SECURED_URL_PREFIX_pay_getall ="http://localhost:7000/Payment_API/payAPI/Payment/";
							
//	private static final String SECURED_URL_PREFIX_ref_Ins = "http://localhost:7000/Payment_API/payAPI/Refund/add";
//	private static final String SECURED_URL_PREFIX_ref_del = "http://localhost:7000/Payment_API/payAPI/Refund/delete";
//	private static final String SECURED_URL_PREFIX_ref_upd = "http://localhost:7000/Payment_API/payAPI/Refund/update";
//	private static final String SECURED_URL_PREFIX_ref_getall = "http://localhost:7000/Payment_API/payAPI/Refund/";
	
//	private static final String SECURED_URL_PREFIX_adm_Ins = "http:// localhost:8099/admin/webapi/admin/hospital";
//	private static final String SECURED_URL_PREFIX_adm_getall = "http:// localhost:8099/admin/webapi/admin/";

	
//	private static final String SECURED_URL_PREFIX_Pat_upd = "http://localhost:8080/PatientAPI/webService/patient/update";
//	private static final String SECURED_URL_PREFIX_pat_getall = "http://localhost:8080/PatientAPI/webService/patient/all";
//	private static final String SECURED_URL_PREFIX_Pat_del = "http://localhost:8080/PatientAPI/webService/patient/delete";
//	private static final String SECURED_URL_PREFIX_Pat_id = "http://localhost:8080/PatientAPI/webService/patient/id";
		
	// Patient Only

	private static final String SECURED_URL_PREFIX_app_Ins = "http://localhost:5000/AppointmentAPI/webService/appointment/add";
	private static final String SECURED_URL_PREFIX_app_del = "http://localhost:5000/AppointmentAPI/webService/appointment/delete";
	private static final String SECURED_URL_PREFIX_pay_Ins = "http://localhost:7000/Payment_API/payAPI/Payment/add";
	private static final String SECURED_URL_PREFIX_ref_Ins = "http://localhost:7000/Payment_API/payAPI/Refund/add";
	
	//doctor only
	
	
	
	//admin only
	private static final String SECURED_URL_PREFIX_app_getall = "http://localhost:5000/AppointmentAPI/webService/appointment/";
	private static final String SECURED_URL_PREFIX_doc_del = "http://localhost:6000/DoctorAPI/WebServices/doctor/delete";
	private static final String SECURED_URL_PREFIX_doc_getall = "http://localhost:6000/DoctorAPI/WebServices/doctor/";
	private static final String SECURED_URL_PREFIX_pay_upd ="http://localhost:7000/Payment_API/payAPI/Payment/update";
	private static final String SECURED_URL_PREFIX_pay_getall ="http://localhost:7000/Payment_API/payAPI/Payment/";
	private static final String SECURED_URL_PREFIX_ref_del = "http://localhost:7000/Payment_API/payAPI/Refund/delete";
	private static final String SECURED_URL_PREFIX_ref_upd = "http://localhost:7000/Payment_API/payAPI/Refund/update";
	private static final String SECURED_URL_PREFIX_ref_getall = "http://localhost:7000/Payment_API/payAPI/Refund/";
	private static final String SECURED_URL_PREFIX_adm_Ins = "http:// localhost:8099/admin/webapi/admin/hospital";
	private static final String SECURED_URL_PREFIX_adm_getall = "http:// localhost:8099/admin/webapi/admin/";
	private static final String SECURED_URL_PREFIX_pat_getall = "http://localhost:8080/PatientAPI/webService/patient/all";
	
	//doctor and admin
	private static final String SECURED_URL_PREFIX_app_upd = "http://localhost:5000/AppointmentAPI/webService/appointment/update";
	private static final String SECURED_URL_PREFIX_doc_upd ="http://localhost:6000/DoctorAPI/WebServices/doctor/update";
	
	
	//patient and admin
	private static final String SECURED_URL_PREFIX_Pat_upd = "http://localhost:8080/PatientAPI/webService/patient/update";
	private static final String SECURED_URL_PREFIX_Pat_del = "http://localhost:8080/PatientAPI/webService/patient/delete";
	private static final String SECURED_URL_PREFIX_Pat_id = "http://localhost:8080/PatientAPI/webService/patient/id";
	
	
	//all three
	private static final String SECURED_URL_PREFIX_app_get = "http://localhost:5000/AppointmentAPI/webService/appointment/app";
	

	DBconnection con = new DBconnection();

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		if ((requestContext.getUriInfo().getPath().equals(SECURED_URL_PREFIX_app_Ins))
				|| (requestContext.getUriInfo().getPath().equals(SECURED_URL_PREFIX_app_del))
				|| (requestContext.getUriInfo().getPath().equals(SECURED_URL_PREFIX_pay_Ins))
				|| (requestContext.getUriInfo().getPath().equals(SECURED_URL_PREFIX_ref_Ins))) {
			List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);

			if (authHeader != null && authHeader.size() > 0) {

				String authToken = authHeader.get(0);
				authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
				byte[] decodedBytes = Base64.getDecoder().decode(authToken);
				String decodeString = new String(decodedBytes);

				StringTokenizer tokenizer = new StringTokenizer(decodeString, ":");

				String username = tokenizer.nextToken();
				String password = tokenizer.nextToken();

				Connection connection = con.connect();

				// String query1 = "SELECT * From Patient where ";
				String email = username;
				String pwd = password;

				String sql = new String("SELECT * FROM patient WHERE email=? AND password=?");

				try {

					PreparedStatement stm = connection.prepareStatement(sql);
					stm.setString(1, email);
					stm.setString(2, pwd);
					ResultSet rs = stm.executeQuery();

					if (rs.next()) {
						if (email.equals(username) && pwd.equals(password)) {
							return;
						}
					}

				} catch (SQLException e) {
					e.printStackTrace();

					Response unauthorizedStatus = Response.status(Response.Status.UNAUTHORIZED)
							.entity("User cannot access ! Enter username & password.").build();

					requestContext.abortWith(unauthorizedStatus);
				}
			}
			} else if ((requestContext.getUriInfo().getPath().equals(SECURED_URL_PREFIX_app_getall))
					|| (requestContext.getUriInfo().getPath().equals(SECURED_URL_PREFIX_doc_del))
					|| (requestContext.getUriInfo().getPath().equals(SECURED_URL_PREFIX_doc_getall))
					|| (requestContext.getUriInfo().getPath().equals(SECURED_URL_PREFIX_pay_upd))
					|| (requestContext.getUriInfo().getPath().equals(SECURED_URL_PREFIX_pay_getall))
					|| (requestContext.getUriInfo().getPath().equals(SECURED_URL_PREFIX_ref_del))
					|| (requestContext.getUriInfo().getPath().equals(SECURED_URL_PREFIX_ref_upd))
					|| (requestContext.getUriInfo().getPath().equals(SECURED_URL_PREFIX_ref_getall))
					|| (requestContext.getUriInfo().getPath().equals(SECURED_URL_PREFIX_adm_Ins))
					|| (requestContext.getUriInfo().getPath().equals(SECURED_URL_PREFIX_adm_getall))
					|| (requestContext.getUriInfo().getPath().equals(SECURED_URL_PREFIX_pat_getall))) {
				List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);

				if (authHeader != null && authHeader.size() > 0) {

					String authToken = authHeader.get(0);
					authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
					byte[] decodedBytes = Base64.getDecoder().decode(authToken);
					String decodeString = new String(decodedBytes);

					StringTokenizer tokenizer = new StringTokenizer(decodeString, ":");

					String username = tokenizer.nextToken();
					String password = tokenizer.nextToken();

					Connection connection = con.connect();

					String email = username;
					String pwd = password;

					//String sql3 = new String("SELECT * FROM doctor WHERE email=? And password=?");
					String sql2 = new String("SELECT * FROM admin WHERE username=? And password=?");
					//String sql = new String("SELECT * FROM patient WHERE email=? AND password=?");

					try {

						PreparedStatement stm = connection.prepareStatement(sql2);
						stm.setString(1, email);
						stm.setString(2, pwd);
						ResultSet rs = stm.executeQuery();

						if (rs.next()) {
							if (email.equals(username) && pwd.equals(password)) {
								return;
							}
						}

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();

						Response unauthorizedStatus = Response.status(Response.Status.UNAUTHORIZED)
								.entity("User cannot access ! Enter username & password.").build();

						requestContext.abortWith(unauthorizedStatus);
					}
				}
			} else if ((requestContext.getUriInfo().getPath().equals(SECURED_URL_PREFIX_app_upd))
					|| (requestContext.getUriInfo().getPath().equals(SECURED_URL_PREFIX_doc_upd))) {
				List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);

				if (authHeader != null && authHeader.size() > 0) {

					String authToken = authHeader.get(0);
					authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
					byte[] decodedBytes = Base64.getDecoder().decode(authToken);
					String decodeString = new String(decodedBytes);

					StringTokenizer tokenizer = new StringTokenizer(decodeString, ":");

					String username = tokenizer.nextToken();
					String password = tokenizer.nextToken();

					Connection connection = con.connect();

					String email = username;
					String pwd = password;

					String sql3 = new String("SELECT * FROM doctor WHERE email=? And password=?");
					String sql2 = new String("SELECT * FROM admin WHERE username=? And password=?");
					//String sql = new String("SELECT * FROM Admin WHERE username=? AND password=?");

					try {
						PreparedStatement stm = connection.prepareStatement(sql3);
						stm.setString(1, email);
						stm.setString(2, pwd);
						ResultSet rs = stm.executeQuery();

						if (rs.next()) {
							if (email.equals(username) && pwd.equals(password)) {
								return;
							}

						} else if (!rs.next()) {
							PreparedStatement stm1 = connection.prepareStatement(sql2);
							stm1.setString(1, email);
							stm1.setString(2, pwd);
							ResultSet rs1 = stm1.executeQuery();

							if (rs1.next()) {
								if (email.equals(username) && pwd.equals(password)) {
									return;
								}

							}

						}

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();

						Response unauthorizedStatus = Response.status(Response.Status.UNAUTHORIZED)
								.entity("User cannot access ! Enter username & password.").build();

						requestContext.abortWith(unauthorizedStatus);
					}
				}
			} else if ((requestContext.getUriInfo().getPath().equals(SECURED_URL_PREFIX_Pat_upd))
					|| (requestContext.getUriInfo().getPath().equals(SECURED_URL_PREFIX_Pat_del))
					|| (requestContext.getUriInfo().getPath().equals(SECURED_URL_PREFIX_Pat_id))) {
				List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);

				if (authHeader != null && authHeader.size() > 0) {

					String authToken = authHeader.get(0);
					authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
					byte[] decodedBytes = Base64.getDecoder().decode(authToken);
					String decodeString = new String(decodedBytes);

					StringTokenizer tokenizer = new StringTokenizer(decodeString, ":");

					String username = tokenizer.nextToken();
					String password = tokenizer.nextToken();

					Connection connection = con.connect();

					String email = username;
					String pwd = password;

					//String sql3 = new String("SELECT * FROM doctor WHERE email=? And password=?");
					String sql2 = new String("SELECT * FROM admin WHERE username=? And password=?");
					String sql = new String("SELECT * FROM patient WHERE email=? AND password=?");

					try {
						PreparedStatement stm = connection.prepareStatement(sql);
						stm.setString(1, email);
						stm.setString(2, pwd);
						ResultSet rs = stm.executeQuery();

						if (rs.next()) {
							if (email.equals(username) && pwd.equals(password)) {
								return;
							}

						} else if (!rs.next()) {
							PreparedStatement stm1 = connection.prepareStatement(sql2);
							stm1.setString(1, email);
							stm1.setString(2, pwd);
							ResultSet rs1 = stm1.executeQuery();

							if (rs1.next()) {
								if (email.equals(username) && pwd.equals(password)) {
									return;
								}

							}

						}

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();

						Response unauthorizedStatus = Response.status(Response.Status.UNAUTHORIZED)
								.entity("User cannot access ! Enter username & password.").build();

						requestContext.abortWith(unauthorizedStatus);
					}
				}
				//#############################################################################################################################################
			}else if ((requestContext.getUriInfo().getPath().equals(SECURED_URL_PREFIX_app_get))) {
				List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);

				if (authHeader != null && authHeader.size() > 0) {

					String authToken = authHeader.get(0);
					authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
					byte[] decodedBytes = Base64.getDecoder().decode(authToken);
					String decodeString = new String(decodedBytes);

					StringTokenizer tokenizer = new StringTokenizer(decodeString, ":");

					String username = tokenizer.nextToken();
					String password = tokenizer.nextToken();

					Connection connection = con.connect();

					String email = username;
					String pwd = password;

					String sql3 = new String("SELECT * FROM doctor WHERE email=? And password=?");
					String sql2 = new String("SELECT * FROM admin WHERE username=? And password=?");
					String sql = new String("SELECT * FROM patient WHERE email=? AND password=?");

					try {
						PreparedStatement stm = connection.prepareStatement(sql);
						stm.setString(1, email);
						stm.setString(2, pwd);
						ResultSet rs = stm.executeQuery();

						if (rs.next()) {
							if (email.equals(username) && pwd.equals(password)) {
								return;
							}

						} else if (!rs.next()) {
							PreparedStatement stm1 = connection.prepareStatement(sql2);
							stm1.setString(1, email);
							stm1.setString(2, pwd);
							ResultSet rs1 = stm1.executeQuery();

							if (rs1.next()) {
								if (email.equals(username) && pwd.equals(password)) {
									return;
								}

							}

						}else if (!rs.next()) {
							PreparedStatement stm1 = connection.prepareStatement(sql3);
							stm1.setString(1, email);
							stm1.setString(2, pwd);
							ResultSet rs1 = stm1.executeQuery();

							if (rs1.next()) {
								if (email.equals(username) && pwd.equals(password)) {
									return;
								}

							}

						}

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();

						Response unauthorizedStatus = Response.status(Response.Status.UNAUTHORIZED)
								.entity("User cannot access ! Enter username & password.").build();

						requestContext.abortWith(unauthorizedStatus);
					}
				}
			}else if ((requestContext.getUriInfo().getPath().contains("myresource"))) {
				List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);

				if (authHeader != null && authHeader.size() > 0) {

					String authToken = authHeader.get(0);
					authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
					byte[] decodedBytes = Base64.getDecoder().decode(authToken);
					String decodeString = new String(decodedBytes);

					StringTokenizer tokenizer = new StringTokenizer(decodeString, ":");

					String username = tokenizer.nextToken();
					String password = tokenizer.nextToken();

					Connection connection = con.connect();

					String email = username;
					String pwd = password;

					String sql3 = new String("SELECT * FROM doctor WHERE email=? And password=?");
					String sql2 = new String("SELECT * FROM admin WHERE username=? And password=?");
					String sql = new String("SELECT * FROM patient WHERE email=? AND password=?");

					try {
						PreparedStatement stm = connection.prepareStatement(sql);
						stm.setString(1, email);
						stm.setString(2, pwd);
						ResultSet rs = stm.executeQuery();

						if (rs.next()) {
							if (email.equals(username) && pwd.equals(password)) {
								return;
							}

						} else if (!rs.next()) {
							PreparedStatement stm1 = connection.prepareStatement(sql2);
							stm1.setString(1, email);
							stm1.setString(2, pwd);
							ResultSet rs1 = stm1.executeQuery();

							if (rs1.next()) {
								if (email.equals(username) && pwd.equals(password)) {
									return;
								}

							}

						}else if (!rs.next()) {
							PreparedStatement stm1 = connection.prepareStatement(sql3);
							stm1.setString(1, email);
							stm1.setString(2, pwd);
							ResultSet rs1 = stm1.executeQuery();

							if (rs1.next()) {
								if (email.equals(username) && pwd.equals(password)) {
									return;
								}

							}

						}

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();

						Response unauthorizedStatus = Response.status(Response.Status.UNAUTHORIZED)
								.entity("User cannot access ! Enter username & password.").build();

						requestContext.abortWith(unauthorizedStatus);
					}
				}
			}
	}
}