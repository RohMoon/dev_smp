package com.studio.smp.dev_smp.init;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/*
* 루트 컨텍스트 설정 파일
* */
@Configuration
@Import({
    ContextDataSource.class,
    ContextSqlMapper.class,
    ContextMessage.class
    })
    @ComponentScan(basePackages = {"com.studio.smp.dev_smp.service.*"})
    public class RootContextConfiguration {


}