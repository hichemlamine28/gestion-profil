package com.arkeup.link_innov.gestion_profil_mcs.commun.config.security.user_details;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	
	private String openFireId;
	private String username;
	private String password;
	private List<GrantedAuthority> authorities;

	public CustomUserDetails(String openFireId, String username, String password, List<GrantedAuthority> authorities) {
		super();
		this.openFireId = openFireId;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}

	public String getOpenFireId() {
		return openFireId;
	}

	public void setOpenFireId(String openFireId) {
		this.openFireId = openFireId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuthorities(List<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
