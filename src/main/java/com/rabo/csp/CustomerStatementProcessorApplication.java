package com.rabo.csp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * CustomerStatementProcessorApplication also abbreviated as CSP
 * is the monthly statement processor to reconcile existing customer monthly statement
 * @author Parthasarathi Sahoo (partha.sahoo1994@gmail.com)
 */
@SpringBootApplication
public class CustomerStatementProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerStatementProcessorApplication.class, args);
	}
}
