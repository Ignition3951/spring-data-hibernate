package com.utk;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.utk.entity.Item;
import com.utk.repository.ItemRepo;
import com.utk.util.Helper;

@SpringBootApplication
public class SpringDataJPATest {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJPATest.class, args);
	}

	@Bean
	public ApplicationRunner configure(ItemRepo itemRepo) {
		return env -> {
			Item item = new Item();
			item.setName("Some Item");
			item.setAuctionDate(Helper.tomorrow());

			Item item1 = new Item();
			item1.setName("Some Item");
			item1.setAuctionDate(Helper.tomorrow());

			itemRepo.save(item);
			itemRepo.save(item1);

			itemRepo.findAll().forEach(System.out::println);
		};

	}
}
