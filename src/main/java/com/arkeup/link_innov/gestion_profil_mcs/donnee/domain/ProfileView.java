package com.arkeup.link_innov.gestion_profil_mcs.donnee.domain;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author njaka
 */
@org.springframework.data.mongodb.core.mapping.Document
@org.springframework.data.elasticsearch.annotations.Document(indexName = "gestion_profil_mcs", type = "ProfileView")
public class ProfileView {

	private String userId;

	private Date seenDate;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getSeenDate() {
		return seenDate;
	}

	public void setSeenDate(Date seenDate) {
		this.seenDate = seenDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ProfileView)) {
			return false;
		}
		ProfileView other = (ProfileView) obj;
		return Objects.equals(userId, other.userId);
	}

}
