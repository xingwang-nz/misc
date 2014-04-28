package nz.co.xingsoft.mybatis.application;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@ComponentScan("nz.co.xingsoft")
@ImportResource("classpath:/META-INF/spring/spring-root-context.xml")
@MapperScan("nz.co.xingsoft.mybatis.persistence.mapper")
public class SpringApplicationContext {

    @Inject
    private DataSource dataSource;

    @Bean
    public static PropertySourcesPlaceholderConfigurer myPropertySourcesPlaceholderConfigurer() {
        final PropertySourcesPlaceholderConfigurer p = new PropertySourcesPlaceholderConfigurer();
        final Resource[] resourceLocations = properties();
        p.setLocations(resourceLocations);
        p.setIgnoreResourceNotFound(false);
        p.setIgnoreUnresolvablePlaceholders(false);
        return p;
    }

    static Resource[] properties() {

        return new Resource[] { new ClassPathResource("config.properties") };
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory()
            throws Exception {

        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setTypeAliasesPackage("nz.co.xingsoft.mybatis.persistence.domain"); // to find domain objects
                                                                                           // like User.java
        return sessionFactory.getObject();
    }

}
