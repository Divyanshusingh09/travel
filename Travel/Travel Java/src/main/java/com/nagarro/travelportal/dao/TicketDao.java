package com.nagarro.travelportal.dao;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.nagarro.travelportal.Enum.TicketStatus;
import com.nagarro.travelportal.model.Ticket;


/**
 * The Interface TicketDao.
 */
public interface TicketDao extends JpaRepository<Ticket, Integer>{
	
	/**
	 * Find by username.
	 *
	 * @param username the username
	 * @return the list
	 * @throws NoSuchElementException the no such element exception
	 */
	@Query("FROM Ticket where employee_username =:username")
    List<Ticket> findByUsername(String username) throws NoSuchElementException;
	
	/**
	 * Ticket raise by.
	 *
	 * @param id the id
	 * @return the string
	 */
	@Query("SELECT t.employee.username FROM Ticket t WHERE t.ticketId =:id")
	String ticketRaiseBy(Integer id) ;
	
	/**
	 * Process ticket request.
	 *
	 * @param remarks the remarks
	 * @param status the status
	 * @param file the file
	 */
	@Transactional
	@Modifying
	@Query("UPDATE Ticket t set t.remarks=:remarks , t.downloadLink=:link , t.status=:status where t.ticketId=:id")
	void processTicketRequest(String remarks,TicketStatus status,String link,Integer id);
	

}
