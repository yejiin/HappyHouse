package com.ssafy.happyhouse.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.happyhouse.interceptor.ConfirmInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	private final List<String> patterns = Arrays.asList("/happyhouse/*", "/admin", "/board/*");

//	@Autowired
//	private ConfirmInterceptor confirmInterceptor;
//	
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(confirmInterceptor).addPathPatterns(patterns);
//	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
//		System.out.println("CORS Setting");
//		default 설정.
//		Allow all origins.
//		Allow "simple" methods GET, HEAD and POST.
//		Allow all headers.
//		Set max age to 1800 seconds (30 minutes).
		registry.addMapping("/**")
			.allowedOrigins("*")
//			.allowedOrigins("http://localhost:8080", "http://localhost:8081")
			.allowedMethods("GET", "POST", "PUT", "DELETE")
			.maxAge(6000);
	}

	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui.html**").addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}