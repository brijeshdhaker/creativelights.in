package com.org.onclick.configs.local;

import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 *
 * @author brijeshdhaker
 */
@Profile("local")
@Configuration
public class LocalDataSourceConfiguration {
    
    @Bean
    @ConfigurationProperties("")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }
}
