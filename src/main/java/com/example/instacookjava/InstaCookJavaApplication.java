package com.example.instacookjava;

import com.example.instacookjava.models.Utilizator;
import com.example.instacookjava.repositories.ColectieRepository;
import com.example.instacookjava.repositories.RetetaRepository;
import com.example.instacookjava.repositories.UtilizatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InstaCookJavaApplication implements CommandLineRunner {

	@Autowired
	private UtilizatorRepository utilizatorRepository;
//	@Autowired
//	private ColectieRepository colectieRepository;
//	@Autowired
//	private RetetaRepository retetaRepository;

	public static void main(String[] args) {
		SpringApplication.run(InstaCookJavaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Utilizator u1 = new Utilizator("Ursu", "Claudia", "claudia.ursu@yahoo.com", "Romania", "0737526240", 0);
		Utilizator u2 = new Utilizator("Popescu", "Ana", "popescu.ana@yahoo.com", "Romania", "0737a26241", 0);

		utilizatorRepository.save(u1);
		utilizatorRepository.save(u2);
	}
}
