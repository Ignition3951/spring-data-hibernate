package com.utk.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utk.entity.Address;
import com.utk.entity.User;
import com.utk.repo.AddressRepository;
import com.utk.repo.UserRepository;

@Service
public class TestService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Transactional
	public void storeLoadEntities() {
//		Address address = new Address("Flowers Street", "01246", "Boston");
//		addressRepository.save(address);

		User john = new User();
		john.setUsername("JOHN SMITH");
		Address address = new Address("Flowers Street", "01246", "Boston", john);
		john.setAddress(address);


		userRepository.save(john);
	}
}
