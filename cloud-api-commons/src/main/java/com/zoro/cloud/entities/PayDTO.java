package com.zoro.cloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author Zoro
 * @date 2024年03月12
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
// @Schema(title = "支付交易DTO")
public class PayDTO {

	// @Schema(title = "主键id")
	private Integer id;
	// 支付流水号
	// @Schema(title = "支付流水号")
	private String payNo;
	// 订单流水号
	// @Schema(title = "订单流水号")
	private String orderNo;
	// 用户账号ID
	// @Schema(title = "用户账号ID")
	private Integer userId;
	// 交易金额
	// @Schema(title = "交易金额")
	private BigDecimal amount;
}
