package spb.cloud.general.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/status")
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
public class StatusController {

    @GetMapping
    public Mono<ResponseEntity<String>> getStatus() {
        log.info("[Request received - Get Status]");
        return Mono.just(ResponseEntity.ok("Healthy"));
    }

}
