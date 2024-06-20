package br.com.everdev.nameresolutionispserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.Map;

@RestController
@RequestMapping("/perfil")
public class ISPController {

    @Autowired
    private WebClient.Builder webClientBuilder;

    private String getProfileAppUrl() {

        String dnsServerUrl = "http://localhost:8761";
        String profileAppUrl = webClientBuilder.build()
                .get()
                .uri(dnsServerUrl + "/dns/resolve?appName=profile-app")
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return profileAppUrl;
    }

    @GetMapping("/obterArquivo/{filename}")
    public Mono<String> obterArquivo(@PathVariable String filename) {

        String profileAppUrl = getProfileAppUrl();
        return webClientBuilder.build()
                .get()
                .uri(profileAppUrl + "/profile-app/obterArquivo/" + filename)
                .retrieve()
                .bodyToMono(String.class);
    }

    @PostMapping("/salvarArquivo/{filename}")
    public Mono<String> salvarArquivo(@PathVariable String filename, @RequestBody Map<String, String> payload) {

        String profileAppUrl = getProfileAppUrl();
        return webClientBuilder.build()
                .post()
                .uri(profileAppUrl + "/profile-app/salvarArquivo/" + filename)
                .bodyValue(payload)
                .retrieve()
                .bodyToMono(String.class);
    }
}
