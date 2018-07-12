package com.gumga.services;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.gumga.dto.Password;
import com.gumga.utils.ValidadorUtils;

public class CharacterChecker {
	
	private Password passwordDto;
	
	public CharacterChecker(Password p) {
		assert p != null : "Password nao pode ser vazio";
		this.passwordDto = p;
	}
	
	public Password check() {
		this.calculaTotalCaracteres();
		this.calculaSomenteLetras();
		this.calculaMinusculoConsecutivo();
		this.calculaMaiusculoConsecutivo();
		this.calculaSequencia();
		this.calculaPossuiUpper();
		this.calculaPossuiLower();
		this.calculaRepeticoes();
		
		return this.passwordDto;
	}
	
	private void calculaSomenteLetras() {
		System.out.println("calculaSomenteLetras");
		if (StringUtils.isAlpha(this.passwordDto.getPassword())) {
			this.passwordDto.sub(this.passwordDto.getPassword().length());
		}
	}
	
	private void calculaMinusculoConsecutivo() {
		System.out.println("calculaMinusculoConsecutivo");
		long total = minusculoConsecutivo(this.passwordDto.getPassword());
		if (total > 0) {
			this.passwordDto.sub(Long.valueOf(total * 2).intValue());
		}
	}
	
	private void calculaMaiusculoConsecutivo() {
		System.out.println("calculaMaiusculoConsecutivo");
		long total = maiusculoConsecutivo(this.passwordDto.getPassword());
		if (total > 0) {
			this.passwordDto.sub(Long.valueOf(total * 2).intValue());
		}
	}
	
	private void calculaSequencia() {
		System.out.println("calculaSequencia");
		long total = totalSequencias(this.passwordDto.getPassword());
		if (total > 0) {
			this.passwordDto.sub(Long.valueOf(total * 3).intValue());
		}
	}
	
	private void calculaPossuiUpper() {
		System.out.println("calculaPossuiUpper");
		int totalCaracteres = this.passwordDto.getPassword().length();
		long totalUpper = totalUpper(this.passwordDto.getPassword());
		
		if (totalUpper > 0 && totalUpper < totalCaracteres) {
			this.passwordDto.add(Long.valueOf((totalCaracteres - totalUpper) * 2).intValue());
		}
	}
	
	public static long totalUpper(String password) {
		return password.codePoints().filter(c-> c>='A' && c<='Z').count();
	}
	
	private void calculaPossuiLower() {
		System.out.println("calculaPossuiLower");
		int totalCaracteres = this.passwordDto.getPassword().length();
		long totalLower = totalLower(this.passwordDto.getPassword());
		
		if (totalLower > 0 && totalLower < totalCaracteres) {
			this.passwordDto.add(Long.valueOf((totalCaracteres - totalLower) * 2).intValue());
		}
	}
	
	public static long totalLower(String password) {
		return password.codePoints().filter(c-> c>='a' && c<='z').count();
	}
	
	private void calculaRepeticoes() {
		System.out.println("calculaRepeticoes");
		long total = totalRepeticoes(this.passwordDto.getPassword());
		if (total > 0) {
			this.passwordDto.sub(Long.valueOf(total).intValue());			
		}
	}
	
	private void calculaTotalCaracteres() {
		System.out.println("calculaTotalCaracteres");
		if (this.passwordDto.getPassword().length() > 0) {
			this.passwordDto.add(this.passwordDto.getPassword().length() * 4);
		}
	}
	
	
	
	public static long totalRepeticoes(String password) {
		String[] arrayChr = StringUtils.replace(password, " ", "").split("\\s*");
		
		int length = arrayChr.length;
		int nRepChar = 0;
		int nUnqChar = 0;
		double nRepInc = 0;
		for (int i = 0; i < length; i++ ) {
			boolean b = false;
			for (int j = 0; j < length; j++) {
				if (i != j && arrayChr[i].equals(arrayChr[j])) {
					b = true;
					nRepInc += new Double(length)/Math.abs(j - i);
				}
			}
			if (b) {
				nRepChar++;
				nUnqChar = length - nRepChar;
				nRepInc = (nUnqChar > 0) ? Math.ceil(nRepInc/nUnqChar) : Math.ceil(nRepInc);
				
			}
		}
		
		return Double.valueOf(nRepInc).intValue();
	}

	
	public static long totalSequencias(String password) {
		if (password.length() >= 3) {
			Set<String> seq = new HashSet<>();
			String[] groups = StringUtils.splitByCharacterType(password.toLowerCase());
			for(int i = 0; i < groups.length; i++) {
				if (StringUtils.isAlpha(groups[i])) {
					ValidadorUtils.sequencial(groups[i], seq);
				}
			}
			return seq.size();
		}
		return 0;
	}
	
	public static long maiusculoConsecutivo(String password) {
		return password.length() < 2 ? 0 :  ValidadorUtils.consecutivo(password, "[A-Z]{2}");
	}
	
	public static long minusculoConsecutivo(String password) {
		return password.length() < 2 ? 0 : ValidadorUtils.consecutivo(password, "[a-z]{2}");

	}	
	

}
