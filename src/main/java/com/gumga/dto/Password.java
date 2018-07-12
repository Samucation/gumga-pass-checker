package com.gumga.dto;

import org.apache.log4j.Logger;

public class Password {
	private static final Logger log = Logger.getLogger(Password.class);
	
	private String password;
	private Long score = 0l;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getScore() {
		return score >= 100 ? 100 : score <= 0 ? 0 :score;
	}
	public void setScore(Long score) {
		this.score = score;
	}
	public Complexity getComplexity() {
		return Complexity.getComplexityFromScore(this.score);
	}
	
	public void add(long value) {
		System.out.println("score " + this.score + " add =" + value);
		score += value;
	}
	public void sub(long value) {
		System.out.println("score " + this.score + " sub =" + value);
		score -= value;
	}
}
