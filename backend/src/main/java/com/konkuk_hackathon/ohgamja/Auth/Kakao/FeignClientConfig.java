package com.konkuk_hackathon.ohgamja.Auth.Kakao;

import com.konkuk_hackathon.ohgamja.OhgamjaApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackageClasses = OhgamjaApplication.class)
public class FeignClientConfig {
}
