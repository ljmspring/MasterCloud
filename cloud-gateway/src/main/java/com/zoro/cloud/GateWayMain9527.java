package com.zoro.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Zoro
 * @date 2024年03月13
 */
@SpringBootApplication
// 服务注册与发现
@EnableDiscoveryClient
public class GateWayMain9527 {
	public static void main(String[] args) {
		SpringApplication.run(GateWayMain9527.class, args);
	}
}