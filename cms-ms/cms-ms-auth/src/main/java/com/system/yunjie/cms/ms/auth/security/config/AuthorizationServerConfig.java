package com.system.yunjie.cms.ms.auth.security.config;

import com.system.yunjie.cms.ms.auth.security.DefaultUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private TokenStore tokenStore;
    private AuthenticationManager authenticationManager;
    private DefaultUserDetailsService userDetailsService;
    private AuthorizationConfiguration authorizationConfiguration;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthorizationServerConfig(final AuthenticationManager authenticationManager,
                                     final DefaultUserDetailsService userDetailsService,
                                     final RedisConnectionFactory redisConnectionFactory,
                                     final AuthorizationConfiguration authorizationConfiguration,
                                     final PasswordEncoder passwordEncoder) {
        this.tokenStore = new RedisTokenStore(redisConnectionFactory);
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.authorizationConfiguration = authorizationConfiguration;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        InMemoryClientDetailsServiceBuilder inMemory = clients.inMemory();
        authorizationConfiguration.getClients().forEach(config -> inMemory
                .withClient(config.getClientId())
                .authorizedGrantTypes(config.getGrantTypes())
                .secret(passwordEncoder.encode(config.getSecret()))
                .scopes(config.getScopes())
                .accessTokenValiditySeconds(config.getAccessTokenValiditySeconds())
                .refreshTokenValiditySeconds(config.getRefreshTokenValiditySeconds()));
    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer
                .allowFormAuthenticationForClients()
                .checkTokenAccess("permitAll()")
                .passwordEncoder(passwordEncoder);
    }

}
