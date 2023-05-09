package com.example.instacookjava;

import com.example.instacookjava.bootstrap.DataLoader;
import com.example.instacookjava.models.*;
import com.example.instacookjava.models.security.Authority;
import com.example.instacookjava.repositories.*;
import com.example.instacookjava.repositories.security.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Date;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class InstaCookJavaApplication implements CommandLineRunner {

	@Autowired
	private AuthorityRepository authorityRepository;

	//private PasswordEncoder passwordEncoder;
	//private DataLoader dataLoader;
//	@Autowired
//	private PasswordEncoder passwordEncoder;

	//@Bean
	public PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


	//	@Bean
//	DataLoader dataLoader(){
//		return new DataLoader(this.authorityRepository, this.userRepository, new PasswordEncoder());
//	}
//	@Autowired
//	private PasswordEncoder passwordEncoder;
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
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
		Authority adminRole = authorityRepository.save(Authority.builder().role("ROLE_ADMIN").build());
		Authority guestRole = authorityRepository.save(Authority.builder().role("ROLE_GUEST").build());


		User u1 = new User("Ursu", "Claudia", "claudia.ursu@yahoo.com", passwordEncoder.encode("parola123"), "Romania", "0737526240");
		User u2 = new User("Popescu", "Ana", "claudia.ursu2@yahoo.com",  passwordEncoder.encode("aaaa"), "Romania", "0737a26241");

		u1.setAccountNotExpired(true);
		u1.setEnabled(true);
		u1.setAccountNotLocked(true);
		u1.setCredentialsNotExpired(true);
		u1.setAuthorities( new HashSet<>(Arrays.asList(adminRole)));

		u2.setAccountNotExpired(true);
		u2.setEnabled(true);
		u2.setAccountNotLocked(true);
		u2.setCredentialsNotExpired(true);
		u2.setAuthorities( new HashSet<>(Arrays.asList(guestRole)));


		userRepository.save(u1);
		userRepository.save(u2);



		Collection col1 = new Collection("My Deserts", "This is how I do my deserts", false, "");
		Collection col2 = new Collection("Fast foods in my style", "A few fast burgers and shaorma ideas", false, "");
		col1.setUser(u1);
		col2.setUser(u2);
		collectionRepository.save(col1);
		collectionRepository.save(col2);

		Recipe recipe1 = new Recipe("Tiramisu", "mascarpone, cafea, piscoturi, ou", "Se face crema de mascarpone cu oul. Se dau piscoturile prin cafea si se construieste prajitura", "", "", false);
		recipe1.setCollection(col1);
		recipeRepository.save(recipe1);

		Kitchen kitchen = new Kitchen("Mexican Kitchen", "Tacos and Tacos", "Mexic", "kitchens/mexican_kitchen.jpg");
		Kitchen kitchen2 = new Kitchen("Romanian Kitchen", "Sarmale & cozonaci", "Romania", "kitchens/romanian_kitchen3.jpg");
		Kitchen kitchen3 = new Kitchen("Chinese Kitchen", "Chinese foods and tips&tricks", "China", "kitchens/chinese_kitchen2.jpg");
		kitchenRepository.save(kitchen);
		kitchenRepository.save(kitchen2);
		kitchenRepository.save(kitchen3);

		Comment comment = new Comment("What a good recipe", new Date(2022, 2, 3));
		comment.setRecipe(recipe1);
		comment.setUser(u1);
		commentRepository.save(comment);

		Contest contest = new Contest("Best Deserts", new Date(2022, 3, 2), new Date(2022, 3, 6), true, 100);
		contestRepository.save(contest);
	}
}
