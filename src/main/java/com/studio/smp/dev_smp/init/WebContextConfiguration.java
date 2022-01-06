package com.studio.smp.dev_smp.init;

import com.studio.smp.dev_smp.exception.UncheckException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Properties;

/*
*  웹 컨텍스트 설정 파일
* */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.studio.smp.dev_smp.controller.*"})
public class WebContextConfiguration implements WebMvcConfigurer {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    /*
    * Exception Resolver를 설정한다.
    * */
    @Bean
    public SimpleMappingExceptionResolver getExceptionResolver(){
        SimpleMappingExceptionResolver smer = new SimpleMappingExceptionResolver();

        //지정되지 않은 예외에 대한 기본 에러페이지 이다.
        smer.setDefaultErrorView("에러표시화면");
        //상태코드 맵핑이 없는 예외를 위한 기본 상태값 이다.
        smer.setDefaultStatusCode(200);
        //기본값이 "exceptiom" 이다. 예외 모듈 속성의 키 값이다. ${exception.message}
        smer.setExceptionAttribute("exception");
        //하나 또는 그 이상의 예외를 맂로버에서 제외한다. 제외된 예외는 web.xml 에서 지정된 값이 적용됩니다.
        smer.setExcludedExceptions(UncheckException.class);

        //예외 클래스에 대해 에러 페이지를 지정한다.
        Properties mappings = new Properties();
        mappings.setProperty("com.studio.smp.dev_smp.exception.DatabaseException","common/error/databaseError");
        mappings.setProperty("com.studio.smp.dev_smp.exception.SecurityException","common/error/securityError");
        mappings.setProperty("com.studio.smp.dev_smp.exception.BusinessException","common/error/businessError");
        mappings.setProperty("com.studio.smp.dev_smp.exception.AjaxException","common/error/ajaxError");
        smer.setExceptionMappings(mappings);

        //에러페이지에 상태코드를 지정합니다.
        Properties statusCodes = new Properties();
        statusCodes.setProperty("common/error/databaseError","500");
        statusCodes.setProperty("common/error/databaseError","404");
        statusCodes.setProperty("common/error/securityError","403");
        statusCodes.setProperty("common/error/businessError","200");
        statusCodes.setProperty("common/error/ajaxError","200");
        smer.setStatusCodes(statusCodes);
        logger.error("##ERROR", smer.getStatusCodesAsMap());
        return smer;
    }

    /*
    * 메세지 소스를 생성한다.
    * */
    @Bean
    public ReloadableResourceBundleMessageSource messageSource(){
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        //메세지 프로퍼티파일의 위치와 이름을 지정한다.
        source.setBasename("classpath:/messages/message");
        //기본 인코딩을 지정한다.
        source.setDefaultEncoding("UTF-8");
        //프로퍼티 파일의 변겨을 감지할 시간 간격을 지정한다.
        source.setCacheSeconds(60);
        // 없는 메세지일 경우 예외를 발생시키는 대신 코드를 기본 메세지로 한다.
        source.setUseCodeAsDefaultMessage(true);
        return source;
    }
    /*
    *  변경된 언어 정보를 기억할 로케일 리졸버를 생성한다.
    *  여기서는 세션에 저장하는 방식을 사용한다.
    * */
    @Bean
    public SessionLocaleResolver localResolver() {return new SessionLocaleResolver();}

    /*
    * 언어 변경을 위한 인터셉터를 생성한다.
    * */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        return interceptor;
    }

    /*
    * 인터셉터를 등록한다.
    * */
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(localeChangeInterceptor());
        registry.addInterceptor(new CmInterceptor());
    }
    /*
    * 뷰 리졸버를 설정한다.
    * */
    @Bean
    public ViewResolver getViewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/view/");
        resolver.setSuffix(".jsp");
        return resolver;
    }




/*
    * jar, resources 설정
    * */

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/META-INF/resources/webjars/").setCachePeriod(31556926);

//        registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/resources/");


        WebMvcConfigurer.super.addResourceHandlers(registry);
    }



    /*
    * 파일업로드 설정
    * N
    * */
    @Bean
    public MultipartResolver multipartResolver(){
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setMaxUploadSize(104857600);
        commonsMultipartResolver.setMaxInMemorySize(104857600);
        commonsMultipartResolver.setDefaultEncoding("UTF-8");
        return commonsMultipartResolver;
    }
}
