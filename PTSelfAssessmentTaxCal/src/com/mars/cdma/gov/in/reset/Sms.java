package com.mars.cdma.gov.in.reset;

import java.awt.PageAttributes.MediaType;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriBuilder;

import com.google.gson.Gson;
import com.mars.cdma.gov.helper.Dbcon;
import com.mars.cdma.gov.helper.SMSHttpPostClient;
//import com.sun.jersey.api.client.Client;
//import com.sun.jersey.api.client.WebResource;

@Path("/sms")
public class Sms {
	@GET
	 @Path("{number}/{msg}")
	  @Produces("application/json")
	public String sendsms(@PathParam("number")String number,@PathParam("msg") String msg){
		
		ResponseBuilder builder;
		String result=null;
		Gson gson=new Gson();
		System.out.println("number "+number+" Msg "+msg+" URL ");
		SMSHttpPostClient sms=new SMSHttpPostClient();
		  sms.SendSMSmain(number, msg);
		 	result="success";
		    return result; 
	}
	
 	 @GET
	 @Path("/sendsmsuri")
public Response getRedirect(@QueryParam("number")String number,@QueryParam("msg")String msg,@QueryParam("uri") String url,@Context ServletContext context) throws URISyntaxException {
 		 System.out.println("number "+number+" msg "+msg+" URI "+url);
		 SMSHttpPostClient sms=new SMSHttpPostClient();
		 sms.SendSMSmain(number, msg);
		  
		 URI uri = new URI("http://"+url);
		  return Response.temporaryRedirect(uri).build();
	    }
	 
	public static void main(String[] args) {
		
		    
	}
}
