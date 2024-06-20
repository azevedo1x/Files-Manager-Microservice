package main.java.br.com.everdev.nameresolutionperfil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/profile-app")
public class ProfileController {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @GetMapping("/obterArquivo/{filename}")
    public Mono<String> obterArquivo(@PathVariable String filename) {

        return webClientBuilder.build()
                .get()
                .uri("http://dfs-app-a/dfs/obterArquivo/" + filename)
                .retrieve()
                .bodyToMono(String.class);
    }

    @PostMapping("/salvarArquivo/{filename}")
    public Mono<String> salvarArquivo(@PathVariable String filename, @RequestBody String content) {
       
        return webClientBuilder.build()
                .post()
                .uri("http://dfs-app-a/dfs/salvarArquivo/" + filename)
                .bodyValue(content)
                .retrieve()
                .bodyToMono(String.class);
    }
}