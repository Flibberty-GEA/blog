package ua.skillsup.javacourse.blog;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Flibberty on 03.06.2016.
 */
@Configuration
public class SpringConfig {

/*    @Value("${hibernateDialect}")
    private String hibernateDialect;*/

    @Autowired
    private Environment env; // отсуюда мы будем брать проперти, которые загрузили через @PropertySource

    /*Аннотация @Bean сообщает фреймворку Spring, что данный метод вернет объект,
    который должен быть зарегистрирован в контексте приложения Spring как компонент.
    Компонент получит идентификатор, совпадающий с именем метода.
    Идентификатор компонента и его тип являются частью сигнатуры метода.
    Фактическое создание компонента выполняется в теле метода.  */



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
        ds.setMaxActive(10);
        return ds;
    }

    // хибернейтовский sessionFactory.
    // у спринга есть удобный класс-фабрика LocalSessionFactoryBean, который мы и будем использовать.
    // он позволяет настроить автоматическое сканирование классов-сущностей.

    /**/

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




/*    private Properties hibernateProperties(){
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", hibernateDialect);
        properties.setProperty("hibernate.show_sql", showSql);
        properties.setProperty("hibernate.format_sql", formatSql);
        properties.setProperty("hibernate.use_sql_comments", useSqlComments);
        return properties;
    }*/




    /*
 <property name="hibernateProperties">
 <props>
 <prop key="dialect">org.hibernate.dialect.HSQLDialect</prop>
 </props>
 </property>
</bean>*/
}
