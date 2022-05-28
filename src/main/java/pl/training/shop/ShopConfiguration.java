package pl.training.shop;

import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import pl.training.shop.time.SystemTimeProvider;
import pl.training.shop.time.TimeProvider;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import java.util.Properties;

import static java.lang.Integer.parseInt;

@PropertySource("classpath:jdbc.properties")
@EnableTransactionManagement
@EnableAspectJAutoProxy
@ComponentScan
@Configuration
public class ShopConfiguration {

    @Bean
    public DataSource dataSource(Environment environment) {
        var datasource = new HikariDataSource();
        datasource.setUsername(environment.getProperty("database.username"));
        datasource.setPassword(environment.getProperty("database.password"));
        datasource.setJdbcUrl(environment.getProperty("database.url"));
        datasource.setDriverClassName(environment.getProperty("database.driver"));
        datasource.setMaximumPoolSize(parseInt(environment.getProperty("database.pool-size")));
        return datasource;
    }

    @Bean
    public PropertiesFactoryBean jpaProperties() {
        var factoryBean = new PropertiesFactoryBean();
        factoryBean.setLocation(new ClassPathResource("jpa.properties"));
        return factoryBean;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, Properties jpaProperties) {
        var factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setJpaProperties(jpaProperties);
        factoryBean.setPackagesToScan("pl.training.shop");
        factoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        return factoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
    public TimeProvider timeProvider() {
        return new SystemTimeProvider();
    }

}
