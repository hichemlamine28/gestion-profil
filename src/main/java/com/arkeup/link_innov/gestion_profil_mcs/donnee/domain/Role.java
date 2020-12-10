package com.arkeup.link_innov.gestion_profil_mcs.donnee.domain;

import org.springframework.ldap.odm.annotations.*;

import java.util.List;

import javax.naming.Name;


@Entry(base = "ou=groups",objectClasses = { "groupOfURLs", "top" })
public final class Role {
	@Id
    private Name id;

    @Attribute(name = "cn")
    @DnAttribute(value="cn", index=1)
    private String roleName;
    
    @Attribute(name = "memberUrl")
    private String memberUrl;
    
    @Attribute(name = "member")
    private List<String> member;
    
    public Name getId() {
        return id;
    }

    public void setId(Name id) {
        this.id = id;
    }

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<String> getMember() {
		return member;
	}

	public void setMember(List<String> member) {
		this.member = member;
	}  

}
