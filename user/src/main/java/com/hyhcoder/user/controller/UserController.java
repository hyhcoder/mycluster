package com.hyhcoder.user.controller;

import com.hyhcoder.quickframe.common.core.reqres.response.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
@Slf4j
public class UserController {
	
	
	@Value("${app.name}")
	private String appName;
	
	
	@RequestMapping("/userInfo")
	public ResponseData getUserInfo(@RequestParam("userId") Integer userId) throws Exception {
		log.info("访问的是{}", appName);
		
		if ("user-1".equals(appName)) {
			Thread.sleep(3000);
			//int a = 1/0;
		}
		log.info("访问完毕{}", appName);
		return ResponseData.success(appName + "    " + userId);
	}
	
}
