package com.zoro.cloud.controller;

import com.zoro.cloud.feign.PayFeignApi;
import com.zoro.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderGateWayController {
	@Resource
	private PayFeignApi payFeignApi;

	@GetMapping(value = "/feign/pay/gateway/get/{id}")
	public ResultData getById(@PathVariable("id") Integer id) {
		return payFeignApi.getGatewayById(id);
	}

	@GetMapping(value = "/feign/pay/gateway/info")
	public ResultData<String> getGatewayInfo() {
		return payFeignApi.getGatewayInfo();
	}
}