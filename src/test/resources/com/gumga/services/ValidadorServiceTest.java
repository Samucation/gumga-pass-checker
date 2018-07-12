package com.gumga.services;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.gumga.dto.Password;

public class ValidadorServiceTest {
	
	private ValidadorService service;
	
	@Before
	public void setup() {
		this.service = new ValidadorService();
	}
	
	@Test
	public void testSenhaNumerica() {
		Password p = this.service.checkPassword("123");
		assertEquals(p.getScore().longValue(), 4);
	}
	
	@Test
	public void testSenhaComplexa() {
		Password p = this.service.checkPassword("aDRgjb$#63&H");
		assertEquals(p.getScore().longValue(), 100);
	}

	@Test
	public void testSenhaVazia() {
		Password p = this.service.checkPassword("");
		assertEquals(p.getScore().longValue(), 0);
	}

	@Test
	public void testSenhaNull() {
		Password p = this.service.checkPassword(null);
		assertEquals(p.getScore().longValue(), 0);
	}

	@Test
	public void testSenhaNumSequencial() {
		Password p = this.service.checkPassword("ab123456");
		assertEquals(p.getScore().longValue(), 54);
	}

}
