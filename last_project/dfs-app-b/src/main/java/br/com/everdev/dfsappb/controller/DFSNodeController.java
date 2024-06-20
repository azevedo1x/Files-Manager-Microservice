package br.com.everdev.dfsappb.controller;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/dfs/node")
public class DFSNodeController {

    @GetMapping("/obterArquivo/{filename}")
    public Mono<String> obterArquivo(@PathVariable String filename) {

        return Mono.just("Conteúdo do arquivo " + filename);
    }

    @PostMapping("/salvarArquivo/{filename}")
    public Mono<String> salvarArquivo(@PathVariable String filename, @RequestBody String content) {
      
        return Mono.just("Arquivo " + filename + " salvo com sucesso");
    }
}