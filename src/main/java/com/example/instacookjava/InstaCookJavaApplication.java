package com.example.instacookjava;

import com.example.instacookjava.models.Collection;
import com.example.instacookjava.models.User;
import com.example.instacookjava.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InstaCookJavaApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CollectionRepository collectionRepository;
	@Autowired
	private RecipeRepository recipeRepository;
	@Autowired
	private KitchenRepository kitchenRepository;
	@Autowired
	private ContestRepository contestRepository;
	@Autowired
	private CommentRepository commentRepository;

	public static void main(String[] args) {
		SpringApplication.run(InstaCookJavaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User u1 = new User("Ursu", "Claudia", "claudia.ursu@yahoo.com", "Romania", "0737526240");
		User u2 = new User("Popescu", "Ana", "popescu.ana@yahoo.com", "Romania", "0737a26241");

		userRepository.save(u1);
		userRepository.save(u2);

		Collection col1 = new Collection("My Deserts", "This is how I do my deserts", false, "");
		Collection col2 = new Collection("Fast foods in my style", "A few fast burgers and shaorma ideas", false, "");
		collectionRepository.save(col1);
		collectionRepository.save(col2);

	}
}
