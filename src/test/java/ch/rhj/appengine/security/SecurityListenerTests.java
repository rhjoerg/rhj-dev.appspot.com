package ch.rhj.appengine.security;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;

public class SecurityListenerTests {

	@Test
	public void environment() {
		
		String username = System.getenv("rhj-dev.username");
		String password = System.getenv("rhj-dev.password");
		
		assertNotNull(username);
		assertNotNull(password);
		
		password = DigestUtils.sha1Hex(password);
		
		System.out.println(password);
	}
}
