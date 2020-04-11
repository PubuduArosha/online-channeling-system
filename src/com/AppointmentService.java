package com;

import model.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.*;

@Path("/paf")
public class AppointmentService {
	
//	@GET 
//    @Produces("text/plain")
//    public String getIt() {
//        return "Hi appoinmentService!";
//    }
	
	@GET
	@Path("/")
	@Produces({MediaType.TEXT_HTML})
	public String GellApp (){
		return  Appointment.GetAllAppoinments();
	}
}
