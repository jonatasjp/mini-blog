package br.com.miniblog.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
public class JPAConfiguration {

	/*
	 * neste método a classe localcontainerentity.... é a abstração do arquivo
	 * persistence.xml nessa classe é preciso setar o datasource e o pacote onde
	 * estão os models mapeados com o JPA, o jpa vendor adapter é recebe a
	 * instancia de hibernatejpavendoradapter, ou seja, estamos informando para
	 * o spring que o hibernate é a implementação escolhida para o jpa
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] {"br.com.miniblog.models"});
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());
		return em;
	}

	/*
	 * método onde é passado todas as informaçoes com relação ao banco de dados
	 * neste método é passado o driver, o endereço do banco, usuário e senha
	 */
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/miniblog");
		dataSource.setUsername("root");
		dataSource.setPassword("1234");
		return dataSource;
	}

	/*
	 * nesse método são setadas propriedades adicionais, como é o caso do update
	 * que diz que nosso banco será atualizado e o show_sql, que mostrará no
	 * console as informações sql
	 */
	private Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		properties.setProperty("hibernate.show_sql", "true");
		return properties;
	}

	/*para habilitar que a transação seja controlada pelo controle transacional do spring*/
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}

}
