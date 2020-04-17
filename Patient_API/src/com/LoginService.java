package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.LoginRepository;

@Path("login")
public class LoginService {
	LoginRepository logrepo=new LoginRepository();
	@POST
	@Produces({ MediaType.TEXT_PLAIN })
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String LoginForSystem(@FormParam("username") String logEmail,@FormParam("password") String logPassword)
	{
		if(logEmail!=null || logPassword!=null)
		{
//			JsonObject itemObject1 = new JsonParser().parse(logEmail).getAsJsonObject();
//			String email = itemObject1.get("email").getAsString();
//			System.out.println(email);
//			JsonObject itemObject2 = new JsonParser().parse(logPassword).getAsJsonObject();
//			String password = itemObject2.get("password").getAsString();
//			System.out.println(password);
//			
			return logrepo.login(logEmail, logPassword);
		}else {
			
			return "Please don't leave email or password blank";
		}
		
		
	}

}
