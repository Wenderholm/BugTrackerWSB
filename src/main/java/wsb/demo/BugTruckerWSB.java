package wsb.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true)
public class BugTruckerWSB {

    public static void main(String[] args) {
        SpringApplication.run(BugTruckerWSB.class, args);
    }

}
