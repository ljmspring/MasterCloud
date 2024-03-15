package com.zoro.cloud.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.unit.DataUnit;
import com.zoro.cloud.entities.PayDTO;
import com.zoro.cloud.feign.PayFeignApi;
import com.zoro.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Zoro
 * @date 2024年03月12
 */
@RestController
@Slf4j
public class OrderController {

	@Resource
	private PayFeignApi payFeignApi;


	@PostMapping("feign/pay/add")
	public ResultData addOrder(PayDTO payDTO) {

		System.out.println("第一步：模拟本地addOrder新增订单成功(省略sql操作)，第二步：再开启addPay支付微服务远程调用");
		return payFeignApi.addPay(payDTO);
	}

	@GetMapping("feign/pay/get/{id}")
	public ResultData getPayInfo(@PathVariable("id") Integer id) {
		ResultData<PayDTO> resultData = null;
		System.out.println("-------支付微服务远程调用，按照id查询订单支付流水信息");
		try {
			log.info("=====调用开始：" + DateUtil.now());
			resultData = payFeignApi.getById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("=====调用结束：" + DateUtil.now());
		}
		return resultData;
	}


	/**
	 * openfeign天然支持负载均衡演示
	 *
	 * @return
	 */
	@GetMapping("feign/pay/get/info")
	public String myLb() {
		return payFeignApi.myLb();
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
