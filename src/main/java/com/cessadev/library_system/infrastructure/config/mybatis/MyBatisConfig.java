package com.cessadev.library_system.infrastructure.config.mybatis;

import com.cessadev.library_system.domain.enums.ECategoryName;
import com.cessadev.library_system.domain.enums.ECopyBookStatus;
import com.cessadev.library_system.domain.enums.ECourseStudent;
import com.cessadev.library_system.domain.enums.ELoanStatus;
import com.cessadev.library_system.infrastructure.config.mybatis.handler.CategoryNameTypeHandler;
import com.cessadev.library_system.infrastructure.config.mybatis.handler.CopyBookStatusTypeHandler;
import com.cessadev.library_system.infrastructure.config.mybatis.handler.CourseStudentTypeHandler;
import com.cessadev.library_system.infrastructure.config.mybatis.handler.LoanStatusTypeHandler;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan("com.cessadev.library_system.infrastructure.adapters.persistence.mapper")
public class MyBatisConfig {

  @Bean
  public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
    SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
    sessionFactory.setDataSource(dataSource);

    // Configuración explícita para los mappers
    sessionFactory.setMapperLocations(
            new PathMatchingResourcePatternResolver().getResources("classpath*:/mappers/**/*.xml")
    );

    org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
    configuration.setMapUnderscoreToCamelCase(true);
    configuration.setDefaultFetchSize(100);
    configuration.setDefaultStatementTimeout(30);
    configuration.getTypeHandlerRegistry().register(ECourseStudent.class, CourseStudentTypeHandler.class);
    configuration.getTypeHandlerRegistry().register(ECopyBookStatus.class, CopyBookStatusTypeHandler.class);
    configuration.getTypeHandlerRegistry().register(ELoanStatus.class, LoanStatusTypeHandler.class);
    configuration.getTypeHandlerRegistry().register(ECategoryName.class, CategoryNameTypeHandler.class);

    sessionFactory.setConfiguration(configuration);
    return sessionFactory.getObject();
  }
}
