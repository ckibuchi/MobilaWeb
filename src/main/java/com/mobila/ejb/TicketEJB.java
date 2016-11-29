package com.mobila.ejb;

import com.mobila.dao.TicketDAO;
import com.mobila.model.Ticket;
import com.mobila.utils.Status;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by ckibuchi on 11/2/2016.
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class TicketEJB implements TicketEJBI {

    @Inject
    TicketDAO ticketDAO;

    @Override
    public List<Ticket> getAllTickets() {
        return ticketDAO.getAllTickets();
    }

    @Override
    public List<Ticket> getAllTicketsBySite(Long siteId) {
        return ticketDAO.getAllTicketsBySite(siteId);
    }

    @Override
    public Ticket getTicketByScanCode(String scanCode) {
        return ticketDAO.getTicketByScanCode(scanCode);
    }

    @Override
    public Long getTicketCount(Status status) {
        return ticketDAO.getTicketCount(status);
    }

    @Override
    public Ticket SaveOrUpdate(String ticketData) throws Exception {
        return ticketDAO.SaveOrUpdate(ticketData);
    }
}
