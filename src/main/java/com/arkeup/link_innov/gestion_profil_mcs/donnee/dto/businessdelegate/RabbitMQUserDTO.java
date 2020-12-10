/**
 * 
 */
package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.businessdelegate;

import java.util.List;
import java.util.Objects;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.MoreObjects;

/**
 * @author mikajy
 *
 */
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class RabbitMQUserDTO extends BaseDTO {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String userId;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String password;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String tags;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<String> users;

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the tags
	 */
	public String getTags() {
		return tags;
	}

	/**
	 * @param tags the tags to set
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}

	/**
	 * @return the users
	 */
	public List<String> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(List<String> users) {
		this.users = users;
	}
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("super", super.toString()).add("userId", userId)
				.add("password", password).add("tags", tags).add("users", users).toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), userId);
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof RabbitMQUserDTO) {
			if (!super.equals(object))
				return false;
			RabbitMQUserDTO that = (RabbitMQUserDTO) object;
			return Objects.equals(this.userId, that.userId);
		}
		return false;
	}
}
