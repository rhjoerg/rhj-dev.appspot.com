package ch.rhj.appengine.security.internal;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import ch.rhj.appengine.security.SecurityRepository;
import ch.rhj.appengine.security.User;

public class SecurityRepositoryImpl implements SecurityRepository {

	public final static SecurityRepositoryImpl INSTANCE = new SecurityRepositoryImpl();
	
	private SecurityRepositoryImpl() {
	}

	@Override
	public User getUser(String userid) {
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Key key = KeyFactory.createKey("User", userid);
		
		try {
			
			Entity entity = datastore.get(key);
			String password = (String) entity.getProperty("password");
			String roles = (String) entity.getProperty("roles");
			
			UserImpl user = new UserImpl(userid, password);
			
			if (roles.length() > 0)
				user.addRoles(roles.split(","));
			
			return user;
			
		} catch (EntityNotFoundException e) {
			
			return null;
		}
	}

	@Override
	public User setUser(String userid, String password, String... roles) {
		
		User user = new UserImpl(userid, password);
		
		user.addRoles(roles);
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Key key = KeyFactory.createKey("User", user.getUserid());
		Entity entity = new Entity(key);
		
		entity.setProperty("password", user.getPassword());
		entity.setProperty("roles", user.getRoles());
		datastore.put(entity);
		
		return user;
	}
}
