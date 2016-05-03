package uk.org.nottinghack.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.sniffy.boot.EnableSniffy;
import io.sniffy.servlet.SnifferFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import java.util.List;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
@Configuration
@EnableSniffy
public class MvcConfig extends WebMvcConfigurerAdapter
{
//    @Autowired
//    ObjectMapper objectMapper;
//
//    public MappingJackson2HttpMessageConverter jacksonMessageConverter()
//    {
//        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
//
//        //Registering Hibernate4Module to support lazy objects
//        objectMapper.registerModule(new Hibernate4Module());
//
//        messageConverter.setObjectMapper(objectMapper);
//        return messageConverter;
//    }
//
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters)
//    {
//        converters.add(jacksonMessageConverter());
//        super.configureMessageConverters(converters);
//    }

    @Override
    // used to render a view where no controller logic is required, uses a ParameterizableViewController
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
    }

//    @Bean
//    public MemberListController memberController()
//    {
//        return new MemberListController();
//    }

    // This can probably be removed when Spring Boot 1.4.0 is released
    // See: https://github.com/spring-projects/spring-boot/issues/4576
    @Bean
    public Java8TimeDialect java8TimeDialect()
    {
        return new Java8TimeDialect();
    }

    @Bean
    public JavaTimeModule javaTimeModule()
    {
        return new JavaTimeModule();
    }

    @Bean
    public RestTemplate restTemplate()
    {
        return new RestTemplate();
    }
}
