package com.utk;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.IllegalTransactionStateException;

import com.utk.config.HibChapter11Config;
import com.utk.exception.DuplicateItemNameException;
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

	/*
	 * The checkNameDuplicate method can be executed only in a transaction, so an
	 * IllegalTransactionStateException will be thrown when calling it without a
	 * transaction. We also check the message from the exception MANDATORY—If a
	 * transaction is in progress, the execution will continue within that
	 * transaction. Otherwise, a TransactionRequiredException exception will be
	 * thrown
	 */
	@Test
	public void mandatory() {
		IllegalTransactionStateException ex = assertThrows(IllegalTransactionStateException.class,
				() -> itemRepository.checkNameDuplicate("Item1"));
		assertEquals("No existing transaction found for transaction marked with propagation 'mandatory'",
				ex.getMessage());
	}

	/*
	 * However, we cannot call the showLogs method from LogRepository within a
	 * transaction, as the calling method showLogs from ItemRepository is
	 * transactional
	 */
	@Test
	public void never() {
		itemRepository.addItem("Item1", LocalDate.of(2022, 5, 1));
		logRepository.showLogs();
		IllegalTransactionStateException ex = assertThrows(IllegalTransactionStateException.class,
				() -> itemRepository.showLogs());
		assertEquals("Existing transaction found for transaction marked with propagation 'never'", ex.getMessage());
	}

	/*
	 * Trying to insert a duplicate Item in the repository will throw a
	 * DuplicateItemNameException However, a log message is persisted in the logs
	 * even after exception, because it was added in a separate transaction.
	 */
	@Test
	public void requiresNew() {
		itemRepository.addItem("Item1", LocalDate.of(2022, 5, 1));
		itemRepository.addItem("Item2", LocalDate.of(2022, 3, 1));
		itemRepository.addItem("Item3", LocalDate.of(2022, 1, 1));
		DuplicateItemNameException ex = assertThrows(DuplicateItemNameException.class,
				() -> itemRepository.addItem("Item2", LocalDate.of(2016, 3, 1)));
		assertAll(() -> assertEquals("Item with name : Item2 already exists!!!!", ex.getMessage()),
				() -> assertEquals(4, logRepository.findAll().size()),
				() -> assertEquals(3, itemRepository.findAll().size()));
		System.out.println("Logs: ");
		logRepository.findAll().forEach(System.out::println);
		System.out.println("List of added items: ");
		itemRepository.findAll().forEach(System.out::println);
	}

	/*
	 * However, a log message is persisted in the logs even after the exception,
	 * because the transaction was not rolled back. The addItemNoRollback method
	 * from ItemRepository does not roll back for DuplicateItemNameException
	 */
	@Test
	public void noRollback() {
		itemRepository.addItemNoRollback("Item1", LocalDate.of(2022, 5, 1));
		itemRepository.addItemNoRollback("Item2", LocalDate.of(2022, 3, 1));
		itemRepository.addItemNoRollback("Item3", LocalDate.of(2022, 1, 1));
		DuplicateItemNameException ex = assertThrows(DuplicateItemNameException.class,
				() -> itemRepository.addItemNoRollback("Item2", LocalDate.of(2016, 3, 1)));
		assertAll(() -> assertEquals("Item with name : Item2 already exists!!!!", ex.getMessage()),
				() -> assertEquals(4, logRepository.findAll().size()),
				() -> assertEquals(3, itemRepository.findAll().size()));
		System.out.println("Logs: ");
		logRepository.findAll().forEach(System.out::println);
		System.out.println("List of added items: ");
		itemRepository.findAll().forEach(System.out::println);
	}
}
