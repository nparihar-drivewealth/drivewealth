package com.drivewealth.configflow.controller;

import com.drivewealth.configflow.service.ConfigService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class DemoController {
    private final ConfigService configService;

    public DemoController(ConfigService configService) {
        this.configService = configService;
    }

    // Demo endpoint showing behavior toggled by a feature flag
    @GetMapping("/api/demo")
    public ResponseEntity<?> demo(@RequestParam(required = false) String userId) {
        boolean newBehavior = configService.isFeatureEnabled("feature.newCheckout", userId);
        Map<String, Object> resp = new HashMap<>();
        resp.put("userId", userId);
        resp.put("feature", "feature.newCheckout");
        resp.put("enabled", newBehavior);
        resp.put("result", newBehavior ? "NEW checkout flow" : "OLD checkout flow");
        return ResponseEntity.ok(resp);
    }
}
