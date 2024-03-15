package com.zoro.cloud.feign.fallback;

import com.zoro.cloud.enums.ReturnCodeEnum;
import com.zoro.cloud.feign.PayFeignSentinelApi;
import com.zoro.cloud.resp.ResultData;
import org.springframework.stereotype.Component;

/**
 * @author Zoro
 * @date 2024年03月14
 */
@Component
public class PayFeignSentinelApiFallBack implements PayFeignSentinelApi {
	@Override
	public ResultData getPayByOrderNo(String orderNo) {
		return ResultData.fail(ReturnCodeEnum.RC500.getCode(), "对方服务宕机或不可用，FallBack服务降级o(╥﹏╥)o");
	}
}
