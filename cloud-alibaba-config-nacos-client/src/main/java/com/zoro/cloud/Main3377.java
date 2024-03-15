package com.zoro.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @author Zoro
 * @date 2024年03月14
 */
@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
public class Main3377 {
	public static void main(String[] args) {
		SpringApplication.run(Main3377.class, args);
	}
}