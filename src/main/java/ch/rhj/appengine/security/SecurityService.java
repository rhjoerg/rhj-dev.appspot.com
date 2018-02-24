package ch.rhj.appengine.security;

public interface SecurityService {

	public User getUser(String userid);
	
	public User addUser(String userid, String... roles);
}
