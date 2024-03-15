package com.zoro.cloud.predicate;

import jakarta.validation.constraints.NotNull;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

@Component
public class MyRoutePredicateFactory extends AbstractRoutePredicateFactory<MyRoutePredicateFactory.Config> {


	public MyRoutePredicateFactory() {
		super(Config.class);
	}

	@Override
	public Predicate<ServerWebExchange> apply(Config config) {
		return new GatewayPredicate() {
			@Override
			public boolean test(ServerWebExchange serverWebExchange) {
				// 检查request的参数里面，userType是否为指定的值，符合配置就通过
				String userType = serverWebExchange.getRequest().getQueryParams().getFirst("userType");
				if (!StringUtils.hasLength(userType)) {
					return false;
				}
				// 如果说参数存在，就和config的数据进行比较
				return userType.equalsIgnoreCase(config.getUserType());
			}
		};
	}

	@Override
	public List<String> shortcutFieldOrder() {
		return Collections.singletonList("userType");
	}

	public static class Config {

		@NotNull
		private String userType;

		public String getUserType() {
			return userType;
		}

		public void setUserType(String userType) {
			this.userType = userType;
		}
	}


}