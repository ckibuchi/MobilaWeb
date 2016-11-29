package com.mobila.ejb;

import com.mobila.model.Ticket;
import com.mobila.utils.Status;

import java.util.List;

/**
 * Created by ckibuchi on 11/2/2016.
 */
public interface TicketEJBI {
    public List<Ticket> getAllTickets();
    public List<Ticket> getAllTicketsBySite(Long siteId);
    public Ticket getTicketByScanCode(String scanCode);
    public Long getTicketCount(Status status);
    public Ticket SaveOrUpdate(String ticketData) throws Exception;
}
