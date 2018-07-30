package com.example.sports;

import com.example.sports.conf.SysConfig;
import com.fasterxml.classmate.TypeResolver;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



@EnableWebMvc
@Controller
@EnableSwagger2
@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan(value = {"com.example.sports.mapper", "com.example.sports.mapper.sys"})
public class SportsApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringBootApplication.class);
	}

	@Autowired
	private TypeResolver typeResolver;
	@Autowired
	private SysConfig sysConfig;

	public static void main(String[] args) {
		SpringApplication.run(SportsApplication.class, args);
	}

	@RequestMapping("/")
	String home() {
		return "redirect:/static/login.html";
	}

//	/**
//	 * 生成API文档的入口
//	 */
//	@Bean
//	public Docket generateApi() {
//		Docket docket = null;
//		// 可以根据配置决定不做任何API生成
//		if (!sysConfig.isGenApi()) {
//			docket = new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.none()).build();
//			return docket;
//		}
//
//		docket = new Docket(DocumentationType.SWAGGER_2).select()
//				// 标示只有被 @Api 标注的才能生成API.
//				.apis(RequestHandlerSelectors.withClassAnnotation(Api.class)).paths(PathSelectors.any()).build().pathMapping("/").directModelSubstitute(Date.class, String.class)
//				// 遇到 Date时，输出成String
//				// .genericModelSubstitutes(ResponseObject.class)
//				.alternateTypeRules(newRule(typeResolver.resolve(DeferredResult.class, typeResolver.resolve(ResponseObject.class, WildcardType.class)), typeResolver.resolve(WildcardType.class)))
//				.useDefaultResponseMessages(false);
//		return docket;
//	}
}
