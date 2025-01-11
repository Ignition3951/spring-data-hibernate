package com.utk.service;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utk.entity.Address;
import com.utk.entity.Bid;
import com.utk.entity.CategorizedItem;
import com.utk.entity.Category;
import com.utk.entity.Item;
import com.utk.entity.Shipment;
import com.utk.entity.User;
import com.utk.repo.AddressRepository;
import com.utk.repo.BidRepository;
import com.utk.repo.CategorizedItemRepository;
import com.utk.repo.CategoryRepository;
import com.utk.repo.ItemRepository;
import com.utk.repo.ShipmentRepo;
import com.utk.repo.UserRepository;

@Service
public class TestService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private ShipmentRepo shipmentRepo;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private BidRepository bidRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CategorizedItemRepository categorizedItemRepository;

	@Transactional
	public void storeLoadEntities() {
//		Address address = new Address("Flowers Street", "01246", "Boston");
//		addressRepository.save(address);

		User john = new User();
		john.setUsername("JOHN SMITH");
		Address address = new Address("Flowers Street", "01246", "Boston");
		john.setAddress(address);

		Shipment shipment = new Shipment();
		shipmentRepo.save(shipment);
		Item item = new Item("FOO");
		itemRepository.save(item);
		Shipment shipment2 = new Shipment(item);
		shipmentRepo.save(shipment2);

		Bid bid = new Bid(item, new BigDecimal(1000));
		item.addBids(bid);
		item.addBids(bid);
		bidRepository.save(bid);

		Item buyedItem = new Item("BAR", john);
		itemRepository.save(buyedItem);

		Category someCategory = new Category("Some Category");
		Category otherCategory = new Category("Other Category");
		categoryRepository.save(someCategory);
		categoryRepository.save(otherCategory);
		Item someItem = new Item("Some Item");
		Item otherItem = new Item("Other Item");
		itemRepository.save(someItem);
		itemRepository.save(otherItem);
		CategorizedItem linkOne = new CategorizedItem("John Smith", someCategory, someItem);
		CategorizedItem linkTwo = new CategorizedItem("John Smith", someCategory, otherItem);
		CategorizedItem linkThree = new CategorizedItem("John Smith", otherCategory, otherItem);
		categorizedItemRepository.save(linkOne);
		categorizedItemRepository.save(linkTwo);
		categorizedItemRepository.save(linkThree);
//		john.addItems(buyedItem);

		userRepository.save(john);
	}
}
