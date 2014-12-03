package pl.edu.agh.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import pl.edu.agh.config.datasources.ProductionDataSource;
import pl.edu.agh.config.datasources.TestDataSource;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Krzysztof Kicinger on 2014-11-24.
 */
@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan({"pl.edu.agh.*"})
@Import({ProductionDataSource.class, TestDataSource.class, SpringSocialConfig.class, SecurityApplicationConfig.class})
@Profile(value = {"default", "test"})
public class ApplicationConfig {

    @Autowired
    private DataSource dataSource;

    //<editor-fold desc="DataSource And Hibernate Configuration">
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory() {
        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(getDataSource());
        builder.scanPackages("pl.edu.agh.domain");
        builder.addProperties(getHibernateProperties());
        return builder.buildSessionFactory();
    }

    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager() {
        return new HibernateTransactionManager(getSessionFactory());
    }

    private Properties getHibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");
        hibernateProperties.put("hibernate.hbm2ddl.auto", "update");
        hibernateProperties.put("hibernate.show_sql", "true");
        hibernateProperties.put("hibernate.format_sql", "true");
        return hibernateProperties;
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
    //</editor-fold>

    //<editor-fold desc="Getters and Setters">
    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    //</editor-fold>
}
