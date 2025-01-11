package com.utk.service;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utk.entity.Address;
import com.utk.entity.Bid;
import com.utk.entity.Item;
import com.utk.entity.Shipment;
import com.utk.entity.User;
import com.utk.repo.AddressRepository;
import com.utk.repo.BidRepository;
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
//		john.addItems(buyedItem);

		userRepository.save(john);
	}
}
