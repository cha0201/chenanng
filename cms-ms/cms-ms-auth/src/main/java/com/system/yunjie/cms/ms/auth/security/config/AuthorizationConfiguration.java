package com.system.yunjie.cms.ms.auth.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
@ConfigurationProperties("oauth2-client")
public class AuthorizationConfiguration {
    private List<ClientConfiguration> clients;

    public void setClients(final List<ClientConfiguration> clients) {
        this.clients = clients;
    }

    public List<ClientConfiguration> getClients() {
        return clients;
    }

    public static class ClientConfiguration {
        private String clientId;
        private String secret;
        private String[] scopes;
        private String[] grantTypes;
        private int accessTokenValiditySeconds;
        private int refreshTokenValiditySeconds;

        public void setClientId(final String clientId) {
            this.clientId = clientId;
        }

        public void setSecret(final String secret) {
            this.secret = secret;
        }

        public void setScopes(final String[] scopes) {
            this.scopes = Arrays.copyOf(scopes, scopes.length);
        }

        public void setGrantTypes(final String[] grantTypes) {
            this.grantTypes = Arrays.copyOf(grantTypes, grantTypes.length);
        }

        public void setAccessTokenValiditySeconds(final int accessTokenValiditySeconds) {
            this.accessTokenValiditySeconds = accessTokenValiditySeconds;
        }

        public void setRefreshTokenValiditySeconds(final int refreshTokenValiditySeconds) {
            this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
        }

        public String getClientId() {
            return clientId;
        }

        public String getSecret() {
            return secret;
        }

        public String[] getScopes() {
            if (scopes == null) {
                return null;
            }
            return Arrays.copyOf(scopes, scopes.length);
        }

        public int getAccessTokenValiditySeconds() {
            return accessTokenValiditySeconds;
        }

        public int getRefreshTokenValiditySeconds() {
            return refreshTokenValiditySeconds;
        }

        public String[] getGrantTypes() {
            if (grantTypes == null) {
                return null;
            }
            return Arrays.copyOf(grantTypes, grantTypes.length);
        }
    }
}
