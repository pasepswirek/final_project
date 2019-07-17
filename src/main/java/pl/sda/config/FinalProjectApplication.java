package pl.sda.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.sda.repository.UserRepository;

@SpringBootApplication
@EnableConfigurationProperties
@ComponentScan("pl.sda")
@Import(SecurityConfig.class)
@EntityScan("pl.sda.model")
@EnableJpaRepositories("pl.sda.repository")
public class FinalProjectApplication {

	public static void main(String[] args) {

		SpringApplication.run(FinalProjectApplication.class, args);

//		String password = "dba";
//		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		String encodedPassword = passwordEncoder.encode(password);
//		System.out.print(encodedPassword);

//		ApplicationContext applicationContext =
//				SpringApplication.run(FinalProjectApplication.class, args);
//
//		for (String name: applicationContext.getBeanDefinitionNames()) {
//			System.out.println(name);
//		}
	}

}
