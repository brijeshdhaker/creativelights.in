/**
 *
 * @author brijeshdhaker
 */
package com.org.onclick;

import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringCloudApplication {

    private static Logger log = LoggerFactory.getLogger(SpringCloudApplication.class);

    @Autowired
    private DataSource dataSource;
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    public static void main(String[] args) {
        String APP_ENV = System.getProperty("APP_ENV");
        if(APP_ENV == null || APP_ENV.isEmpty()){
           APP_ENV = "LOCAL"; 
        }
        System.out.println("Application Environment : " + APP_ENV );
        SpringApplication.run(SpringCloudApplication.class, args);
    }
    
}
