package com.nagarro.travelportal.Service;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.stereotype.Service;



/**
 * The Class PasswordService.
 * Generates random passwords.
 */
@Service
public class PasswordService {
	
	
	
	/**
	 * Generate password.
	 *
	 * @return the string
	 */
	public String generatePassword() {
		CharacterRule alphabets = new CharacterRule(EnglishCharacterData.Alphabetical);
	      CharacterRule digits = new CharacterRule(EnglishCharacterData.Digit);
	      PasswordGenerator passwordGenerator = new PasswordGenerator();
	      String password = passwordGenerator.generatePassword(8, alphabets, digits);
	      return password;
	}
	
	
}
