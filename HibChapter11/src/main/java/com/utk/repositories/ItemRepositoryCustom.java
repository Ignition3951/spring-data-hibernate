package com.utk.repositories;

import java.time.LocalDate;

public interface ItemRepositoryCustom {

	void addItem(String name, LocalDate creationDate);

	void checkNameDuplicate(String name);

	void addLogs();

	void showLogs();

	void addItemNoRollback(String name, LocalDate creationDate);
}
