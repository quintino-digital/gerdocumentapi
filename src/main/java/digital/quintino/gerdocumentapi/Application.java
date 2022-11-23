package digital.quintino.gerdocumentapi;

import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetAddress;

@SpringBootApplication
public class Application implements CommandLineRunner {
	
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(Application.class);
	        application.setBannerMode(Banner.Mode.OFF);
	        application.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("GERDOCUMENT - Sistema de Gestão de Documentos (API) v1.0.0");
	}

}
