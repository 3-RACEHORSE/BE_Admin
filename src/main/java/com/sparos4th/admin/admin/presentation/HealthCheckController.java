package com.sparos4th.admin.admin.presentation;

import com.sparos4th.admin.common.SuccessResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HealthCheckController {

    @GetMapping("/health-check")
    public SuccessResponse<Object> healthCheck() {
        return new SuccessResponse<>(null);
    }
}
