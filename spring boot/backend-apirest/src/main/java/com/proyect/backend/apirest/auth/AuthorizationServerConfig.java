package com.proyect.backend.apirest.auth;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authenticationManager;

    @Autowired
    private InfoAdicionaltoken infoAdicionaltoken;

    // esta funcion es opcional ya que .tokeStore(tokenStore()) ya lo ahce por defecto "AuthorizationServerEndpointsConfigurer"
    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey(JwtConfig.RSA_PRIVADA);
        jwtAccessTokenConverter.setVerifierKey(JwtConfig.RSA_PUBLICA);
        return jwtAccessTokenConverter;
    }

    // permisos de nuestros endpoints, rutas de acceso
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()") // genera el token cuando se autentica
                .checkTokenAccess("isAuthenticated()"); // validar el token
    }

    // 
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient("angularapp") // se indica el id de la aplicacion o cliente
               .secret(passwordEncoder.encode("secret")) // secret o contraseña
               .scopes("read", "write") // alcance, permisos que se le dan al cliente o aplicacion
               .authorizedGrantTypes("password", "refresh_token") // tipo de concesion del token, tipo de autenticacion, como se obtiene el token, 
               // usamos password cuando es con credenciales, cuando los usuarios existen en el backend
               // concecion refresh token es para un token nuevo
               .accessTokenValiditySeconds(3600) // tiempo en que caduca el token (en segundos)
               .refreshTokenValiditySeconds(3600); // tiempo de expiracion del refresh token
    }

    // se encarga de todo el proceso de autenticación y de validar el token
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(infoAdicionaltoken, accessTokenConverter()));
        endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore()).accessTokenConverter(accessTokenConverter()).tokenEnhancer(tokenEnhancerChain);
    }

}
