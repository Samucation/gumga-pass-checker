package com.gumga.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class Log4jInit extends HttpServlet {

	private static final long serialVersionUID = -7241360743518985990L;
	private static final Logger log = Logger.getLogger(Log4jInit.class);

	public void init() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();

		System.setProperty("my.logsDir", "PasswordStrengthChecker - " + dateFormat.format(date));

		String prefix = getServletContext().getRealPath("/");
		String fileLog4JConfig = getInitParameter("log4j-init-file");

		DOMConfigurator.configure(prefix + fileLog4JConfig);
		log.debug("Configurações encontradas para o Log4J. [DESENVOLVIMENTO]");
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) {

	}
}