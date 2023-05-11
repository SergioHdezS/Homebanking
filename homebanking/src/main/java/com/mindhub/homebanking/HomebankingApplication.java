package com.mindhub.homebanking;

import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {

		SpringApplication.run(HomebankingApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(
			ClientRepository clientRepository,
			AccountRepository accountRepository,
			TransactionRepository transactionRepository,
			LoanRepository loanRepository,
			ClientLoanRepository clientLoanRepository,
			CardRepository cardRepository
	) {
		return (args) -> {
			// <----- CLIENTS ----->

			Client client1 = new Client("Melba", "Morel", "melba@mindhub.com");
			clientRepository.save(client1);
			Client client2 = new Client("Tulio","Triviño", "t.triviño@31m.com");
			clientRepository.save(client2);

			// <----- ACCOUNTS ----->

			Account account1 = new Account("VIN001", LocalDateTime.now(), 5000f, client1);
			Account account2 = new Account("VIN001", LocalDateTime.now().plusDays(1), 7500f, client1);
			accountRepository.save(account1);
			accountRepository.save(account2);
			Account account3 = new Account("VIN003", LocalDateTime.now(), 750000f, client2);
			accountRepository.save(account3);

			// <----- TRANSACTIONS ----->

			Transaction ts1 = new Transaction(TransactionType.DEBIT, -7500f, "Cine Hoyts", LocalDateTime.now(), account1);
			Transaction ts2 = new Transaction(TransactionType.DEBIT, -12000f, "Netflix", LocalDateTime.now(), account1);
			Transaction ts3 = new Transaction(TransactionType.DEBIT, -4500f, "Spotify", LocalDateTime.now(), account2);
			Transaction ts4 = new Transaction(TransactionType.CREDIT, 600000f, "Sueldo", LocalDateTime.now(), account1);
			Transaction ts5 = new Transaction(TransactionType.CREDIT, 120000f, "Devolucion de impuestos", LocalDateTime.now(), account2);
			Transaction ts6 = new Transaction(TransactionType.CREDIT, -400000f, "Taller mecanico", LocalDateTime.now(), account1);

			transactionRepository.save(ts1);
			transactionRepository.save(ts2);
			transactionRepository.save(ts3);
			transactionRepository.save(ts4);
			transactionRepository.save(ts5);
			transactionRepository.save(ts6);

			// <----- LOANS ----->
			List<Integer> payments1 = List.of(12, 24, 36, 48, 60);
			List<Integer> payments2 = List.of(6, 12, 24);
			List<Integer> payments3 = List.of(6, 12, 24, 36);

			Loan hipotecario = new Loan("Hipotecario", 500000f, payments1);
			Loan personal = new Loan("Personal", 100000f, payments2);
			Loan automotriz = new Loan("Automotriz", 300000f, payments3);

			loanRepository.save(hipotecario);
			loanRepository.save(personal);
			loanRepository.save(automotriz);

			ClientLoan clientLoan1 = new ClientLoan(400000f, 60, client1, hipotecario);
			ClientLoan clientLoan2 = new ClientLoan(50000f, 12, client1, personal);
			ClientLoan clientLoan3 = new ClientLoan(100000f, 24, client2, personal);
			ClientLoan clientLoan4 = new ClientLoan(200000f, 36, client2, automotriz);

			clientLoanRepository.save(clientLoan1);
			clientLoanRepository.save(clientLoan2);
			clientLoanRepository.save(clientLoan3);
			clientLoanRepository.save(clientLoan4);

			// <----- CARDS ----->

			Card card1 = new Card(client1.getFirstName()+ " " + client1.getLastName(),CardType.DEBIT, CardColor.GOLD, 5968006790035461l, 458, LocalDateTime.now(), LocalDateTime.now().plusYears(5), client1);
			Card card2 = new Card(client1.getFirstName()+ " " + client1.getLastName(),CardType.CREDIT, CardColor.TITANIUM, 5799354401758998l, 695, LocalDateTime.now(), LocalDateTime.now().plusYears(5), client1);
			Card card3 = new Card(client2.getFirstName()+ " " + client2.getLastName(), CardType.CREDIT, CardColor.SILVER, 6324158600318641l, 456, LocalDateTime.now(), LocalDateTime.now().plusYears(5), client2);


			cardRepository.save(card1);
			cardRepository.save(card2);
			cardRepository.save(card3);
		};
	}
}
