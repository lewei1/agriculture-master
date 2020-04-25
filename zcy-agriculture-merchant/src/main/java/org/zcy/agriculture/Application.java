package org.zcy.agriculture;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

@PropertySource(value = { "classpath:config.properties"})
@SpringBootApplication(scanBasePackages = {"org.zcy.agriculture"})
@MapperScan(basePackages = {"org.zcy.agriculture.admin.mapper","org.zcy.agriculture.mapper"})
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	protected SpringApplicationBuilder config(SpringApplicationBuilder applicationBuilder) {
		return applicationBuilder.sources(Application.class);
	}
	@Bean
	public FilterRegistrationBean<org.zcy.agriculture.merchant.aspect.HttpServletRequestReplacedFilter> httpServletRequestReplacedRegistration() {
		FilterRegistrationBean<org.zcy.agriculture.merchant.aspect.HttpServletRequestReplacedFilter> registration = new FilterRegistrationBean<org.zcy.agriculture.merchant.aspect.HttpServletRequestReplacedFilter>();
		registration.setFilter(new org.zcy.agriculture.merchant.aspect.HttpServletRequestReplacedFilter());
		registration.addUrlPatterns("/*");
		registration.addInitParameter("paramName", "paramValue");
		registration.setName("httpServletRequestReplacedFilter");
		registration.setOrder(1);
		return registration;
	}
}