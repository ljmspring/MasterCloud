package com.zoro.cloud.controller;

import com.zoro.cloud.entities.PayDTO;
import com.zoro.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Zoro
 * @date 2024年03月12
 */
@RestController
public class OrderController {


	// public static final String PAYMENT_SERVER_URL = "http://localhost:8001";

	public static final String PAYMENT_SERVER_URL = "http://cloud-payment-service";

	@Resource
	private RestTemplate restTemplate;


	/**
	 * 一般情况下，通过浏览器的地址栏输入url，发送的只能是get请求
	 * 我们底层调用的是post方法，模拟消费者发送get请求，客户端消费者
	 * 参数可以不添加@RequestBody
	 *
	 * @param payDTO
	 * @return
	 */
	@GetMapping("consumer/pay/add")
	public ResultData addOrder(PayDTO payDTO) {
		return restTemplate.postForObject(PAYMENT_SERVER_URL + "/pay/add", payDTO, ResultData.class);
	}

	@GetMapping("consumer/pay/get/{id}")
	public ResultData getPayInfo(@PathVariable("id") Integer id) {
		return restTemplate.getForObject(PAYMENT_SERVER_URL + "/pay/get/{id}", ResultData.class, id);
	}

	@GetMapping("consumer/pay/del/{id}")
	public void delPayInfo(@PathVariable("id") Integer id) {
		restTemplate.delete(PAYMENT_SERVER_URL + "/pay/del/{id}", id);
	}

	@GetMapping("consumer/pay/update/")
	public void updatePayInfo(PayDTO payDTO) {
		restTemplate.put(PAYMENT_SERVER_URL + "/pay/update", payDTO);
	}


	@GetMapping("consumer/pay/get/info")
	public String getZoroInfo() {
		return restTemplate.getForObject(PAYMENT_SERVER_URL + "/pay/get/info", String.class);
	}

	@Resource
	private DiscoveryClient discoveryClient;

	@GetMapping("/consumer/discovery")
	public String discovery() {
		List<String> services = discoveryClient.getServices();
		for (String element : services) {
			System.out.println(element);
		}

		System.out.println("===================================");

		List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
		for (ServiceInstance element : instances) {
			System.out.println(element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort() + "\t" + element.getUri());
		}

		return instances.get(0).getServiceId() + ":" + instances.get(0).getPort();
	}


}
