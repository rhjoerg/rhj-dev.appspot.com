package ch.rhj.appengine.security;

public interface SecurityRepository {

	public User getUser(String userid);
	
	public User setUser(String userid, String password, String... roles);
}
