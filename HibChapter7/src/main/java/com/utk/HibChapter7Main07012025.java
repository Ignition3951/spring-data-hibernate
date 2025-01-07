package com.utk;

import java.util.List;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.utk.entity.BankAccount;
import com.utk.entity.CreditCard;
import com.utk.repo.BankAccountRepository;
import com.utk.repo.CrediCardRepository;

@SpringBootApplication
public class HibChapter7Main07012025 {

	public static void main(String[] args) {
		SpringApplication.run(HibChapter7Main07012025.class, args);
	}

	@Bean
	public ApplicationRunner configure(CrediCardRepository cardRepository, BankAccountRepository accountRepository) {
		return env -> {
			CreditCard creditCard = new CreditCard("John Smith", "123456789", "10", "2030");
			cardRepository.save(creditCard);
			BankAccount bankAccount = new BankAccount("Mike Johnson", "12345", "Delta Bank", "BANKXY12");
			accountRepository.save(bankAccount);
			List<CreditCard> creditCards = cardRepository.findByOwner("John Smith");
			List<BankAccount> bankAccounts = accountRepository.findByOwner("Mike Johnson");
			List<CreditCard> creditCards2 = cardRepository.findByExpYear("2030");
			List<BankAccount> bankAccounts2 = accountRepository.findBySwift("BANKXY12");
			creditCards.forEach(System.out::println);
			bankAccounts.forEach(System.out::println);
			creditCards2.forEach(System.out::println);
			bankAccounts2.forEach(System.out::println);
		};

	}
}
