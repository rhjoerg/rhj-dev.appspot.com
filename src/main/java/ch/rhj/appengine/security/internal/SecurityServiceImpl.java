package ch.rhj.appengine.security.internal;

import org.apache.commons.codec.digest.DigestUtils;

import ch.rhj.appengine.security.SecurityRepository;
import ch.rhj.appengine.security.SecurityService;
import ch.rhj.appengine.security.SecurityServiceFactory;
import ch.rhj.appengine.security.User;

public class SecurityServiceImpl implements SecurityService {
	
	public final static SecurityServiceImpl INSTANCE
		= new SecurityServiceImpl(SecurityServiceFactory.getSecurityRepository());
	
	private final SecurityRepository repository;

	private SecurityServiceImpl(SecurityRepository repository) {
		
		this.repository = repository;
	}

	@Override
	public User getUser(String userid, String password) {
		
		User user = repository.getUser(userid);
		
		if (user != null) {
			
			password = DigestUtils.sha1Hex(password);
			
			if (!password.equals(user.getPassword()))
				user = null;
		}
		
		return user;
	}

	@Override
	public User addUser(String userid, String password, String... roles) {
		
		password = DigestUtils.sha1Hex(password);
		
		return repository.setUser(userid, password, roles);
	}
}
