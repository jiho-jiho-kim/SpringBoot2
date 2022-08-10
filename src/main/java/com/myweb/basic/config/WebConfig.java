package com.myweb.basic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.myweb.basic.util.interceptor.MenuHandler;
import com.myweb.basic.util.interceptor.UserAuthHandler;

@Configuration //스프링부트의 빈 파일 설정
public class WebConfig implements WebMvcConfigurer {
	
	
	//인터셉터 등록 //스프링에서 ioc config에서 관리하는 빈이된다.
	@Bean
	public UserAuthHandler userAuthHandler() {
		return new UserAuthHandler();
	}
	
	//메뉴인터셉터등록
	@Bean
	public MenuHandler menuHandler() {
		return new MenuHandler();
	}

	
	//인터셉터 등록을 위한 메서드 오버라이딩 alt + shift + s
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		InterceptorRegistration aa = registry.addInterceptor( userAuthHandler() )
				.addPathPatterns("/product/**")      //인터셉터등록 (product로 시작하는 모든경로 ** )
				//.addPathPatterns("/topic/**")     //이런식으로 계속 여러페이지에 인터셉터를 등록할 수 있다. 
				.addPathPatterns("/user/**")
				.addPathPatterns("/main")
				.excludePathPatterns("/user/login");  //인터셉터 제외
		
		//혹시 다른 인터셉터가 있다면 추가하면 된다.
		//registry.addInterceptor(인터셉터)
		
		registry.addInterceptor(menuHandler())
				.addPathPatterns("/product/**")
				.addPathPatterns("/user/**");
	}

	
	
	
	
	
	
}
