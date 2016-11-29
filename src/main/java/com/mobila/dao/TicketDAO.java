package com.mobila.dao;

import com.mobila.dao.generic.GenericDAO;
import com.mobila.model.Ticket;
import com.mobila.utils.Status;

import java.util.List;

/**
 * Created by ckibuchi on 11/2/2016.
 */
public interface TicketDAO extends GenericDAO<Ticket,Long> {
    public List<Ticket> getAllTickets();
    public List<Ticket> getAllTicketsBySite(Long siteId);
    public Ticket getTicketByScanCode(String scanCode);
    public Long getTicketCount(Status status);
    public Ticket SaveOrUpdate(String ticketData) throws Exception;

}
