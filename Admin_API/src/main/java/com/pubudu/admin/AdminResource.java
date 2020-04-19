package com.pubudu.admin;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
//import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("admin")
public class AdminResource {
	
	AdminRepository admin = new AdminRepository();
	
/*-------------------------------------------------------hospital----------------------------------*/
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Admin> getHospitals() {
		System.out.println("getHospital called...");
		return admin.getHospitals();
	}
	
	@GET
	@Path("hospital/{hospitalID}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Admin getHospital(@PathParam("hospitalID") int hospitalID) {
		System.out.println(hospitalID);
		return admin.getHospital(hospitalID);
	}
	
	@POST
	@Path("hospital")
	@Produces(MediaType.APPLICATION_JSON)
	public Admin add(Admin h1) 
	{
		admin.add(h1);
		return h1;
	}

	@PUT
	@Path("hospital")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Admin updateHospital(Admin h1) {
		System.out.println(h1);
		if (admin.getHospital(h1.getHospitalID()).getHospitalID() == 0) {
			admin.add(h1);
		} else {
			admin.update(h1);
		}
		return h1;
	}
	
	@DELETE
	@Path("hospital/{hospitalID}")
	public Admin deleteHospital(@PathParam("hospitalID") int hospitalID) {
		Admin h = admin.getHospital(hospitalID);

		if (h.getHospitalID() != 0) {
			admin.delete(hospitalID);
		}

		return h;
	}

/*-------------------------------------------------------Doctor----------------------------------*/
	@Path("doctor")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Admin> getDoctors() {
		System.out.println("getDoctor called...");
		return admin.getDoctors();
	}

	@Path("newdoctors")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Admin> getNewDoctors() {
		System.out.println("getNewDoctor called...");
		return admin.getNewDoctors();
	}
	
	@PUT
	@Path("doctorapprove/{doctorID}")
	public Admin doctorValidate(Admin d1) {
		System.out.println(d1);
		admin.doctorValidate(d1);
		
		return d1;
	}
	
/*-------------------------------------------------------Appointments----------------------------------*/
	@Path("appointmentsdetails")
	@GET
	public List<Admin> getAppointments() {
		System.out.println("getAppointment called...");
		return admin.getAppointments();
	}
	
/*-------------------------------------------------------Patient----------------------------------*/
	@Path("patientdetails")
	@GET
	public List<Admin> getPatientDetails() {
		System.out.println("getPatient called...");
		return admin.getPatientDetails();
	}
	
/*-------------------------------------------------------Payment----------------------------------*/
	@Path("paymentdetails")
	@GET
	public List<Admin> getPayemtDetails() {
		System.out.println("getPayemtDetails called...");
		return admin.getPayemtDetails();
	}
	
}
