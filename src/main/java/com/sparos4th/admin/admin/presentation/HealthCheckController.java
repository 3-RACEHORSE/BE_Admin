package com.sparos4th.admin.admin.presentation;

import com.sparos4th.admin.common.SuccessResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment-service")
public class HealthCheckController {

    @GetMapping("/temp/health")
    public SuccessResponse<Object> heathCheck() {
        return new SuccessResponse<>(null);
    }
}
