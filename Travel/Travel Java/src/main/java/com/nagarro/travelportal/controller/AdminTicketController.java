package com.nagarro.travelportal.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.nagarro.travelportal.Service.TicketService;
import com.nagarro.travelportal.model.Ticket;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AdminTicketController {

	/** The log. */
	private Logger log = Logger.getLogger(AdminTicketController.class);

	/** The ticket service. */
	@Autowired
	private TicketService ticketService;

	/**
	 * Gets the all tickets.
	 *
	 * @return the all tickets
	 */
	@GetMapping("/admin/ticket")
	public List<Ticket> getAllTickets() {
		return ticketService.getAllTicket();
	}

	/**
	 * Update ticket.
	 *
	 * @param newDetails the ticket to update
	 * @return the response entity
	 */

	@PostMapping(value = "/admin/ticket/update", consumes = { "multipart/form-data" }

	)
	public ResponseEntity<String> updateTicket(@Valid @RequestPart("ticket") Ticket newDetails,
			@RequestPart(required = false) MultipartFile file) {

		try {
			JSONObject jsonWithFileUrl = ticketService.processTicketRequest(newDetails, file);
			return new ResponseEntity<>(jsonWithFileUrl.toString(), HttpStatus.OK);

		} catch (IOException e) {
			log.info("File not uploaded");
		}
		return new ResponseEntity<String>(HttpStatus.EXPECTATION_FAILED);
	}

	/**
	 * Gets the ticket by id.
	 *
	 * @param principal the principal
	 * @param id        the id
	 * @return the ticket by id
	 */
	@GetMapping("/admin/ticket/{id}")
	public ResponseEntity<Ticket> getTicketById(Principal principal, @PathVariable(value = "id") Integer id) {
		if (principal.getName().equals("admin@nagarro.com")) {
			Ticket fetchedTicket = ticketService.getTicketById(id);
			return new ResponseEntity<>(fetchedTicket, HttpStatus.OK);
		}
		log.info("Requested Ticket doesnot exist");
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

	}

}
