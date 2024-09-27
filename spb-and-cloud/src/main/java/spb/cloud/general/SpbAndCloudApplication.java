package spb.cloud.general;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
    servers = {
        @Server(url = "/general")
    }
)
@SpringBootApplication
public class SpbAndCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpbAndCloudApplication.class, args);
    }

}
