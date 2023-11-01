package id.nano.challangespring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallangeSpringApplication {
    private static final Logger logger = LoggerFactory.getLogger(ChallangeSpringApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(ChallangeSpringApplication.class, args);
        logger.info("App Started");
    }

}
