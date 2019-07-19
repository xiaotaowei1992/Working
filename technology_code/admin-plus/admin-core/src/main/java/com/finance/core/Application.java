package com.finance.core;

import com.finance.core.dbchange.aop.DynamicDataSourceAnnotationAdvisor;
import com.finance.core.dbchange.aop.DynamicDataSourceAnnotationInterceptor;
import com.finance.core.dbchange.register.DynamicDataSourceRegister;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Administrator
 */
@Import(DynamicDataSourceRegister.class)
@MapperScan({"com.finance.core.*.mapper"})
@ServletComponentScan
@SpringBootApplication
@EnableTransactionManagement
public class Application extends SpringBootServletInitializer implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public DynamicDataSourceAnnotationAdvisor dynamicDatasourceAnnotationAdvisor() {
		return new DynamicDataSourceAnnotationAdvisor(new DynamicDataSourceAnnotationInterceptor());
	}

    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        factory.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/error/400"));
        factory.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500"));
        factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error/404"));
    }
}
