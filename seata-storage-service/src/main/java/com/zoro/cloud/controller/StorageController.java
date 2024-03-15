package com.zoro.cloud.controller;

import com.zoro.cloud.resp.ResultData;
import com.zoro.cloud.service.StorageService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StorageController {
	@Resource
	private StorageService storageService;

	/**
	 * 扣减库存
	 */
	@RequestMapping("/storage/decrease")
	public ResultData decrease(Long productId, Integer count) {

		storageService.decrease(productId, count);
		return ResultData.success("扣减库存成功!");
	}
}