package com.cts.clickfix.appconfig;

import com.cts.clickfix.model.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="USER-SERVICE")
public interface FeignClientUserService {
    @GetMapping("/user-api/users/{email}")
    public UserDto getUserByEmail(String email);

}
