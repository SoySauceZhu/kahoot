package mingjie.kahoot.gameservice.config;

import com.github.pagehelper.PageHelper;
import mingjie.kahoot.gameservice.typehandler.LongArrayTypeHandler;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class MyBatisConfig {
//    @Bean
//    public PageHelper pageHelper() {
//        PageHelper pageHelper = new PageHelper();
//        Properties properties = new Properties();
//        properties.setProperty("helperDialect", "mysql");
//        properties.setProperty("reasonable", "true");
//        properties.setProperty("supportMethodsArguments", "true");
//        properties.setProperty("params", "count=countSql");
//        pageHelper.setProperties(properties);
//        return pageHelper;
//    }
//
    @Bean
    public SqlSessionFactory sqlSessionFactory(javax.sql.DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.getTypeHandlerRegistry().register(LongArrayTypeHandler.class);
        sessionFactory.setConfiguration(configuration);
        return sessionFactory.getObject();
    }
}
