package sbz.cardiagnosticbe;

import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sbz.cardiagnosticbe.model.Authority;
import sbz.cardiagnosticbe.model.User;
import sbz.cardiagnosticbe.service.UserService;

import java.util.HashSet;

@SpringBootApplication
public class CardiagnosticBeApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	@Override
	public void run(String... args) {

		if (userService.findByUsername("admin") == null) {
			User user = new User();
			user.setUsername("admin");
			user.setPassword("admin");
			user.setAuthority(Authority.ADMIN);

			userService.register(user);
		}
	}

	@Bean
	public KieContainer kieContainer() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("sbnz.integracija", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
		KieScanner kScanner = ks.newKieScanner(kContainer);
		kScanner.start(10_000);
		return kContainer;
	}

	public static void main(String[] args) {
		SpringApplication.run(CardiagnosticBeApplication.class, args);
	}

}
