package com.mobila.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobila.dao.generic.GenericDaoImpl;
import com.mobila.model.Ticket;
import com.mobila.utils.Status;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import javax.ejb.Stateless;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by ckibuchi on 11/2/2016.
 */
@Stateless
public class TicketDAOImpl extends GenericDaoImpl<Ticket,Long> implements TicketDAO{
    ObjectMapper mapper = new ObjectMapper();
    SimpleDateFormat df=new SimpleDateFormat("ddMMyyyyhhmmss");
    @Override
    public List<Ticket> getAllTickets() {
        return findAll();
    }

    @Override
    public List<Ticket> getAllTicketsBySite(Long siteId) {
        return null;
    }

    @Override
    public Ticket getTicketByScanCode(String scanCode) {
        Session session = (Session)getEm().unwrap((Class)Session.class);
        Criteria crit=session.createCriteria(Ticket.class);
        crit.add((Criterion) Restrictions.eq("scancode",scanCode));
        crit.addOrder(Order.desc("id"));
        List<Ticket> tickets = crit.list();
        if(tickets.size()>0) {
            //Here you can do ticket fee calculation by calling an external class, pass ticket and change it's amount and send back
            return tickets.get(0);
        }
        return null;
    }


    @Override
    public Long getTicketCount(Status status) {
        return null;
    }

    @Override
    public Ticket SaveOrUpdate(String ticketData) throws Exception {
        System.out.println("ticketData "+ticketData);
        Ticket ticket= mapper.readValue(ticketData, Ticket.class);
        Date now=new Date();
        if(ticket.getTimedatecreated()==null)
            ticket.setTimedatecreated(now);
        String scancode=df.format(now);
        System.out.println("scancode "+ scancode);
        ticket.setScancode(scancode);
        return save(ticket);
    }
}
