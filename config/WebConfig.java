package com.simple.basic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.simple.basic.util.interceptor.UserAuthHandler;

@Configuration //스프링 설정파일로 선언
public class WebConfig implements WebMvcConfigurer {
	//스프링 설정파일로 사용할 클래스 (webmvcconfiguer를 상속받습니다)
	
	//ioc컨테이너 안에 생성된 객체들을 확인할 수 있는 인터페이스
//	@Autowired
//	private ApplicationContext app;
//	
//	//application.properties에 값을 참조하는 방법
//	@Value("${spring.url}")
//	private String url;
//	
//	
//	
//	@Bean
//	public void test() {
//		System.out.println("IOC컨테이너 빈 개수:" + app.getBeanDefinitionCount());
//
//		HomeController h = app.getBean(HomeController.class);
//		System.out.println("IOC컨테이너 안에 빈객체:" + h);
//		System.out.println("홈컨트롤러 안에 메서드:" + h.home());
//	
//		//util메서드 실행.
//		//UtilComponent com = app.getBean(UtilComponent.class);
//		//System.out.println("유틸컴포넌트 안에 메서드:" + com.util());
//	
//		//util메서드 실행.
//		System.out.println("유틸컴포넌트 안에 메서드:" +  utilComponent().util()    );
//	
//		//application.properties안에 값
//		System.out.println("spring.url값:" + url);
//	}
//	
//	
//	@Bean //빈으로 생성
//	public UtilComponent utilComponent() {
//		return new UtilComponent();
//	}
	
	/*
	 * 2nd
	 * @Bean //빈으로 생성 public BoardServiceImpl boardServiceImpl() { return new
	 * BoardServiceImpl(); }
	 */
	
	///////////////////////////////////////////////////////
	@Bean
	public UserAuthHandler userAuthHandler() {
		return new UserAuthHandler();
	}

	
	//인터셉터 등록을 위한 메서드 오버라이딩 alt + shift + s
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		InterceptorRegistration aa = registry.addInterceptor( userAuthHandler() )
				.addPathPatterns("/user/**")      //인터셉터등록 (유저로 시작하는 모든경로 ** )
				.addPathPatterns("/memo/**")     //이런식으로 계속 여러페이지에 인터셉터를 등록할 수 있다. 
				.excludePathPatterns("/user/login");  //인터셉터 제외
		
	}
	
	
}
