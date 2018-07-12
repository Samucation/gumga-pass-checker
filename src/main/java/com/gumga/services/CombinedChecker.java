package com.gumga.services;

import org.apache.commons.lang3.StringUtils;

import com.gumga.dto.Password;

public class CombinedChecker {
	
	private Password passwordDto;
	
	public CombinedChecker(Password p) {
		assert p != null : "Password nao pode ser vazio";
		this.passwordDto = p;
	}

	public Password check() {
		this.calculaCaracteresMeio();
		this.calculaRequisitosMinimos();
		
		return this.passwordDto;
	}
	
	private void calculaCaracteresMeio() {
		System.out.println("calculaCaracteresMeio");
		long totalNumeros = NumberChecker.totalNumerosMeio(this.passwordDto.getPassword());
		long totalSimbolos = SimboloChecker.totalSimbolosMeio(this.passwordDto.getPassword());
		if (totalNumeros + totalSimbolos > 0) {
			this.passwordDto.add(Long.valueOf((totalNumeros + totalSimbolos) * 2).intValue());
		}
	}
	
	private void calculaRequisitosMinimos() {
		System.out.println("calculaRequisitosMinimos");
		int total = 0;
		boolean regracaracters = this.passwordDto.getPassword().length() >= 8;
		if (CharacterChecker.totalUpper(this.passwordDto.getPassword()) > 0) {
			total++;
		}
		if (CharacterChecker.totalLower(this.passwordDto.getPassword()) > 0) {
			total++;
		}
		if (StringUtils.getDigits(this.passwordDto.getPassword()).length() >= 1) {
			total++;
		}
		if (this.passwordDto.getPassword().replaceAll("[a-zA-Z_0-9]", "").length() > 0) {
			total++;
		}
		if (regracaracters && total >= 3) {
			this.passwordDto.add((1 + total) * 2);
		}
	}
}
