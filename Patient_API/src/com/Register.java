package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.PatientRepository;

/**
 * @author Malidi
 *
 */

@Path("register")
//For patient Registration
public class Register {

	PatientRepository repo=new PatientRepository();
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String CreatePatient(@FormParam("NIC") String NIC,@FormParam("firstname") String firstname,@FormParam("lastname") String lastname,@FormParam("email") String email,@FormParam("gender") String gender,@FormParam("address") String address,@FormParam("password") String password,@FormParam("city") String city,@FormParam("contact") String contact)
	{
		System.out.println(password);
		if(NIC.length()==0 || email.length()==0 || password.length()==0)
		{
			return "You cannot keep mandatory fields password, email, NIC null";
			
		}else {
			if((NIC.length()>13) || (NIC.length()<11))
			{
				return "Please provide Valid NIC";
			}else {
				if(password.length()<7)
				{
					return "Please provide a strong password HINT: greater than 7 integers";
				}else {
					String output = repo.createPatientAsForm(NIC, firstname, lastname, email, gender, address, password, city, contact);
					return output;
				}
				
				
			}
			
			
		}
		
		
		
	}

}
