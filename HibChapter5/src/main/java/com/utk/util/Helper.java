package com.utk.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Helper {

	public static LocalDate tomorrow() {
		return LocalDateTime.now().toLocalDate().plusDays(1);
	}

}
