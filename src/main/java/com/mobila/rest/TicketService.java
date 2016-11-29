package com.mobila.rest;

import com.mobila.ejb.TicketEJB;
import com.mobila.ejb.TicketEJBI;
import com.mobila.model.Ticket;
import com.mobila.model.Ticket;
import com.mobila.utils.Data;
import org.json.JSONObject;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.util.List;

/**
 * Created by ckibuchi on 11/2/2016.
 */
@Path("/tickets")
public class TicketService {

    @EJB(mappedName = "java:global/Mobila/TicketEJB")
    protected TicketEJBI ticketEJB;


    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ticket> getAllTickets(){

        List<Ticket> ticketList=  ticketEJB.getAllTickets();
        System.out.println("ticketList "+ticketList);
        return ticketList;
    }
    @POST
    @Path("/saveorupdate")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON})
        public Response SaveOrUpdate(InputStream ticket){
        JSONObject reply=new JSONObject();
        StringBuilder crunchifyBuilder = Data.Readinput(ticket);

        System.out.println("Data Received: " + crunchifyBuilder.toString());
        try {
            String ticketdata= crunchifyBuilder.toString();
            System.out.println("ticketdata: "+ticketdata);
            JSONObject incomingticket=new JSONObject(ticketdata);
            Ticket myticket = ticketEJB.SaveOrUpdate(incomingticket.get("ticket").toString());
            System.out.println("ticket " + myticket);

            reply.append("status","success");
            reply.append("ticket",myticket.toString2());
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
    @Path("/getTicketDetails")
    @Produces(MediaType.APPLICATION_JSON)
    public Response Authenticate(InputStream ticketdetails){
        JSONObject reply=new JSONObject();
        StringBuilder datareceived = Data.Readinput(ticketdetails);
        try {
            JSONObject incomingdetails=new JSONObject(datareceived.toString());
            JSONObject ticketjson=incomingdetails.getJSONObject("ticket");
            System.out.println("ticketjson "+ticketjson);
            Ticket ticket = ticketEJB.getTicketByScanCode(ticketjson.get("scancode").toString());

            if(ticket!=null)
            {
                System.out.println("ticket " + ticket.toString());
                reply.append("status","success");
                reply.append("ticket",ticket.toString2());
                String json = reply.toString();//convert entity to json
                return Response.ok(json, MediaType.APPLICATION_JSON).build();
            }
            else
            {reply.append("status","false");
                reply.append("message","Ticket not Found");
                String json = reply.toString();//convert entity to json
                return Response.ok(json, MediaType.APPLICATION_JSON).build();}
            //return ticket;
        }
        catch(Exception e)
        {e.printStackTrace();
            reply.append("status","false");
            reply.append("message","Error: "+e.getMessage());
            String json = reply.toString();//convert entity to json
            return Response.ok(json, MediaType.APPLICATION_JSON).build();}
    }
}
