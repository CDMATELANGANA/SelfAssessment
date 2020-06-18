package com.mars.cdma.gov.in.reset;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.google.gson.Gson;

@Path("Test")
public class Test {

	Gson gson=new Gson();
	@POST
	@Path("meth")
	@Produces("application/json")
	public  String getdata(){
		System.out.println("welcome to Test");
		
		Test test=new Test();
		return gson.toJson("welcome");
		
	}
	
	
}

