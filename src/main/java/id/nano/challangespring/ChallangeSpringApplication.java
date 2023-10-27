package id.nano.challangespring;

import id.nano.challangespring.controller.Menu;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallangeSpringApplication {
    public static void main(String[] args) {

        Menu app = new Menu();
        app.showMenu();

    }

}
