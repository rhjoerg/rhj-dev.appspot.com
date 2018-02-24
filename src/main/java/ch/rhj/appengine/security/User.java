package ch.rhj.appengine.security;

public interface User {

	String getUserid();
	
	String[] getRoles();
	
	boolean isInRoles(String... roles);
	
	void addRoles(String... roles);
	
	void removeRoles(String... roles);
}
