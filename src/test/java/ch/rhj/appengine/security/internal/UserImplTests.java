package ch.rhj.appengine.security.internal;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class UserImplTests {

	@Test
	public void addRoles() {
		
		UserImpl user = new UserImpl("12345");
		
		assertFalse(user.isInRoles("admin", "developer"));
		
		user.addRoles("admin", "developer");
		
		assertTrue(user.isInRoles("admin", "developer"));
		
		String expected = "12345";
		String actual = user.getUserid();
		
		assertEquals(expected, actual);
		
		String[] expecteds = {"admin", "developer"};
		String[] actuals = user.getRoles();
		
		assertArrayEquals(expecteds, actuals);
		
		user.removeRoles("admin", "developer");
		
		assertEquals(0, user.getRoles().length);
	}
}
