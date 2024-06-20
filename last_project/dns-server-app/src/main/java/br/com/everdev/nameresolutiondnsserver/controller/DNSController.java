package br.com.everdev.nameresolutiondnsserver.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/dns")
public class DNSController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/resolve")
    public String resolve(@RequestParam String appName) {

        return discoveryClient.getInstances(appName).stream()
                .findFirst()
                .map(serviceInstance -> serviceInstance.getUri().toString())
                .orElseThrow(() -> new RuntimeException("Application not found: " + appName));
            }
}