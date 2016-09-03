package ua.skillsup.javacourse.blog;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan({
        "ua.skillsup.javacourse.blog.service",
        "ua.skillsup.javacourse.blog.persistence",
        //"ua.skillsup.javacourse.blog.demo"
        })
@EnableTransactionManagement
public class SpringPersistenceConfig {

/*    @Value("${hibernateDialect}")
    private String hibernateDialect;*/

    @Autowired
    private Environment env; // отсуюда мы будем брать проперти, которые загрузили через @PropertySource

    /* Настройка источника данных. Использование пулов соединений */
    @Bean // Компонент получит идентификатор dataSource.
    public DataSource dataSource() {
        final BasicDataSource ds = new BasicDataSource();
        // Здесь выполняются настройки драйвера JDBC для базы данных H2
        ds.setDriverClassName("org.h2.Driver");
        ds.setUrl("jdbc:h2:mem:blog_db;DB_CLOSE_DELAY=-1");
        ds.setUsername("sa");
        ds.setPassword("");
        ds.setInitialSize(5);
        ds.setMaxTotal(10);
        return ds;
    }

    /* Настройка фабрики сеансов доступа к данным */
    @Bean
    public AnnotationSessionFactoryBean sessionFactory() {
        final AnnotationSessionFactoryBean sessionFactory = new AnnotationSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("ua.skillsup.javacourse.blog.domain");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    private Properties hibernateProperties() {
        return new Properties() {
            {
                //setProperty("hibernate.hbm2ddl.auto", env.getProperty("create-drop"));
                //setProperty("connection.isolation", env.getProperty("4"));
                setProperty("hibernate.dialect", env.getProperty("org.hibernate.dialect.H2Dialect"));
                //setProperty("hibernate.globally_quoted_identifiers", "true");
            }
        };
    }

    /* Настройка преобразования исключений без применения класса шаблона поддержки (необязательно) */
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    /* Настройка менеджера транзакций */
    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }

}