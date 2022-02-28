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

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "in.athenaeum.multipledbdemo.repositories.place",
        entityManagerFactoryRef = "placeEntityManagerFactory",
        transactionManagerRef = "placeTransactionManager"
)
public class PlaceDatabaseConfiguration {
    private final Environment environment;

    public PlaceDatabaseConfiguration(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DataSource placeDatasource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(environment.getProperty("place.datasource.url"));
        dataSource.setUsername(environment.getProperty("place.datasource.username"));
        dataSource.setPassword(environment.getProperty("place.datasource.password"));
        dataSource.setDriverClassName(environment.getProperty("place.datasource.driverClassName"));

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean placeEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory
                = new LocalContainerEntityManagerFactoryBean();

        factory.setDataSource(placeDatasource());
        factory.setPackagesToScan("in.athenaeum.multipledbdemo.entities.place");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        factory.setJpaVendorAdapter(vendorAdapter);

        factory.setPersistenceUnitName("place");

        Properties properties = new Properties();
        properties.put("hibernate.hbm2ddl.auto", environment.getProperty("place.hibernate.hbm2ddl.auto"));
        properties.put("hibernate.show_sql", environment.getProperty("place.hibernate.show_sql"));

        factory.setJpaProperties(properties);

        return factory;
    }

    @Bean
    public PlatformTransactionManager placeTransactionManager() {
        return new JpaTransactionManager(placeEntityManagerFactory().getObject());
    }
}
