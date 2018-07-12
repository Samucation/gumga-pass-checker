package com.gumga.services;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.gumga.dto.Password;
import com.gumga.utils.ValidadorUtils;

public class NumberChecker {
	
	private Password passwordDto;
	
	public NumberChecker(Password p) {
		assert p != null : "Password nao pode ser vazio";
		this.passwordDto = p;
	}
	
	public Password check() {
		this.calculaTotalNumeros();
		this.calculaSomenteNumeros();
		this.calculaNumerosConsecutivos();
		this.calculaNumerosSequenciais();
		return this.passwordDto;
	}
	
	private void calculaTotalNumeros() {
		System.out.println("calculaTotalNumeros");
		int totalDigitos = StringUtils.getDigits(this.passwordDto.getPassword()).length();
		int totalCaracteres = this.passwordDto.getPassword().length();		
		
		if (totalDigitos > 0 && totalDigitos < totalCaracteres) {
			this.passwordDto.add(Long.valueOf(totalDigitos * 4).intValue());
		}		
	}
	
	private void calculaSomenteNumeros() {
		System.out.println("calculaSomenteNumeros");
		int total = StringUtils.getDigits(this.passwordDto.getPassword()).length();
		if (this.passwordDto.getPassword().length() > 0 && this.passwordDto.getPassword().length() == total) {
			this.passwordDto.sub(total);
		}
	}
	
	private void calculaNumerosConsecutivos() {
		System.out.println("calculaNumerosConsecutivos");
		long total = totalNumerosConsecutivos(this.passwordDto.getPassword());
		if (total > 0 ) {
			this.passwordDto.sub(Long.valueOf(total * 2).intValue());
		}
	}
	
	private void calculaNumerosSequenciais() {
		System.out.println("calculaNumerosSequenciais");
		long total = totalNumerosSequenciais(this.passwordDto.getPassword());
		if (total > 0) {
			this.passwordDto.sub(Long.valueOf(total * 3).intValue());
		}
	}

	public static int totalNumerosMeio(String password) {
		if (StringUtils.isNotBlank(password) && password.length() >= 3) {
			return StringUtils.getDigits(password.substring(1, password.length() -1)).length();
		}
		return 0;
	}
	
	public static long totalNumerosSequenciais(String password) {
		if (StringUtils.getDigits(password).length() < 3) {
			return 0;
		}
		
		Set<String> seq = new HashSet<>();
		String[] b = StringUtils.splitByCharacterType(password);
		for (int i = 0; i < b.length; i++) {
			if (StringUtils.isNumeric(b[i])) {
				if (b[i].length() >= 3) {
					ValidadorUtils.sequencial(b[i], seq);
				}
			}
		}
		return seq.size();
	}	
	
	public static long totalNumerosConsecutivos(String password) {
		if (StringUtils.getDigits(password).length() < 2) {
			return 0;
		}
		
		return ValidadorUtils.consecutivo(password, "[0-9]{2}");
	}	
	
}
