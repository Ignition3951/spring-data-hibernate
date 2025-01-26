package com.utk;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import javax.persistence.NoResultException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.utk.config.SpringConfiguration;
import com.utk.dao.BidDao;
import com.utk.dao.ItemDao;
import com.utk.entity.Bid;
import com.utk.entity.Item;
import com.utk.service.DatabaseService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { SpringConfiguration.class })
public class SpringJpaTest {

	@Autowired
	private DatabaseService databaseService;

	@Autowired
	private ItemDao itemDao;

	@Autowired
	private BidDao bidDao;

	@BeforeEach
	public void setup() {
		databaseService.init();
	}

	@Test
	public void testInsertItems() {
		List<Item> items = itemDao.findAll();
		List<Bid> bids = bidDao.getAll();
		
		assertAll(
				()-> assertNotNull(items),
				()->assertEquals(10, items.size()),
				()->assertNotNull(itemDao.findByName("Item 1")),
				()->assertNotNull(bids),
				()->assertEquals(20, bids.size()),
				() -> assertEquals(10, bidDao.findByAmount("1000.00").size())
				);
		
	}

	@Test
	public void testDeleteItems() {
		itemDao.delete(itemDao.findByName("Item 2"));
		assertThrows(NoResultException.class, () -> itemDao.findByName("Item 2"));
	}

	@AfterEach
	public void clear() {
		databaseService.clear();
	}
}
