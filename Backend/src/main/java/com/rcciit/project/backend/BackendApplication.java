package com.rcciit.project.backend;

import com.rcciit.project.backend.connect.DatabaseConfiguration;
import com.rcciit.project.backend.connect.DatabaseConnection;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);


	}


	@Override
	public void run(String... args) throws SQLException {
		String tableName = "members";
		boolean isTableCreated = DatabaseConfiguration.checkTable(tableName);

		if(! isTableCreated) {
			DatabaseConfiguration.createTable(tableName);
		}else{
			System.out.println(tableName + " is already there ! ");
		}

	}
}
