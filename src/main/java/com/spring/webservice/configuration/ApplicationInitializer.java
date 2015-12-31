package com.spring.webservice.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Deployment descriptor annotated class to configure 
 * Application Context, Servlet mapping, filters 
 * 
 * @author Hemantha
 */
public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	/**
	 * Application Context configuration
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}

	/**
	 * MVC Dispatcher servlet configuration
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { SpringServletConfiguration.class };
	}

	/**
	 * default url mapping to root
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	

}