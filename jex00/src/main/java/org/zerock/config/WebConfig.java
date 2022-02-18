package org.zerock.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {RootConfig.class};		// root-context.xml 대신 하는 클래스
	}
	
	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
