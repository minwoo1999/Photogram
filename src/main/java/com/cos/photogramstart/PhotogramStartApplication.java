package com.cos.photogramstart;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PhotogramStartApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotogramStartApplication.class, args);
	}

	// 빈생성
	@Bean
	Hibernate5Module hibernate5Module(){
		return new Hibernate5Module();
	}

//	@Bean
//	public FilterRegistrationBean setFilterRegistration(){
//		FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean(new MyFilter());
//		filterRegistrationBean.addUrlPatterns("/filtered/*");
//		return filterRegistrationBean;
//	}
}
