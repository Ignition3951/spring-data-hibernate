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
			item.setName("FOO");
			item.add("background.jpg", "BACKGROUND");
			item.add("foreground.jpg", "FOREGROUND");
			item.add("landscape.jpg", "LANDSCAPE");
			item.add("portrait.jpg", "PORTRAIT");

			Item item1 = new Item();
			item1.setName("BAR");
			item1.add("portrait.jpg", "PORTRAIT");
			item1.add("foreground.jpg", "FOREGROUND");


			itemRepository.save(item);
			itemRepository.save(item1);

			Item item2 = itemRepository.findItemWithImages(item.getId());

			List<Item> items = itemRepository.findAll();
			Set<String> itemNative = itemRepository.findImagesNative(item.getId());

			System.out.println(" Size of Item1 : " + item2.getImages().size());
			System.out.println("Size of items " + items.size());
			System.out.println("Size of itemNative " + itemNative.size());
			System.out.println("Check the first key of sorted map : " + item2.getImages().firstKey());
//			System.out.println("Check for keys in map : "+item2.);

		};
	}

}
