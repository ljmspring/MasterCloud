package com.zoro.cloud.feign;

import com.zoro.cloud.entities.PayDTO;
import com.zoro.cloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Zoro
 * @date 2024年03月12
 */
// @FeignClient(name = "cloud-payment-service", path = "/pay")
@FeignClient(name = "cloud-gateway", path = "/pay")
public interface PayFeignApi {

	/**
	 * 新增一条支付相关流水记录
	 *
	 * @param payDTO
	 * @return
	 */
	@PostMapping(value = "/add")
	ResultData<String> addPay(@RequestBody PayDTO payDTO);

	/**
	 * 按照主键记录查询支付流水信息
	 *
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/get/{id}")
	ResultData<PayDTO> getById(@PathVariable("id") Integer id);

	/**
	 * openfeign天然支持负载均衡演示
	 *
	 * @return
	 */
	@GetMapping("/get/info")
	String myLb();


	/**
	 * =========Resilience4j CircuitBreaker 的例子
	 */
	@GetMapping(value = "/circuit/{id}")
	String myCircuit(@PathVariable("id") Integer id);

	/**
	 * =========Resilience4j bulkhead 的例子
	 */
	@GetMapping(value = "/bulkhead/{id}")
	String myBulkhead(@PathVariable("id") Integer id);

	/**
	 * =========Resilience4j ratelimit 的例子
	 */
	@GetMapping(value = "/rateLimit/{id}")
	String myRateLimit(@PathVariable("id") Integer id);

	/**
	 * Micrometer(Sleuth)进行链路监控的例子
	 *
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/micrometer/{id}")
	String myMicrometer(@PathVariable("id") Integer id);

	/**
	 * GateWay进行网关测试案例01
	 *
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/gateway/get/{id}")
	ResultData getGatewayById(@PathVariable("id") Integer id);

	/**
	 * GateWay进行网关测试案例02
	 *
	 * @return
	 */
	@GetMapping(value = "/gateway/info")
	ResultData<String> getGatewayInfo();

}
