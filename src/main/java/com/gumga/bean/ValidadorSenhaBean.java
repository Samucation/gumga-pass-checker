package com.gumga.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import com.gumga.dto.Complexity;
import com.gumga.dto.Password;
import com.gumga.services.ValidadorService;

@ManagedBean
@ViewScoped
public class ValidadorSenhaBean implements Serializable{

	private static final long serialVersionUID = 177378530333999819L;
	private static final Logger log = Logger.getLogger(ValidadorSenhaBean.class);
	private String score;
	private String complexity;
	private String cssComplexity;
	private String password;
	private ValidadorService service;
	
	@PostConstruct
	private void initParameters() {
		password = "";
		score = "0";
		complexity = "Muito curto";
		service = new ValidadorService();
		cssComplexity = returnColorStatusPassword(complexity);
	}	
	
	public void handleKeyEvent(){
		checkPassword(password);
		cssComplexity = returnColorStatusPassword(complexity);
	}
	
	public void checkPassword(String inputPassword){
		Password p = service.checkPassword(inputPassword);
		this.score = p.getScore().toString();
		this.complexity = p.getComplexity().getDescricao();
	}
	
	/**Retorna CSS do Password complexity */
	private String returnColorStatusPassword(String statusPassword) {
		if ("Muito curto".equalsIgnoreCase(this.complexity)) {
			return "#d9534f";
		}
		return Complexity.getCssByComplexity(this.complexity);
	}	
	
	public String getCssComplexity() {
		return cssComplexity;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String inputPassword) {
		this.password = inputPassword;
	}
	
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	
	public String getComplexity() {
		return complexity;
	}
	public void setComplexity(String complexity) {
		this.complexity = complexity;
	}	
}
