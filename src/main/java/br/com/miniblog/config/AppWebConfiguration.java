package br.com.miniblog.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.miniblog.controllers.ArtigoController;
import br.com.miniblog.dao.ArtigoDAO;;

@EnableWebMvc
@ComponentScan(basePackageClasses = { ArtigoController.class, ArtigoDAO.class })
public class AppWebConfiguration extends WebMvcConfigurerAdapter {

	/*
	 * configura��o para dizer ao spring onde v�o ser encontradas as views deste
	 * projeto, informando o prefixo ou seja, o ponto de partida onde estar�o as
	 * views, e o sufixo que representa a exten��o destes arquivos
	 */
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	/*
	 * m�todo de configura��o para para informar ao spring onde est�o
	 * localizadas as mensagens de valida��o do projeto, um detalhe importante
	 * sobre este m�todo � que ele tem que se chamar messageSource, pois o
	 * spring vai procurar por um bean registrado com esse nome, caso n�o queira
	 * deixar o m�todo com o nome messaSource � s� dar um nome de messageSource
	 * a anota��o Bean como o nome a seguir: @Bean(name="messageSource")
	 */
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource bundle = new ReloadableResourceBundleMessageSource();
		bundle.setBasename("/WEB-INF/messages");
		bundle.setDefaultEncoding("UTF-8");
		bundle.setCacheSeconds(1);
		return bundle;
	}
	
	/*configura��o necess�ria para informar ao spring que o formato da data padr�o a ser utilizado � o formado
	 * informado logo abaixo no metodo em quest�o*/
	@Bean
	public FormattingConversionService mvcConversionService() {
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService(true);
		DateFormatterRegistrar registrar = new DateFormatterRegistrar();
		registrar.setFormatter(new DateFormatter("yyyy-MM-dd"));
		registrar.registerFormatters(conversionService);
		return conversionService;
	}
	
	/*configura��o respons�vel pelo tratamento de upload provido pela especifica��o de servlets*/
	/*@Bean
	public MultipartResolver multipartResolver(){
		return new StandardServletMultipartResolver();
	}*/

}
