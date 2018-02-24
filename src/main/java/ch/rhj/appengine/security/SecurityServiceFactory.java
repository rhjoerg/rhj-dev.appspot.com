package ch.rhj.appengine.security;

import ch.rhj.appengine.security.internal.SecurityServiceImpl;

public class SecurityServiceFactory {

	public static SecurityService getSecurityService() {
		
		return SecurityServiceImpl.INSTANCE;
	}
}
