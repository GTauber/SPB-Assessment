package spb.cloud.vehicles.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * \brief Controller for handling status-related requests.
 *
 * This controller provides endpoints for checking the status of the application.
 * The only purpose of this controller is to show mastery with git branching -> question 8 of Assessment.
 */

@RestController
@RequestMapping("/api/status")
@Slf4j
public class StatusController {

    @GetMapping
    public ResponseEntity<String> getStatus() {
        log.info("[Request received - Get Status]");
        return ResponseEntity.ok("Healthy");
    }


}
