package ch.rhj.appengine.security;

import ch.rhj.appengine.security.internal.SecurityRepositoryImpl;
import ch.rhj.appengine.security.internal.SecurityServiceImpl;

public class SecurityServiceFactory {

	public static SecurityRepository getSecurityRepository() {
		
		return SecurityRepositoryImpl.INSTANCE;
	}
	
	public static SecurityService getSecurityService() {
		
		return SecurityServiceImpl.INSTANCE;
	}
}
