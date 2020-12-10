package com.arkeup.link_innov.gestion_profil_mcs.donnee.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.neo4j.User;

/**
 * The Class Productions.
 *
 * @author njaka
 */
public class Productions {

	/** The id. */
	@Id
	private String id;

	/** The category. */
	private String productType;

	/** The contributors. */
	private List<User> contributors = new ArrayList<>();

	/** The creation date. */
	private Date creationDate = new Date();

	/** The modification date. */
	private Date modificationDate;

	/** The tags. */
	private List<String> tags;

	/** The Title. */
	private String title;

	/** The url. */
	private String url;

	/** The owner id. */
	private String ownerId;

	private String mediaId;

	private Boolean hasMedia = false;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	/**
	 * Gets the contributors.
	 *
	 * @return the contributors
	 */
	public List<User> getContributors() {
		return contributors;
	}

	/**
	 * Sets the contributors.
	 *
	 * @param contributors the new contributors
	 */
	public void setContributors(List<User> contributors) {
		this.contributors = contributors;
	}

	/**
	 * Gets the creation date.
	 *
	 * @return the creation date
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * Sets the creation date.
	 *
	 * @param creationDate the new creation date
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * Gets the modification date.
	 *
	 * @return the modification date
	 */
	public Date getModificationDate() {
		return modificationDate;
	}

	/**
	 * Sets the modification date.
	 *
	 * @param modificationDate the new modification date
	 */
	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}

	/**
	 * Gets the tags.
	 *
	 * @return the tags
	 */
	public List<String> getTags() {
		return tags;
	}

	/**
	 * Sets the tags.
	 *
	 * @param tags the new tags
	 */
	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the url.
	 *
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Sets the url.
	 *
	 * @param url the new url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Gets the owner id.
	 *
	 * @return the owner id
	 */
	public String getOwnerId() {
		return ownerId;
	}

	/**
	 * Sets the owner id.
	 *
	 * @param ownerId the new owner id
	 */
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public Boolean getHasMedia() {
		return hasMedia;
	}

	public void setHasMedia(Boolean hasMedia) {
		this.hasMedia = hasMedia;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(productType, contributors, creationDate, id, modificationDate, ownerId, tags, title, url,
				mediaId, hasMedia);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Productions)) {
			return false;
		}
		Productions other = (Productions) obj;
		return Objects.equals(productType, other.productType) && Objects.equals(contributors, other.contributors)
				&& Objects.equals(creationDate, other.creationDate) && Objects.equals(id, other.id)
				&& Objects.equals(modificationDate, other.modificationDate) && Objects.equals(ownerId, other.ownerId)
				&& Objects.equals(tags, other.tags) && Objects.equals(title, other.title)
				&& Objects.equals(hasMedia, other.hasMedia) && Objects.equals(hasMedia, other.hasMedia)
				&& Objects.equals(url, other.url) && Objects.equals(mediaId, other.mediaId);
	}

}
