package home.ua.gameofthrones;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GameOfThronesApplication {
    private static final Logger log = LoggerFactory.getLogger(GameOfThronesApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(GameOfThronesApplication.class, args);

}



}
