package com.humanup.matrix.management.api.gatway;


import com.humanup.matrix.management.api.gatway.filters.AccessLogFilter;
import com.humanup.matrix.management.api.gatway.filters.FilterLogger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class GatwayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatwayApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); // For encrypting user password
	}

	@Bean
	public FilterLogger filterLogger() {
		return new FilterLogger();

	}
	@Bean
	public AccessLogFilter acessLogger() {
		return new AccessLogFilter();

	}

}

