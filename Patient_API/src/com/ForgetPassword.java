package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.LoginRepository;

/**
 * @author Malidi
 *
 */
@Path("forgetpassword")
//for user reset password (doctor/admin/patient)
public class ForgetPassword {
	LoginRepository logrepo=new LoginRepository();
	
	@PUT
	@Produces({ MediaType.TEXT_PLAIN })
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String changePassword(@FormParam("email") String logEmail,@FormParam("password") String logPassword)
	{
	
		if(logEmail!=null || logPassword!=null)
		{
			
			return logrepo.forgetPassword(logEmail, logPassword);
		}else {
			
			return "Please don't leave email or password blank";
		}
		
		
	}

}
