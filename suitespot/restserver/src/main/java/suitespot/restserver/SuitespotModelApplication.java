package suitespot.restserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SuitespotModelApplication {

  public static void main(String[] args) {
    SpringApplication.run(SuitespotModelApplication.class, args);
  }
}


// to start server use: 
// mvn spring-boot:run -f ./springboot/restserver/pom.xml 
// from suitespot