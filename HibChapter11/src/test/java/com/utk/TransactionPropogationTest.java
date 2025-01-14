package com.utk;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.utk.config.HibChapter11Config;
import com.utk.repositories.ItemRepository;
import com.utk.repositories.LogRepository;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { HibChapter11Config.class })
public class TransactionPropogationTest {

	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private LogRepository logRepository;
	
	@BeforeEach
	public void clean() {
		itemRepository.deleteAll();
		logRepository.deleteAll();
	}
	
	/**
	 * If a transaction is in progress, it will be suspended and a non transactional
	 * execution will continue. Otherwise, the execution will simply continue.
	 */
	@Test
	public void notSupported() {
		assertAll(() -> assertThrows(RuntimeException.class, () -> itemRepository.addLogs()),
				() -> assertEquals(1, logRepository.findAll().size()),
				() -> assertEquals("Check from not supported 1", logRepository.findAll().get(0).getMessage()));
		logRepository.showLogs();
	}

	/*
	 * If a transaction is in progress, the execution will continue within that
	 * transaction. Otherwise, no transaction will be created
	 */
	@Test
	public void supports() {
		assertAll(() -> assertThrows(RuntimeException.class, () -> logRepository.addSeparateLogsSupports()),
				() -> assertEquals(1, logRepository.findAll().size()),
				() -> assertEquals("Check from supported 1", logRepository.findAll().get(0).getMessage()));
		logRepository.showLogs();
	}
}
