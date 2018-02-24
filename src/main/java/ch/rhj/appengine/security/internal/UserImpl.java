package ch.rhj.appengine.security.internal;

import static java.util.stream.Collectors.joining;

import java.io.Serializable;
import java.util.Objects;
import java.util.TreeSet;

import ch.rhj.appengine.security.User;

public class UserImpl implements User, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private final String userid;
	private String roles = null;
	
	public UserImpl(String userid) {
		
		this.userid = Objects.requireNonNull(userid);
	}

	@Override
	public String getUserid() {

		return userid;
	}
	
	private synchronized TreeSet<String> getRoleSet() {
		
		TreeSet<String> result = new TreeSet<>();
		
		if (roles == null)
			return result;
		
		for (String role : roles.split(","))
			result.add(role);
		
		return result;
	}
	
	private synchronized void setRoleSet(TreeSet<String> roleSet) {
		
		this.roles = roleSet.isEmpty() ? null : roleSet.stream().collect(joining(","));
	}

	@Override
	public synchronized String[] getRoles() {
		
		TreeSet<String> roleSet = getRoleSet();
		
		return roleSet.toArray(new String[roleSet.size()]);
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
