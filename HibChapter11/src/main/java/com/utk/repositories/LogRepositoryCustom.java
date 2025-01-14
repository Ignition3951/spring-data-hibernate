package com.utk.repositories;

public interface LogRepositoryCustom {

	void log(String message);

	void showLogs();

	void addSeparateLogsNotSupported();

	void addSeparateLogsSupports();

}
