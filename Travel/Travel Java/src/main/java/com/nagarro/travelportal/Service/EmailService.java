package com.nagarro.travelportal.Service;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.nagarro.travelportal.model.Employee;

/**
 * The Class EmailService sends email.
 */
@Service
public class EmailService {

	/** The log. */
	private Logger log = Logger.getLogger(EmailService.class);

	/** The mail sender. */
	@Autowired
	JavaMailSender mailSender;

	/** The pswd service. */
	@Autowired
	PasswordService pswdService;

	/** The emp service. */
	@Autowired
	EmployeeService empService;

	/**
	 * Send email.
	 *
	 * @param ToEmail the to email
	 * @param text    the text
	 */
	@Async
	public void sendEmail(String ToEmail, String text) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		String subject = "Nagarro Travel Portal Information";
		simpleMailMessage.setTo(ToEmail);
		simpleMailMessage.setText(text + "");
		simpleMailMessage.setSubject(subject);
		mailSender.send(simpleMailMessage);

	}

	/**
	 * Welcome mail.
	 *
	 * @param emailAddress the email address
	 * @return true, if successful
	 */
	public String welcomeMail(String emailAddress) {

		// generate password
		String password = pswdService.generatePassword();

		StringBuffer text = new StringBuffer();
		text.append("Greetings for the day!").append("\n" + "\n").append("Welcome to Nagarro Travel Portal")
				.append("\n" + "\n" + "\n").append("Login Credentials for your account are:").append("\n" + "\n")
				.append("Username: " + emailAddress).append("\n").append("Password: " + password)
				.append("\n" + "\n" + "\n").append("Regards!").append("\n").append("Nagarro Travel Team");

		sendEmail(emailAddress, text.toString());
		log.info("Welcome Mail Sent Succesfully.");
		return password;
	}

	/**
	 * Gets the mail with credentials.
	 *
	 * @param email the email
	 * @return the mail with credentials
	 */
	public boolean getMailWithCredentials(String email) {
		try {
			Employee employee = empService.getEmployeeByUsername(email);

			StringBuffer text = new StringBuffer();
			text.append("Greetings for the day!").append("\n" + "\n" + "\n")
					.append("Login Credentials for your account are:").append("\n" + "\n")
					.append("Username: " + employee.getUsername()).append("\n")
					.append("Password: " + employee.getPasswordAsString()).append("\n" + "\n" + "\n").append("Regards!")
					.append("\n").append("Nagarro Travel Team");

			sendEmail(email, text.toString());
			log.info("Mail with username and password has been sent");
			return true;
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return false;
	}

	/**
	 * Gets the credentialsfor admin.
	 *
	 * @param emailAddress the email address
	 * @return the credentialsfor admin
	 */
	public boolean getCredentialsforAdmin(String emailAddress) {

		StringBuffer text = new StringBuffer();
		text.append("Greetings for the day!").append("\n" + "\n" + "\n")
				.append("Login Credentials for your account are:").append("\n" + "\n")
				.append("Username: " + emailAddress).append("\n").append("Password: " + "1234")
				.append("\n" + "\n" + "\n").append("Regards!").append("\n").append("Nagarro Travel Team");

		sendEmail("anamikalbsim@gmail.com", text.toString());
		log.info("Mail with username and password has been sent");
		return true;
	}

}
