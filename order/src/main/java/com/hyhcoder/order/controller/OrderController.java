package com.hyhcoder.order.controller;

import com.hyhcoder.order.userfegin.UserFeign;
import com.hyhcoder.quickframe.common.core.reqres.response.ResponseData;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/ord")
@Slf4j
public class OrderController {
	
	private static final String USER_URL = "http://USER";
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private UserFeign userFeign;
	
	@Value("${app.name}")
	private String appName;
	
	@RequestMapping("/getUserInfo")
	public ResponseData getOrderUserInfo() {
		log.info("访问的服务器为{}", appName);
		
		ResponseData userResponseData = userFeign.getUserInfo(5);
		
		return ResponseData.success(appName + "  " + userResponseData.getData());
	}
	
	@RequestMapping("/getHyUserInfo")
	@HystrixCommand(fallbackMethod = "fallbackMethod")
	public ResponseData getHyOrderUserInfo() {
		log.info("访问的服务器为{}", appName);
		
		ResponseData userResponseData = userFeign.getUserInfo(5);
		//int a = 1/0;
		return ResponseData.success(appName + "  " + userResponseData.getData());
	}
	
	
	/**
	 * 降级处理
	 */
	public ResponseData fallbackMethod() {
		log.error("USER服务器调用失败");
		return ResponseData.error("USER服务器调用失败, 开始降级");
	}
}
