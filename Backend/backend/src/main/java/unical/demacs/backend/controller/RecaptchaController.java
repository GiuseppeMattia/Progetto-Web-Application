package unical.demacs.backend.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/captcha")
public class RecaptchaController {

    @Value("${recaptcha.secret}")
    private String recaptchaSecretKey;

    private final WebClient webClient;

    public RecaptchaController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://www.google.com/recaptcha/api/siteverify").build();
    }

    @PostMapping("/verify-recaptcha")
    public Mono<ResponseEntity<Map<String, Object>>> verifyRecaptcha(@RequestBody Map<String, String> request) {
        String token = request.get("token");

        if (token == null || token.isEmpty()) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Token mancante");
            return Mono.just(ResponseEntity.badRequest().body(response));
        }

        return webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("secret", recaptchaSecretKey)
                        .queryParam("response", token)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {}).map(googleResponse -> {
                    boolean success = (Boolean) googleResponse.getOrDefault("success", false);

                    if (success) {
                        return ResponseEntity.ok(googleResponse);
                    } else {
                        googleResponse.put("success", false);
                        googleResponse.put("message", "reCAPTCHA non valido");
                        return ResponseEntity.badRequest().body(googleResponse);
                    }
                });
    }
}

