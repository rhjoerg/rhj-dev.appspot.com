package ch.rhj.appengine.security;

public interface SecurityService {

	public User getUser(String userid, String password);
	
	public User addUser(String userid, String password, String... roles);
}
