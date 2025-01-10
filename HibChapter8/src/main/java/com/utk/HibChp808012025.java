package com.utk;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import com.utk.config.HIbChapter8Config;
import com.utk.entity.Bid;
import com.utk.entity.Image;
import com.utk.entity.Item;
import com.utk.repo.BidRepository;
import com.utk.repo.ItemRepository;

@SpringBootApplication
public class HibChp808012025 {

	public static void main(String[] args) {
		SpringApplication.run(HibChp808012025.class, args);
	}

	@Bean
	public ApplicationRunner configure(ItemRepository itemRepository, BidRepository bidRepository) {
		return env -> {
			AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext(
					HIbChapter8Config.class);
			for (String name : configApplicationContext.getBeanDefinitionNames()) {
				System.out.println("NAME : " + name);
			}
			Item item = new Item();
			item.setName("FOO");
			item.addImage(new Image("background.jpg", 640, 480));
			item.addImage(new Image("foreground.jpg", 800, 600));
			item.addImage(new Image("landscape.jpg", 1024, 768));
			item.addImage(new Image("landscape.jpg", 1024, 768));

			itemRepository.save(item);

			Item item2 = itemRepository.findItemWithImages(item.getId());

			List<Item> items = itemRepository.findAll();
			Set<String> itemNative = itemRepository.findImagesNative(item.getId());

			System.out.println(" Size of Item1 : " + item2.getImages().size());
			System.out.println("Size of items " + items.size());
			System.out.println("Size of itemNative " + itemNative.size());

			Bid bid1 = new Bid(item, BigDecimal.valueOf(1000));
			Bid bid2 = new Bid(item, BigDecimal.valueOf(2000));

			item.addBids(bid1);
			item.addBids(bid2);

			bidRepository.save(bid1);
			bidRepository.save(bid2);

		};
	}

}
