package com.docmall.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.docmall.demo.interceptor.AdminInterceptor;
import com.docmall.demo.interceptor.UserInterceptor;

@Component
public class WebMvcConfiguration2 implements WebMvcConfigurer{
	
	@Autowired
	AdminInterceptor adminInterseptor;
	
	
//	인터셉터 설정에서 제외되는 경로 
	private static final String[] EXCLUDE_PATHS = {
			"/admin/intro",
			"/admin/admin_ok"
	};

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(adminInterseptor)
		.addPathPatterns("/admin/**") // 인터셉터 포함
		.excludePathPatterns(EXCLUDE_PATHS); // 인터셉터 미포함
	}
}
