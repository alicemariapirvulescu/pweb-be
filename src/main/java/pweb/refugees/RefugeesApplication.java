package pweb.refugees;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import pweb.refugees.config.AppProperties;

@EnableConfigurationProperties(AppProperties.class)
@SpringBootApplication
public class RefugeesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RefugeesApplication.class, args);
	}

}
