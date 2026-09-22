package Service;

import Principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Projeto implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Projeto.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        Principal principal = new Principal();

    }
}