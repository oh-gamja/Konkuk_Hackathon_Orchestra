package com.konkuk_hackathon.ohgamja.Common.Argument_resolver;

import com.konkuk_hackathon.ohgamja.Common.Interceptor.JwtAuthInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    private final JwtAuthHandlerArgumentResolver jwtAuthHandlerArgumentResolver;
    private final JwtAuthInterceptor jwtAuthInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtAuthInterceptor).addPathPatterns("/auth/test");
        registry.addInterceptor(jwtAuthInterceptor).addPathPatterns("/games/all");
        registry.addInterceptor(jwtAuthInterceptor).addPathPatterns("/games/detail");
        registry.addInterceptor(jwtAuthInterceptor).addPathPatterns("/likes");
        registry.addInterceptor(jwtAuthInterceptor).addPathPatterns("/likes/add");
        registry.addInterceptor(jwtAuthInterceptor).addPathPatterns("/likes/remove");
        registry.addInterceptor(jwtAuthInterceptor).addPathPatterns("/playlists");
        registry.addInterceptor(jwtAuthInterceptor).addPathPatterns("/playlists/detail");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(jwtAuthHandlerArgumentResolver);
    }
}
