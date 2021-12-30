package com.studio.smp.dev_smp.init;

import com.studio.smp.dev_smp.util.CmPathInfo;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/*
 * 데이터 설정
 * */
@Configuration
//어노테이션 기반 트랜잭션 관리를 사용 <tx:annotation-driven>
@EnableTransactionManagement
public class ContextDataSource {

    /*
    * 데이터 소스 등록
    * @return
    * */
    @Bean(destroyMethod = "close")
    public DataSource dataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        try{
            //추후 postgresql 주소오면 그쪽으로 연결필요.
            dataSource.setDriverClassName(CmPathInfo.getDB_DRIVER());
            dataSource.setUrl(CmPathInfo.getDB_URL());
            dataSource.setUsername(CmPathInfo.getDB_USER());
            dataSource.setPassword(CmPathInfo.getDB_PASSWORD());
            dataSource.setDefaultAutoCommit(false);

        }catch (Exception e){
            e.printStackTrace();
        }
        return dataSource;
    }
    
    /*
    * 트랜잭션 매니저 등록
    * @return
    * */
    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

}
