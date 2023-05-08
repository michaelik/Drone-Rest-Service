package com.droneservice;

import com.droneservice.model.Medication;
import com.droneservice.repository.MedicationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(
			MedicationRepository medicationRepository) {
		return args -> {
			Medication medication1 = Medication.builder()
					.code("04.5673")
					.name("Abilify")
					.weight(100)
					.image("/Abilify_IMG")
					.build();
			medicationRepository.save(medication1);
			Medication medication2 = Medication.builder()
					.code("04.6792")
					.name("Balsamic")
					.weight(200)
					.image("/Balsamic_IMG")
					.build();
			medicationRepository.save(medication2);
			Medication medication3 = Medication.builder()
					.code("04.7651")
					.name("Inhaler")
					.weight(300)
					.image("/Inhaler_IMG")
					.build();
			medicationRepository.save(medication3);
			Medication medication4 = Medication.builder()
					.code("04.5762")
					.name("Multivitamin")
					.weight(400)
					.image("/Multivitamin_IMG")
					.build();
			medicationRepository.save(medication4);
			Medication medication5 = Medication.builder()
					.code("04.6738")
					.name("Vitamin")
					.weight(500)
					.image("/Vitamin_IMG")
					.build();
			medicationRepository.save(medication5);
		};
	}

}
