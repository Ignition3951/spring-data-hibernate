package com.utk;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.utk.config.SpringConfiguration;
import com.utk.dao.GenericDao;
import com.utk.entity.Bid;
import com.utk.entity.Item;
import com.utk.service.DatabaseService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { SpringConfiguration.class })
public class SpringJpaTest {

	private static Logger logger = LoggerFactory.getLogger(SpringJpaTest.class);

	@Autowired
	private DatabaseService databaseService;

	@Autowired
	private GenericDao<Item> itemDao;

	@Autowired
	private GenericDao<Bid> bidDao;

	@BeforeEach
	public void setup() {
		databaseService.init();
	}

	@Test
	public void testInsertItems() {
		List<Item> items = itemDao.getAll();
		List<Bid> bids = bidDao.getAll();
		logger.info("BIGDECIMAL printing: {}", new BigDecimal(30000).setScale(2, RoundingMode.HALF_EVEN));
		assertAll(
				()-> assertNotNull(items),
				()->assertEquals(10, items.size()),
				() -> assertNotNull(itemDao.findByProperty("name", "Item 1")),
				()->assertNotNull(bids),
				()->assertEquals(20, bids.size()),
				() -> assertEquals(10, bidDao.findByProperty("amount", new BigDecimal(1000)).size()),
				() -> assertEquals(new BigDecimal(30000).setScale(2, RoundingMode.HALF_EVEN), bidDao.sumOfAmount())
				);
		
	}

	@Test
	public void testDeleteItems() {
		itemDao.delete(itemDao.findByProperty("name", "Item 2").get(0));
		assertEquals(0, itemDao.findByProperty("name", "Item 2").size());
	}

	@AfterEach
	public void clear() {
		databaseService.clear();
	}
}
