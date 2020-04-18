package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.parser.Parser;
import org.jsoup.nodes.Document;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Doctor;

@Path("/doctor")
public class DoctorService {
	
	Doctor doctor =  new Doctor();

    
    //Inserting Doctors
    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
    @Produces(MediaType.TEXT_PLAIN)
    public String insertDoctor(@FormParam("NIC") String NIC, @FormParam("gender") String gender, @FormParam("firstName") String firstName,
    		@FormParam("lastName") String lastName, @FormParam("email") String email, @FormParam("specification") String specification,
    		@FormParam("contact") String contact, @FormParam("workDate") String workDate, @FormParam("workTime") String workTime,
    		@FormParam("password") String password, @FormParam("adminID") String adminID, @FormParam("doctorStatus") String doctorStatus, @FormParam("valid") String valid)
    {
    	String output = doctor.insertDoctor(NIC, gender, firstName, lastName, email, specification, contact, workDate, workTime, password, adminID, doctorStatus, valid);
    	return output;
    }
    
    //reading Doctors
    @GET
    @Path("/")
    @Produces(MediaType.TEXT_HTML)
    public String readDoctors()
    {
    	return doctor.readDoctors();
    }

    //update doctor
    @PUT
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN) 
    public String updateDoctor(String doctorData)
    {
    	//convert the input string to a JSON object
    	JsonObject jdoctor = new JsonParser().parse(doctorData).getAsJsonObject();
    	
    	//read the values from the JSON object
    	String doctorID = jdoctor.get("doctorID").getAsString();
    	String NIC = jdoctor.get("NIC").getAsString();
    	String gender = jdoctor.get("gender").getAsString();
    	String firstName = jdoctor.get("firstName").getAsString();
    	String lastName = jdoctor.get("lastName").getAsString();
    	String email = jdoctor.get("email").getAsString();
    	String specification = jdoctor.get("specification").getAsString();
    	String contact = jdoctor.get("contact").getAsString();
    	String workDate = jdoctor.get("workDate").getAsString();
    	String workTime = jdoctor.get("workTime").getAsString();
    	String password = jdoctor.get("password").getAsString();
    	String adminID = jdoctor.get("adminID").getAsString();
    	String doctorStatus = jdoctor.get("doctorStatus").getAsString();
    	String valid = jdoctor.get("valid").getAsString();
    	
    	String output = doctor.updateDoctor(doctorID, NIC, gender, firstName, lastName, email, specification, contact, workDate, workTime, password, adminID, doctorStatus, valid);
    	
    	return output;
    }

    //delete doctor
    @DELETE
    @Path("/")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteDoctor(String doctorData)
    {
    	//convert the input string to an XML document
    	Document doc = Jsoup.parse(doctorData, "", Parser.xmlParser());
    	
    	//read the value from the element<itemID>
    	String doctorID = doc.select("doctorID").text();
    	
    	String output = doctor.deleteDoctor(doctorID);
    	
    	return output;
    }
    		
    		
}
