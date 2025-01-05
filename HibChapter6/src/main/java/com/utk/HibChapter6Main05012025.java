package com.utk;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.utk.config.SpringDataConfigChapter6;
import com.utk.entity.Address;
import com.utk.entity.ItemChapter6;
import com.utk.entity.MonetaryAmount;
import com.utk.entity.UserChapter6;
import com.utk.repo.ItemRepoChapter6;
import com.utk.repo.UserRepoChapter6;
import com.utk.util.Helper;

@SpringBootApplication
public class HibChapter6Main05012025 {

	public static void main(String[] args) {
		HibChapter6Main05012025 main05012025 = new HibChapter6Main05012025();
		main05012025.executeFunction();
	}

	public void executeFunction() {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
				SpringDataConfigChapter6.class);
		String[] names = applicationContext.getBeanDefinitionNames();
		for (String name : names) {
			System.out.println("NAME : " + name);
		}
		UserRepoChapter6 userRepoChapter6 = (UserRepoChapter6) applicationContext.getBean("userRepoChapter6");
		ItemRepoChapter6 itemRepoChapter6 = (ItemRepoChapter6) applicationContext.getBean("itemRepoChapter6");
		UserChapter6 userChapter6 = new UserChapter6();
		userChapter6.setUsername("Username");
		userChapter6.setHomeAddress(new Address("Flowers Street", "12345", "Boston"));

		userRepoChapter6.save(userChapter6);


		ItemChapter6 item = new ItemChapter6();
		item.setName("Some Item");
		item.setMetricWeight(2);
		item.setDescription("description");
		item.setAuctionDate(Helper.tomorrow());
		item.setBuyNowPrice(new MonetaryAmount(BigDecimal.valueOf(1.1), Currency.getInstance("USD")));
		itemRepoChapter6.save(item);
		List<UserChapter6> users = (List<UserChapter6>) userRepoChapter6.findAll();
		List<ItemChapter6> items = (List<ItemChapter6>) itemRepoChapter6.findByMetricWeight(2.0);

		users.forEach(System.out::println);
		items.forEach(System.out::println);
		items.forEach(x -> {
			System.out.println(x.getShortDescription());
			System.out.println(x.getMetricWeight());
		});
		applicationContext.close();
	}

}
