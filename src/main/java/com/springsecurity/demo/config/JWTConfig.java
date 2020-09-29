package com.springsecurity.demo.config;

import com.springsecurity.demo.utils.JWTDataSource;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;


// You can also use @ConfigurationProperties(prefix="jwt.properties")
@Configuration
@PropertySources({@PropertySource(value = "classpath:jwt.properties")})
@ToString
public class JWTConfig {
    @Value("${jwt.secret}")
    private String SECRET_KEY;
    @Value("${jwt.tokenPrefix}")
    private String tokenPrefix;
    @Value("${jwt.expirationDate}")
    private Long expirationDate;

    @Bean
    public JWTDataSource getJwtDataSource() {
        JWTDataSource jwtDataSource = new JWTDataSource();
        jwtDataSource.setSecretKey(SECRET_KEY);
        jwtDataSource.setTokenPrefix(tokenPrefix);
        jwtDataSource.setExpirationDate(expirationDate);

        return jwtDataSource;
    }
}
