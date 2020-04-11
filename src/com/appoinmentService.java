package com;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/paf")
public class appoinmentService {
	@GET 
    @Produces("text/plain")
    public String getIt() {
        return "Hi jude!";
    }
}
