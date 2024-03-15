package com.zoro.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
// import tk.mybatis.spring.annotation.MapperScan;
@MapperScan("com.zoro.cloud.mapper")
// 服务注册和发现
@EnableDiscoveryClient
@EnableFeignClients
public class SeataStorageMainApp2002 {
	public static void main(String[] args) {
		SpringApplication.run(SeataStorageMainApp2002.class, args);
	}
}