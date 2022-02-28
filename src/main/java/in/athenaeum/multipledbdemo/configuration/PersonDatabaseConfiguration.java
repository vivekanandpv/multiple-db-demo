package in.athenaeum.multipledbdemo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

//  Applies only to the repositories found in repositories.person package
//  entityManagerFactoryRef and transactionManagerRef should go by the custom name as mentioned inside
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "in.athenaeum.multipledbdemo.repositories.person",
        entityManagerFactoryRef = "personEntityManagerFactory",
        transactionManagerRef = "personTransactionManager"
)
public class PersonDatabaseConfiguration {
    //  Required to read application.properties
    private final Environment environment;

    public PersonDatabaseConfiguration(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DataSource personDatasource() {
        //  Also possible to get from DataSourceBuilder.create().build()
        //  Builder approach doesn't provide configuration found below
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(environment.getProperty("person.datasource.url"));
        dataSource.setUsername(environment.getProperty("person.datasource.username"));
        dataSource.setPassword(environment.getProperty("person.datasource.password"));
        dataSource.setDriverClassName(environment.getProperty("person.datasource.driverClassName"));

        //  For low-level database specific configuration, consider
        //  Properties properties = new Properties();
        //  properties.put(...);
        //  dataSource.setConnectionProperties(properties);

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean personEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory
                = new LocalContainerEntityManagerFactoryBean();

        //  This should be managing the entities found in entities.person package
        factory.setDataSource(personDatasource());
        factory.setPackagesToScan("in.athenaeum.multipledbdemo.entities.person");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        factory.setJpaVendorAdapter(vendorAdapter);

        //  Preferred. Otherwise the persistence unit will be named default.
        //  This is not good when we have multiple persistence units.
        factory.setPersistenceUnitName("person");

        //  Hibernate properties can be provided here
        //  Spring jpa specific properties do not work
        Properties properties = new Properties();
        properties.put("hibernate.hbm2ddl.auto", environment.getProperty("person.hibernate.hbm2ddl.auto"));
        properties.put("hibernate.show_sql", environment.getProperty("person.hibernate.show_sql"));

        factory.setJpaProperties(properties);

        return factory;
    }

    @Bean
    public PlatformTransactionManager personTransactionManager() {
        //  Required for transaction management
        return new JpaTransactionManager(personEntityManagerFactory().getObject());
    }
}
