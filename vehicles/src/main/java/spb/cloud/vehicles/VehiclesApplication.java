package spb.cloud.vehicles;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
    servers = {
        @Server(url = "/vehicle")
    }
)
@SpringBootApplication
public class VehiclesApplication {

    public static void main(String[] args) {
        SpringApplication.run(VehiclesApplication.class, args);
    }

}
