package com.digitalmedia.users.config;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfiguration {

    @Value("http://localhost:8082")
    private String serverUrl;

    @Value("${digital-media")
    private String realm;

    @Value("microservicios")
    private String clientId;

    @Value("h1lZxa6kKaojbaq10TfCP7CoTL5xvf3Q")
    private String clientSecret;

    @Bean
    public Keycloak buildClient() {
        return KeycloakBuilder
                .builder()
                .serverUrl(serverUrl)
                .realm(realm)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .build();
    }
}
