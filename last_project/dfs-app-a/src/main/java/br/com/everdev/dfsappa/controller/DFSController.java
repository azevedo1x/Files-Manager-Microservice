package br.com.everdev.dfsappa.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/dfs")
public class DFSController {

    private final WebClient webClient;

    public DFSController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @GetMapping("/obterArquivo/{filename}")
    public Mono<String> obterArquivo(@PathVariable String filename) {

        String nodeUrl = getNodeUrl(filename);

        return webClient.get()
                .uri(nodeUrl + "/dfs/node/obterArquivo/" + filename)
                .retrieve()
                .bodyToMono(String.class);
    }

    @PostMapping("/salvarArquivo/{filename}")
    public Mono<String> salvarArquivo(@PathVariable String filename, @RequestBody String content) {

        String nodeUrl = getRandomNodeUrl();
        return webClient.post()
                .uri(nodeUrl + "/dfs/node/salvarArquivo/" + filename)
                .bodyValue(content)
                .retrieve()
                .bodyToMono(String.class);
    }

    private String getNodeUrl(String filename) {

        return "http://dfs-app-b";
    }

    private String getRandomNodeUrl() {

        return Math.random() < 0.5 ? "http://dfs-app-b" : "http://dfs-app-c";
    }
}
