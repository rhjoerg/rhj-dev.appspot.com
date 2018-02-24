package ch.rhj.appengine.security.internal;

import static java.util.stream.Collectors.joining;

import java.io.Serializable;
import java.util.Objects;
import java.util.TreeSet;

import ch.rhj.appengine.security.User;

public class UserImpl implements User, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private final String userid;
	private String password;
	
	private String roles = "";
	
	public UserImpl(String userid, String password) {
		
		this.userid = Objects.requireNonNull(userid);
		this.password = Objects.requireNonNull(password);
	}

	@Override
	public String getUserid() {

		return userid;
	}
	
	@Override
	public String getPassword() {

		return password;
	}

	@Override
	public void setPassword(String password) {

		this.password = Objects.requireNonNull(password);
	}

	@Override
	public String getRoles() {

		return roles;
	}

	private synchronized TreeSet<String> getRoleSet() {
		
		TreeSet<String> result = new TreeSet<>();
		
		if (roles.isEmpty())
			return result;
		
		for (String role : roles.split(","))
			result.add(role);
		
		return result;
	}
	
	private synchronized void setRoleSet(TreeSet<String> roleSet) {
		
		this.roles = roleSet.isEmpty() ? "" : roleSet.stream().collect(joining(","));
	}

	@Override
	public boolean isInRoles(String... roles) {
		
		TreeSet<String> roleSet = getRoleSet();
		
		for (String role : roles) {
			
			if (!roleSet.contains(role))
				return false;
		}
		
		return true;
	}

	@Override
	public synchronized void addRoles(String... roles) {
		
		TreeSet<String> roleSet = getRoleSet();
		
		for (String role : roles)
			roleSet.add(role);
		
		setRoleSet(roleSet);
	}

	@Override
	public void removeRoles(String... roles) {
		
		TreeSet<String> roleSet = getRoleSet();
		
		for (String role : roles)
			roleSet.remove(role);
		
		setRoleSet(roleSet);
	}
}
