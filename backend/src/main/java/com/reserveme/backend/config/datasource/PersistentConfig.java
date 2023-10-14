package com.reserveme.backend.config.datasource;

import com.reserveme.backend.config.ApplicationSettings;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(basePackages = {"com.reserveme.backend.repository"})
@EnableTransactionManagement
@ComponentScan({PersistentConfig.RM_MODEL})
public class PersistentConfig {
    public static final String RM_MODEL = "com.reserveme.backend.model.entity";
    private final ApplicationSettings settings;

    @Autowired
    public PersistentConfig(ApplicationSettings settings) {
        this.settings = settings;
    }

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(settings.getDatabaseUrl());
        config.setUsername(settings.getDatabaseUsername());
        config.setPassword(settings.getDatabasePassword());
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.setMaximumPoolSize(settings.getDatabasePoolSize());
        return new HikariDataSource(config);
    }

    public Map<String, Object> getJpaProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(Environment.DIALECT, "com.reserveme.backend.config.hibernate.RmPostgresDialect");
        properties.put(Environment.STATEMENT_BATCH_SIZE, 1000);
        properties.put("hibernate.temp.use_jdbc_metadata_defaults", false);
        properties.put("hibernate.proc.param_null_passing", true);
        properties.put(Environment.ORDER_UPDATES, true);
        properties.put(Environment.AUTOCOMMIT, false);
        properties.put(Environment.USE_SCROLLABLE_RESULTSET, true);
        properties.put("spring.jpa.hibernate.ddl-auto", "update");
        properties.put("spring.jpa.generate-ddl", false);
        properties.put("spring.jpa.properties.hibernate.hql.bulk_id_strategy", "org.hibernate.hql.spi.id.inline.InlineIdsInClauseBulkIdStrategy");
        properties.put("hibernate.hql.bulk_id_strategy", "org.hibernate.hql.spi.id.inline.InlineIdsInClauseBulkIdStrategy");
        return properties;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());

        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.POSTGRESQL);
        em.setJpaVendorAdapter(adapter);
        em.setPackagesToScan(RM_MODEL);
        em.setJpaPropertyMap(getJpaProperties());
        return em;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

}
