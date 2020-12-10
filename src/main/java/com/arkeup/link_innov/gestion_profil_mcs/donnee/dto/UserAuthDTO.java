package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto;

import java.util.List;
import java.util.Objects;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by bgh on 18/10/18.
 */
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class UserAuthDTO extends BaseDTO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String lastName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String firstName;

    private String fullName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String pseudoName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String username;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> roles;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String mail;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;
    
    @JsonInclude
    private String language;

    private String oldPassword;

    public UserAuthDTO() {
        super();
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

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
    
    public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

    public UserAuthDTO(String lastName, String firstName, String pseudoName, String username, List<String> roles, String mail) {
        super();
        this.lastName = lastName;
        this.firstName = firstName;
        this.pseudoName = pseudoName;
        this.username = username;
        this.roles = roles;
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "UserAuthDTO [lastName=" + lastName + ", firstName=" + firstName + ", pseudoName=" + pseudoName
                + ", username=" + username + ", password=" + password + ", mail=" + mail + ", roles=" + roles + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), lastName, firstName, pseudoName, username, password, roles, mail);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof UserAuthDTO) {
            if (!super.equals(object)) {
                return false;
            }
            UserAuthDTO that = (UserAuthDTO) object;
            return Objects.equals(this.lastName, that.lastName) && Objects.equals(this.firstName, that.firstName)
                    && Objects.equals(this.pseudoName, that.pseudoName) && Objects.equals(this.username, that.username)
                    && Objects.equals(this.password, that.password) && Objects.equals(this.roles, that.roles)
                    && Objects.equals(this.mail, that.mail);
        }
        return false;
    }

}
