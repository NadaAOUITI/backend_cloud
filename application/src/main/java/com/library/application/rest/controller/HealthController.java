package com.library.application.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Liveness/health endpoint for AWS ALB target group health checks.
 */
@RestController
@Tag(name = "Health", description = "Load balancer health check")
public class HealthController {

    @GetMapping("/health")
    @Operation(summary = "Health check (ALB)")
    public ResponseEntity<Void> health() {
        return ResponseEntity.ok().build();
    }
}
