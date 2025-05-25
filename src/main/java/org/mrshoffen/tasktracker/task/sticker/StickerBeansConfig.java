package org.mrshoffen.tasktracker.task.sticker;


import org.mrshoffen.tasktracker.task.sticker.client.PermissionsClient;
import org.mrshoffen.tasktracker.task.sticker.model.dto.links.StickerDtoLinksInjector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class StickerBeansConfig {

    @Bean
    @LoadBalanced
    public WebClient.Builder loadBalancedWebClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public PermissionsClient permissionsClient(WebClient.Builder webClientBuilder) {
        return new PermissionsClient(webClientBuilder.baseUrl("http://user-permission-rs").build());
    }

    @Bean
    public StickerDtoLinksInjector stickerDtoLinksInjector(@Value("${app.gateway.api-prefix}") String apiPrefix) {
        return new StickerDtoLinksInjector(apiPrefix);
    }

}
