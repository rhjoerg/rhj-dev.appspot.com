package ch.rhj.dev.security;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import ch.rhj.appengine.security.SecurityServiceFactory;

@WebListener
public class SecurityListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		SecurityServiceFactory.getSecurityService()
			.addUser("113299765676294923909", "admin", "developer");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}
}
