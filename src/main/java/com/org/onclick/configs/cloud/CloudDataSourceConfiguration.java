package com.org.onclick.configs.cloud;

/**
 *
 * @author brijeshdhaker
 */
import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("cloud")
public class CloudDataSourceConfiguration {

    @Bean
    public Cloud cloud() {
        return new CloudFactory().getCloud();
    }

    @Bean
    @ConfigurationProperties("datasource")
    public DataSource dataSource() {
        return cloud().getSingletonServiceConnector(DataSource.class, null);
    }

}
