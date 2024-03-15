package com.zoro.cloud;

import java.time.ZonedDateTime;

/**
 * @author Zoro
 * @date 2024年03月12
 */
public class Main {
	public static void main(String[] args) {
		// 默认时区
		ZonedDateTime zbj = ZonedDateTime.now();
		System.out.println(zbj);
	}
}