package ch.rhj.appengine.security.internal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class UserImplTests {

	@Test
	public void addRoles() {
		
		UserImpl user = new UserImpl("user", "pass");
		
		assertEquals("user", user.getUserid());
		assertEquals("pass", user.getPassword());
		assertFalse(user.isInRoles("admin", "developer"));
		
		user.addRoles("admin", "developer");
		
		assertTrue(user.isInRoles("admin", "developer"));
		
		user.removeRoles("admin", "developer");
		assertEquals("", user.getRoles());
	}
}
