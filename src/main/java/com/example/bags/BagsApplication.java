package com.example.bags;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@SpringBootApplication
public class BagsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BagsApplication.class, args);
	}


}
