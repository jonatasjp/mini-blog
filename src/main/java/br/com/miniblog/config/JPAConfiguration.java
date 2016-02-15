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
	 * neste m�todo a classe localcontainerentity.... � a abstra��o do arquivo
	 * persistence.xml nessa classe � preciso setar o datasource e o pacote onde
	 * est�o os models mapeados com o JPA, o jpa vendor adapter � recebe a
	 * instancia de hibernatejpavendoradapter, ou seja, estamos informando para
	 * o spring que o hibernate � a implementa��o escolhida para o jpa
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
	 * m�todo onde � passado todas as informa�oes com rela��o ao banco de dados
	 * neste m�todo � passado o driver, o endere�o do banco, usu�rio e senha
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
	 * nesse m�todo s�o setadas propriedades adicionais, como � o caso do update
	 * que diz que nosso banco ser� atualizado e o show_sql, que mostrar� no
	 * console as informa��es sql
	 */
	private Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		properties.setProperty("hibernate.show_sql", "true");
		return properties;
	}

	/*para habilitar que a transa��o seja controlada pelo controle transacional do spring*/
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}

}
