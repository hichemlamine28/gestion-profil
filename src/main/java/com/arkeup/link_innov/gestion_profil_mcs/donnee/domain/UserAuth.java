package com.arkeup.link_innov.gestion_profil_mcs.donnee.domain;

import org.springframework.ldap.odm.annotations.*;

import java.util.List;

import javax.naming.Name;


@Entry(
  base = "ou=people",
  objectClasses = { "inetOrgPerson", "organizationalPerson", "person", "top" })
public final class UserAuth {
    @Id
    private Name id;

    @Attribute(name = "cn")
    private String fullName;

    @Attribute(name = "sn")
    private String pseudoName;
    
    @Attribute(name = "uid")
    @DnAttribute(value="uid", index=1)
    private String username;
    
    @Attribute(name = "userPassword")
    private  String password;
    
    @Attribute(name = "mail")
    private  String mail;
    
    @Attribute(name = "ou")
    private List<String> roles;

    public Name getId() {
        return id;
    }

    public void setId(Name id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getPseudoName() {
		return pseudoName;
	}

	public void setPseudoName(String pseudoName) {
		this.pseudoName = pseudoName;
	}

	@Override
	public String toString() {
		return "UserAuth [id=" + id + ", fullName=" + fullName + ", username=" + username +
				", password=" + password + ", mail=" + mail + ", roles=" + roles + "]";
	}
    
}
