package ch.rhj.appengine.security.internal;

import static java.util.stream.Collectors.joining;

import java.util.stream.Stream;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import ch.rhj.appengine.security.SecurityService;
import ch.rhj.appengine.security.User;

public class SecurityServiceImpl implements SecurityService {
	
	public final static SecurityServiceImpl INSTANCE = new SecurityServiceImpl();

	private SecurityServiceImpl() {
		
	}

	@Override
	public User getUser(String userid) {
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Key key = KeyFactory.createKey("User", userid);
		
		try {
			
			Entity entity = datastore.get(key);
			String roles = (String) entity.getProperty("roles");
			
			UserImpl user = new UserImpl(userid);
			
			if (roles.length() > 0)
				user.addRoles(roles.split(","));
			
			return user;
			
		} catch (EntityNotFoundException e) {
			
			return null;
		}
	}

	@Override
	public User addUser(String userid, String... roles) {
		
		User user = getUser(userid);
		
		if (user == null)
			user = new UserImpl(userid);
		
		user.addRoles(roles);
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Key key = KeyFactory.createKey("User", userid);
		Entity entity = new Entity(key);
		
		entity.setProperty("roles", Stream.of(user.getRoles()).collect(joining(",")));
		datastore.put(entity);
		
		return user;
	}
}
