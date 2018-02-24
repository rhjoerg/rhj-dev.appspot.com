package ch.rhj.dev.security;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@WebListener
public class SecurityListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Key key = KeyFactory.createKey("User", "rhjoerg");
		Entity entity = new Entity(key);
		
		entity.setProperty("password", "a2002ec835bd21293d1762666030ac3765283873");
		entity.setProperty("roles", "admin,developer");
		datastore.put(entity);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}
}
