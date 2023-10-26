package suitespot.restserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SuitespotModelApplication {

  public static void main(String[] args) {
    SpringApplication.run(SuitespotModelApplication.class, args);
  }
}


// to start server use: 
// mvn spring-boot:run 
// from suitespot