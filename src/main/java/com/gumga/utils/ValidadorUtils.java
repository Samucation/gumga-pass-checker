package com.gumga.utils;

import java.util.Set;

public class ValidadorUtils {
	
	public static void sequencial(String substr, Set<String> seq) {
		if (substr.length() < 3) {
			return;
		}
	
		int a = substr.codePointAt(0);
		int b = substr.codePointAt(1);
		int c = substr.codePointAt(2);
		
		if (Math.abs(a - c) == 2 && Math.abs(a - b) == 1){
			String str = substr.substring(0, 3);
			if (a > c) {
				str = substr.substring(2, 3) + substr.substring(1, 2) + substr.substring(0, 1);
			}
			seq.add(str);
		}
		sequencial(substr.substring(1), seq);
	}
	
	public static long consecutivo(String substr, String regex) {
		if (substr.length() < 2) {
			return 0;
		}
	
		if (substr.substring(0, 2).matches(regex)){
			return 1 + consecutivo(substr.substring(1), regex);
		}
		return consecutivo(substr.substring(1), regex);
	}

}
