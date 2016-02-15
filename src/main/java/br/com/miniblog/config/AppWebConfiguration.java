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
	 * configuração para dizer ao spring onde vão ser encontradas as views deste
	 * projeto, informando o prefixo ou seja, o ponto de partida onde estarão as
	 * views, e o sufixo que representa a extenção destes arquivos
	 */
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	/*
	 * método de configuração para para informar ao spring onde estão
	 * localizadas as mensagens de validação do projeto, um detalhe importante
	 * sobre este método é que ele tem que se chamar messageSource, pois o
	 * spring vai procurar por um bean registrado com esse nome, caso não queira
	 * deixar o método com o nome messaSource é só dar um nome de messageSource
	 * a anotação Bean como o nome a seguir: @Bean(name="messageSource")
	 */
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource bundle = new ReloadableResourceBundleMessageSource();
		bundle.setBasename("/WEB-INF/messages");
		bundle.setDefaultEncoding("UTF-8");
		bundle.setCacheSeconds(1);
		return bundle;
	}
	
	/*configuração necessária para informar ao spring que o formato da data padrão a ser utilizado é o formado
	 * informado logo abaixo no metodo em questão*/
	@Bean
	public FormattingConversionService mvcConversionService() {
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService(true);
		DateFormatterRegistrar registrar = new DateFormatterRegistrar();
		registrar.setFormatter(new DateFormatter("yyyy-MM-dd"));
		registrar.registerFormatters(conversionService);
		return conversionService;
	}
	
	/*configuração responsável pelo tratamento de upload provido pela especificação de servlets*/
	/*@Bean
	public MultipartResolver multipartResolver(){
		return new StandardServletMultipartResolver();
	}*/

}
