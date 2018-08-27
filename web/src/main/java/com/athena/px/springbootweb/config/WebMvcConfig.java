//package com.athena.px.springbootweb.config;//package com.athena.px.springbootweb.config;
//
//import com.athena.px.springbootweb.converter.PropertiesJsonHttpMessageConverter;
//import com.athena.px.springbootweb.interceptor.DefaultHandlerInterceptor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import java.util.List;
//
///**
// * @Description:
// * @Author: ShenLong
// * @CreateDate: 2018/4/23 10:45
// */
//@Configuration
//@EnableWebMvc
//public class WebMvcConfig implements WebMvcConfigurer {
//    @Override
//    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//        converters.add(new PropertiesJsonHttpMessageConverter());
//    }
//    @Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		InterceptorRegistration interceptorRegistration = registry.addInterceptor(new DefaultHandlerInterceptor());
//		interceptorRegistration.addPathPatterns("/*");
//	}
//}
