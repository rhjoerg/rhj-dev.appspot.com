package ch.rhj.appengine.security.internal;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

import ch.rhj.appengine.security.SecurityService;
import ch.rhj.appengine.security.User;

public class SecurityServiceImplTests {
	
	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalDatastoreServiceTestConfig()
	);
	
	@BeforeEach
	public void setUp() {
		
		helper.setUp();
	}
	
	@AfterEach
	public void tearDown() {
		
		helper.tearDown();
	}

	@Test
	public void noUser() throws Exception {
		
		SecurityService service = SecurityServiceImpl.INSTANCE;
		
		assertNull(service.getUser("12345"));
	}
	
	@Test
	public void userWithRoles() throws Exception {
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Key key = KeyFactory.createKey("User", "12345");
		Entity entity = new Entity(key);
		
		entity.setProperty("roles", "admin,developer");
		datastore.put(entity);
		
		SecurityService service = SecurityServiceImpl.INSTANCE;
		User user = service.getUser("12345");
		String[] expecteds = { "admin", "developer" };
		String[] actuals = user.getRoles();
		
		assertArrayEquals(expecteds, actuals);
	}
}
