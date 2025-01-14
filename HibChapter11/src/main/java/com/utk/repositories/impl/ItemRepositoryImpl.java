package com.utk.repositories.impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.utk.entity.Item;
import com.utk.entity.Log;
import com.utk.exception.DuplicateItemNameException;
import com.utk.repositories.ItemRepository;
import com.utk.repositories.ItemRepositoryCustom;
import com.utk.repositories.LogRepository;

public class ItemRepositoryImpl implements ItemRepositoryCustom {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private LogRepository logRepository;

	@Override
	@Transactional
	public void addItem(String name, LocalDate creationDate) {
		logRepository.log("Adding item with name : " + name);
		checkNameDuplicate(name);
		itemRepository.save(new Item(name, creationDate));
	}

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public void checkNameDuplicate(String name) {
		if (itemRepository.findAll().stream().map(x -> x.getName()).filter(n -> n.equals(name)).count() > 0) {
			throw new DuplicateItemNameException("Item with name : " + name + " already exists!!!!");
		}

	}

	@Override
	@Transactional
	public void addLogs() {
		logRepository.addSeparateLogsNotSupported();
	}

	@Override
	@Transactional
	public void showLogs() {
		logRepository.showLogs();
	}

	@Override
	@Transactional(noRollbackFor = DuplicateItemNameException.class)
	public void addItemNoRollback(String name, LocalDate creationDate) {
		logRepository.save(new Log("Adding log in method with no rollback for name :" + name));
		checkNameDuplicate(name);
		itemRepository.save(new Item(name, creationDate));
	}

}
