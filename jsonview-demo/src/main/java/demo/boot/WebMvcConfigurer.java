package demo.boot;

import java.util.List;

import javax.servlet.Filter;

import org.developframework.jsonview.spring.JsonviewScanLoader;
import org.developframework.jsonview.springmvc.JsonviewHttpMessageConverter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "demo.controller,demo.service")
@EnableAutoConfiguration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		// 此处务必添加到首位，领先于MappingJackson2HttpMessageConverter
		converters.add(0, getJsonviewHttpMessageConverter());
	}

	private JsonviewHttpMessageConverter getJsonviewHttpMessageConverter() {
		JsonviewScanLoader loader = new JsonviewScanLoader("classpath:jsonview/*.xml");
		JsonviewHttpMessageConverter jsonviewHttpMessageConverter = new JsonviewHttpMessageConverter(loader.createJsonviewFactory());
		jsonviewHttpMessageConverter.getObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
		return jsonviewHttpMessageConverter;
	}

	@Bean
	public Filter characterEncodingFilter() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return characterEncodingFilter;
	}
}
