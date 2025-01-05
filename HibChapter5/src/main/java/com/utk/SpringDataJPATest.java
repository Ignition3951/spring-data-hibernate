package com.utk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDataJPATest {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJPATest.class, args);
	}

//	@Bean
//	public ApplicationRunner configure(ItemRepo itemRepo) {
//		return env -> {
//			Item item = new Item();
//			item.setName("Some Item");
//			item.setAuctionDate(Helper.tomorrow());
//
//			Item item1 = new Item();
//			item1.setName("Some Item");
//			item1.setAuctionDate(Helper.tomorrow());
//
//			itemRepo.save(item);
//			itemRepo.save(item1);
//
//			itemRepo.findAll().forEach(System.out::println);
//		};
//
//	}
}
