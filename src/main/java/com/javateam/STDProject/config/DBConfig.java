package com.javateam.STDProject.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
//import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

//import lombok.extern.slf4j.Slf4j;

@Configuration
//@Slf4j
public class DBConfig {
	
	@Bean
	//@ConfigurationProperties 어노테이션을 사용하여 프로퍼티파일의 값을 바인딩한 java config 클래스를 빈으로 등록하여 사용할 수 있다.
	//특정 prefix로 시작하는 프로퍼티 값들을 가져와서 바인딩 할 수 있으며 @PropertySource를 사용하여 특정 파일의 값들을 받아올 수도 있다.
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSourceProperties dataSourceProp() {
        return new DataSourceProperties();
    }
	
    @Primary
    @Bean(name="dataSource")
    @ConfigurationProperties(prefix="spring.datasource")
    // @Qualifier("primary")
    public DataSource dataSource() {
        return dataSourceProp().initializeDataSourceBuilder().build();
    }

    @Bean(name="hikariCP")
    //connection pooling을 제공하는 JDBC DataSource의 구현체
    // @Primary
    @ConfigurationProperties(prefix="spring.datasource.hikari") 
    public DataSource hikariDataSource() {
        return DataSourceBuilder.create()
        			.type(HikariDataSource.class)
        			.build();
    }

    @Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		//데이터베이스와의 연결과 SQL의 실행에 대한 모든 것을 가진 가장 중요한 객체다.
    	//이 객체가 DataSource를 참조하여 MyBatis와 Mysql 서버를 연동시켜준다.
	    SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
	    // factoryBean.setDataSource(hikariDataSource());
	    factoryBean.setDataSource(dataSource());
	    factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
					.getResources("classpath:/mapper/*.xml"));
	    
	    return factoryBean.getObject();
	}
	
	@Bean(name="sqlSession")	
	public SqlSessionTemplate sqlSessionTemplate() throws Exception {
		
		return new SqlSessionTemplate(sqlSessionFactory());
	}
	
	@Bean
    public PlatformTransactionManager transactionManager() {
	        
		return new DataSourceTransactionManager(this.hikariDataSource());
	}
	
//	@Bean
//	public DataSourceTransactionManager getTransactionManager2() {
//		
//		return new DataSourceTransactionManager(this.hikariDataSource());
//	} //
}
