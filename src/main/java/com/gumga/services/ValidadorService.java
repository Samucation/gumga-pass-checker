package com.gumga.services;

import com.gumga.dto.Password;

public class ValidadorService {

	public Password checkPassword(String password) {
		Password p = new Password();
		p.setPassword(password!=null?password:"");
		
		p = new CombinedChecker(p).check();
		p = new CharacterChecker(p).check();
		p = new NumberChecker(p).check();
		p = new SimboloChecker(p).check();
		
		return p;
	}
	
}
