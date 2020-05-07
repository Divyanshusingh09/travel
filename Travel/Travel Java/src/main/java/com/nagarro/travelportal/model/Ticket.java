package com.nagarro.travelportal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import java.util.Arrays;
import java.util.Date;

import com.nagarro.travelportal.Enum.Priority;
import com.nagarro.travelportal.Enum.TicketStatus;
import com.nagarro.travelportal.Enum.TicketType;

/**
 * The Entity Ticket.
 */
@Entity
@Table(name = "Ticket")
public class Ticket {

	/** The ticked id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ticketId;

	/** The employee. */
	// @JsonIgnore
	@ManyToOne
	@JoinColumn(name = "employee_username")
	private Employee employee;

	/** The request type. */

	@Enumerated(EnumType.STRING)
	private TicketType requestType;

	/** The priority. */

	@Enumerated(EnumType.STRING)
	private Priority priority;

	/** The travel city. */

	private String travelCity;

	/** The from. */

	@Column(name = "fromCity")
	private String from;

	/** The start date. */

	@Temporal(TemporalType.DATE)
	private Date startDate;

	/** The end date. */

	@Temporal(TemporalType.DATE)
	private Date endDate;

	/** The passport number. */

	@Size(max = 25)
	private String passportNumber;

	/** The project name. */

	private String projectName;

	/** The borne by. */

	private String borneBy;

	/** The approver. */
	private String approver;

	/** The expected duration. */
	private String expectedDuration;

	/** The allowed amount. */
	private String allowedAmount;

	/** The extra details. */
	private String extraDetails;

	/** The submit date. */

	@Temporal(TemporalType.DATE)
	private Date submitDate;

	/** The status. */

	@Enumerated(EnumType.STRING)
	private TicketStatus status;

	/** The files. */
	@Lob
	@Column(length = 100000)
	private byte[] files;

	private String remarks;

	private String downloadLink;

	/**
	 * @return the downloadLink
	 */
	public String getDownloadLink() {
		return downloadLink;
	}

	/**
	 * @param downloadLink the downloadLink to set
	 */
	public void setDownloadLink(String downloadLink) {
		this.downloadLink = downloadLink;
	}

	/**
	 * Instantiates a new ticket.
	 */
	public Ticket() {

	}

	/**
	 * Gets the employee.
	 *
	 * @return the employee
	 */
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * Sets the employee.
	 *
	 * @param employee the employee to set
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	/**
	 * @return the ticketId
	 */
	public int getTicketId() {
		return ticketId;
	}

	/**
	 * Sets the ticked id.
	 *
	 * @param ticketId the ticketId to set
	 */
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	/**
	 * Gets the request type.
	 *
	 * @return the requestType
	 */
	public TicketType getRequestType() {
		return requestType;
	}

	/**
	 * Sets the request type.
	 *
	 * @param requestType the requestType to set
	 */
	public void setRequestType(TicketType requestType) {
		this.requestType = requestType;
	}

	/**
	 * Gets the priority.
	 *
	 * @return the priority
	 */
	public Priority getPriority() {
		return priority;
	}

	/**
	 * Sets the priority.
	 *
	 * @param priority the priority to set
	 */
	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	/**
	 * Gets the travel city.
	 *
	 * @return the travelCity
	 */
	public String getTravelCity() {
		return travelCity;
	}

	/**
	 * Sets the travel city.
	 *
	 * @param travelCity the travelCity to set
	 */
	public void setTravelCity(String travelCity) {
		this.travelCity = travelCity;
	}

	/**
	 * Gets the from.
	 *
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * Sets the from.
	 *
	 * @param from the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * Gets the start date.
	 *
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * Sets the start date.
	 *
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Gets the end date.
	 *
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * Sets the end date.
	 *
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Gets the passport number.
	 *
	 * @return the passportNumber
	 */
	public String getPassportNumber() {
		return passportNumber;
	}

	/**
	 * Sets the passport number.
	 *
	 * @param passportNumber the passportNumber to set
	 */
	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	/**
	 * Gets the project name.
	 *
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * Sets the project name.
	 *
	 * @param projectName the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * Gets the borne by.
	 *
	 * @return the borneBy
	 */
	public String getBorneBy() {
		return borneBy;
	}

	/**
	 * Sets the borne by.
	 *
	 * @param borneBy the borneBy to set
	 */
	public void setBorneBy(String borneBy) {
		this.borneBy = borneBy;
	}

	/**
	 * Gets the approver.
	 *
	 * @return the approver
	 */
	public String getApprover() {
		return approver;
	}

	/**
	 * Sets the approver.
	 *
	 * @param approver the approver to set
	 */
	public void setApprover(String approver) {
		this.approver = approver;
	}

	/**
	 * Gets the expected duration.
	 *
	 * @return the expectedDuration
	 */
	public String getExpectedDuration() {
		return expectedDuration;
	}

	/**
	 * Sets the expected duration.
	 *
	 * @param expectedDuration the expectedDuration to set
	 */
	public void setExpectedDuration(String expectedDuration) {
		this.expectedDuration = expectedDuration;
	}

	/**
	 * Gets the allowed amount.
	 *
	 * @return the allowedAmount
	 */
	public String getAllowedAmount() {
		return allowedAmount;
	}

	/**
	 * Sets the allowed amount.
	 *
	 * @param allowedAmount the allowedAmount to set
	 */
	public void setAllowedAmount(String allowedAmount) {
		this.allowedAmount = allowedAmount;
	}

	/**
	 * Gets the extra details.
	 *
	 * @return the extraDetails
	 */
	public String getExtraDetails() {
		return extraDetails;
	}

	/**
	 * Sets the extra details.
	 *
	 * @param extraDetails the extraDetails to set
	 */
	public void setExtraDetails(String extraDetails) {
		this.extraDetails = extraDetails;
	}

	/**
	 * @return the submitDate
	 */
	public Date getSubmitDate() {
		return submitDate;
	}

	/**
	 * @param submitDate the submitDate to set
	 */
	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	/**
	 * @return the status
	 */
	public TicketStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(TicketStatus status) {
		this.status = status;
	}

	/**
	 * @return the files
	 */
	public byte[] getFiles() {
		return files;
	}

	/**
	 * @param files the files to set
	 */
	public void setFiles(byte[] files) {
		this.files = files;
	}

	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Ticket [ticketId=" + ticketId + ", employee=" + employee.getUsername() + ", requestType=" + requestType
				+ ", priority=" + priority + ", travelCity=" + travelCity + ", from=" + from + ", startDate="
				+ startDate + ", endDate=" + endDate + ", passportNumber=" + passportNumber + ", projectName="
				+ projectName + ", borneBy=" + borneBy + ", approver=" + approver + ", expectedDuration="
				+ expectedDuration + ", allowedAmount=" + allowedAmount + ", extraDetails=" + extraDetails
				+ ", submitDate=" + submitDate + ", status=" + status + ", files=" + Arrays.toString(files)
				+ ", remarks=" + remarks + "]";
	}

}