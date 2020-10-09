package com.hyhcoder.order.userfegin;

import com.hyhcoder.quickframe.common.core.reqres.response.ResponseData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("USER")
public interface UserFeign {
	
	@GetMapping("/user/userInfo")
	ResponseData getUserInfo(@RequestParam("userId") Integer userId);
	
}
