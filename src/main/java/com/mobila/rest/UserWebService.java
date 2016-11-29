package com.mobila.rest;

/**
 * Created by ckibuchi on 8/13/2016.
 */


import com.mobila.ejb.UserEJBI;
import com.mobila.model.User;
import com.mobila.utils.Data;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.applet.Applet;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@Path("/users")
public class UserWebService {

    @EJB(mappedName = "java:global/Mobila/UserEJB")
    protected UserEJBI userEJB;


    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUsers(){

        List<User> users=  userEJB.getAllUsers();
        System.out.println("users "+users);
        return users;
    }
    @POST
    @Path("/saveorupdate")
    @Produces(MediaType.APPLICATION_JSON)
        public Response SaveOrUpdate(InputStream user){
        JSONObject reply=new JSONObject();
        StringBuilder crunchifyBuilder = Data.Readinput(user);

        System.out.println("Data Received: " + crunchifyBuilder.toString());
        try {
            String userdata= crunchifyBuilder.toString();
            System.out.println("userdata: "+userdata);
            JSONObject incominguser=new JSONObject(userdata);
            User myuser = userEJB.SaveOrUpdate(incominguser.get("user").toString());
            System.out.println("user " + myuser);

            reply.append("status","success");
            reply.append("message","Saved Successfully ");
            String json = reply.toString();//convert entity to json
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e)
        {e.printStackTrace();
            reply.append("status","false");
            reply.append("message","Error: "+e.getMessage());
            String json = reply.toString();//convert entity to json
            return Response.ok(json, MediaType.APPLICATION_JSON).build();}
    }
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response Authenticate(InputStream logindetails){
        JSONObject reply=new JSONObject();
        StringBuilder datareceived = Data.Readinput(logindetails);
        try {
            JSONObject incomingdetails=new JSONObject(datareceived.toString());
            JSONObject userjson=incomingdetails.getJSONObject("user");
            System.out.println("userjson "+userjson);
            User user = userEJB.Authenticate(userjson.get("email").toString(),userjson.get("password").toString());
            System.out.println("user " + user);
            if(user!=null)
            {
                reply.append("status","success");
                reply.append("user",user.toString2());
                String json = reply.toString();//convert entity to json
                return Response.ok(json, MediaType.APPLICATION_JSON).build();
            }
            else
            {reply.append("status","false");
                reply.append("message","Incorrect login details");
                String json = reply.toString();//convert entity to json
                return Response.ok(json, MediaType.APPLICATION_JSON).build();}
            //return user;
        }
        catch(Exception e)
        {e.printStackTrace();
            reply.append("status","false");
            reply.append("message","Error: "+e.getMessage());
            String json = reply.toString();//convert entity to json
            return Response.ok(json, MediaType.APPLICATION_JSON).build();}
    }
}