package com.gumga.services;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.gumga.dto.Password;

public class SimboloChecker {
	private static final String sequencia = ")!@#$%^&*()";
	private Password passwordDto; 
	
	public SimboloChecker(Password p) {
		assert p != null : "Password nao pode ser vazio";
		this.passwordDto = p;
	}
	
	public Password check() {
		this.calculaTotalSimbolos();
		this.calculaSimbolosSequenciais();
		
		return this.passwordDto;
	}
	
	private void calculaTotalSimbolos() {
		System.out.println("calculaTotalSimbolos");
		int totalSimbolos = this.passwordDto.getPassword().replaceAll("[a-zA-Z_0-9]", "").length();
		if (totalSimbolos > 0) {
			this.passwordDto.add(Long.valueOf(totalSimbolos * 6).intValue());
		}
	}
	
	private void calculaSimbolosSequenciais() {
		System.out.println("calculaSimbolosSequenciais");
		long total = totalSimbolosSequenciais(this.passwordDto.getPassword());
		if (total > 0) {
			this.passwordDto.add(Long.valueOf(total * 3).intValue());			
		}
		
	}
	
	public static long totalSimbolosSequenciais(String password) {
		if (password.length() < 3) {
			return 0;
		}
		
		String str = password.replaceAll("[a-zA-Z_0-9]", ";");
		String[] groups = StringUtils.split(str, ";");
		Set<String> seq = new HashSet<>();
		for (int i = 0; i < groups.length; i++) {
			if (!StringUtils.isAlphanumeric(groups[i])) {
				if (groups[i].length() >= 3) {
					sequencial(groups[i], seq);
				}
			}
		}
		return seq.size();
	}
	
	public static long totalSimbolosMeio(String password) {
		if (StringUtils.isNotBlank(password) && password.length() >= 3) {
			String caracteresIntermediarios = password.substring(1, password.length() -1);
			return caracteresIntermediarios.replaceAll("[a-zA-Z_0-9]", "").length();
		}
		return 0;
	}
	
	
	private static void sequencial(String substr, Set<String> seq) {
		if (substr.length() < 3) {
			return;
		}
	
		int a = sequencia.indexOf(substr.charAt(0));
		int b = sequencia.indexOf(substr.charAt(1));
		int c = sequencia.indexOf(substr.charAt(2));
		
		if ((a >= 0 && b >= 0 && c >= 0) && (Math.abs(a - c) == 2 && Math.abs(a - b) == 1)){
			String str = substr.substring(0, 3);
			if (a > c) {
				str = substr.substring(2, 3) + substr.substring(1, 2) + substr.substring(0, 1);
			}
			seq.add(str);
		}
		sequencial(substr.substring(1), seq);
	}	
	
	public static long getSequentialCount(String password) {
		Set<String> seq = new HashSet<>();
		sequencial(password, seq);
		return seq.size();
	}	

}
