package firma.RestServicesStart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

@ComponentScan("firma")

@SpringBootApplication
public class RestServicesStart {
    public static void main(String[] args){
        SpringApplication.run(RestServicesStart.class,args);
    }
}
