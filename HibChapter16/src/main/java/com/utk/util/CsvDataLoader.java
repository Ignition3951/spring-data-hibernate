package com.utk.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.context.annotation.Bean;

import com.utk.entity.Auction;
import com.utk.entity.User;

public class CsvDataLoader {

	@Bean
	public Auction buildAuctionFromCsv() throws FileNotFoundException, IOException {
		Auction auction = new Auction("1234", 20);
		try (BufferedReader bufferedReader = new BufferedReader(
				new FileReader("src/main/resources/users_information.csv"))) {
			String line = null;
			do {
				line = bufferedReader.readLine();
				if (line != null) {
					User user = new User(line);
					user.setRegistered(false);
					auction.addUser(user);
				}
			} while (line != null);
		}
		return auction;
	}
}
