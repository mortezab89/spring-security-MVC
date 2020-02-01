package com.mkyong.config.core;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.mkyong.config.AppConfig;

/*
* With the release of the Servlet 3.0 spec it became possible to configure your Servlet Container with (almost)
 * no xml. For this there is the ServletContainerInitializer in the Servlet specification. In this class you
 * can register filters, listeners, servlets etc. as you would traditionally do in a web.xml.

   Spring provides a an implementation the SpringServletContainerInitializer which knows how to handle
   WebApplicationInitializer classes. Spring also provides a couple of base classes to extend to make
   your life easier the AbstractAnnotationConfigDispatcherServletInitializer is one of those.
   It registers a ContextLoaderlistener (optionally) and a DispatcherServlet and allows you to easily add
   configuration classes to load for both classes and to apply filters to the DispatcherServlet and to
   provide the servlet mapping.

   The WebMvcConfigurerAdapter is for configuring Spring MVC, the replacement of the xml file loaded by
   the DispatcherServlet for configuring Spring MVC. The WebMvcConfigurerAdapter should be used for a
   @Configuration class.

   An added advantage is that you now can use the convenience classes provided by Spring instead of
   manually configuring the DispatcherServlet and/or ContextLoaderListener.
**/

public class SpringMvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { AppConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
}