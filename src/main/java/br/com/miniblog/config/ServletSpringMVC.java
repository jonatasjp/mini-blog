package br.com.miniblog.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {

		return null;

	}

	/*
	 * estou dizendo para o spring que ele ir� encontrar na classe
	 * appwebconfiguration os controllers e outras classes que v�o ser
	 * carregadas pelo container do spring
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {

		return new Class[] { AppWebConfiguration.class, JPAConfiguration.class};

	}

	/*
	 * estou dizendo para o spring que a partir do padr�o / vai ser delegado
	 * para o spring MVC
	 */
	@Override
	protected String[] getServletMappings() {

		return new String[] { "/" };

	}

}
