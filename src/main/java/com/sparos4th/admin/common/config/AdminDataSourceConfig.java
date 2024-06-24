package com.sparos4th.admin.common.config;

import jakarta.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
	basePackages = "com.sparos4th.admin.admin.infrastructure.admin",
	entityManagerFactoryRef = "adminEntityManagerFactory",
	transactionManagerRef = "adminTransactionManager"
)
public class AdminDataSourceConfig {

	@Bean(name = "adminDataSource")
	@Primary
	@ConfigurationProperties(prefix = "spring.admin.datasource")
	public DataSource adminDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Primary
	@Bean(name = "adminEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean adminEntityManagerFactory(
		EntityManagerFactoryBuilder builder,
		@Qualifier("adminDataSource") DataSource adminDataSource
	) {
		return builder
			.dataSource(adminDataSource)
			.packages("com.sparos4th.admin.admin.domain.admin")
			.build();
	}

	@Primary
	@Bean(name = "adminTransactionManager")
	public PlatformTransactionManager adminTransactionManager(
		final @Qualifier("adminEntityManagerFactory") EntityManagerFactory adminEntityManagerFactory
	) {
		return new JpaTransactionManager(adminEntityManagerFactory);
	}
}
