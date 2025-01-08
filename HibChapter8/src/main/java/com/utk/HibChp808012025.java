package com.utk;

import java.util.List;
import java.util.Set;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import com.utk.config.HIbChapter8Config;
import com.utk.entity.Item;
import com.utk.repo.ItemRepository;

@SpringBootApplication
public class HibChp808012025 {

	public static void main(String[] args) {
		SpringApplication.run(HibChp808012025.class, args);
	}

	@Bean
	public ApplicationRunner configure(ItemRepository itemRepository) {
		return env -> {
			AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext(
					HIbChapter8Config.class);
			for (String name : configApplicationContext.getBeanDefinitionNames()) {
				System.out.println("NAME : " + name);
			}
			Item item = new Item();
			item.add("background.jpg");
			item.add("foreground.jpg");
			item.add("landscape.jpg");
			item.add("portrait.jpg");

			itemRepository.save(item);

			Item item1 = itemRepository.findItemWithImages("1000");

			List<Item> items = itemRepository.findAll();
			Set<String> itemNative = itemRepository.findImagesNative(item.getId());

			System.out.println(" Size of Item1 : " + item1.getImages().size());
			System.out.println("Size of items " + items.size());
			System.out.println("Size of itemNative " + itemNative.size());

		};
	}

}
