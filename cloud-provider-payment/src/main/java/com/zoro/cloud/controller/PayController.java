package com.zoro.cloud.controller;

import com.zoro.cloud.entities.Pay;
import com.zoro.cloud.entities.PayDTO;
import com.zoro.cloud.enums.ReturnCodeEnum;
import com.zoro.cloud.resp.ResultData;
import com.zoro.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Tag(name = "支付微服务模块", description = "支付CRUD")
public class PayController {
	@Resource
	private PayService payService;

	@PostMapping(value = "/pay/add")
	@Operation(summary = "新增", description = "新增支付流水方法,json串做参数")
	public ResultData<String> addPay(@RequestBody Pay pay) {
		System.out.println(pay.toString());
		int i = payService.add(pay);
		return ResultData.success(String.valueOf(i));
	}

	@DeleteMapping(value = "/pay/del/{id}")
	@Operation(summary = "删除", description = "删除支付流水方法")
	public ResultData<Integer> deletePay(@PathVariable("id") Integer id) {
		return ResultData.success(payService.delete(id));
	}

	@PutMapping(value = "/pay/update")
	@Operation(summary = "修改", description = "修改支付流水方法")
	public ResultData<String> updatePay(@RequestBody PayDTO payDTO) {
		Pay pay = new Pay();
		BeanUtils.copyProperties(payDTO, pay);

		int i = payService.update(pay);
		return ResultData.success(String.valueOf(i));
	}

	@GetMapping(value = "/pay/get/{id}")
	@Operation(summary = "按照ID查流水", description = "查询支付流水方法")
	public ResultData<Pay> getById(@PathVariable("id") Integer id) {
		Pay pay = payService.getById(id);
		try {
			TimeUnit.SECONDS.sleep(62);
		} catch (Exception e) {
			return ResultData.fail(ReturnCodeEnum.RC500.getCode(), ReturnCodeEnum.RC500.getMessage());
		}

		return ResultData.success(pay);
	}

	// 全部查询getAll作为家庭作业
	@GetMapping("/pay/getAll")
	@Operation(summary = "查询", description = "查询全部支付流水方法")
	public ResultData<List<Pay>> getAll() {
		List<Pay> pays = payService.getAll();

		return ResultData.success(pays);
	}


	@Value("${server.port}")
	private String port;

	@GetMapping("/pay/get/info")
	public String getInfoByConsul(@Value("${zoro.info}") String zoroInfo) {

		return "zoroInfo：" + zoroInfo + "port：" + port;
	}
}