package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.neo4j.User;

/**
 * The Class OtherProduction.
 *
 * @author njaka
 */
@org.springframework.data.mongodb.core.mapping.Document
@org.springframework.data.elasticsearch.annotations.Document(indexName = "gestion_profil_mcs", type = "OtherProduction")
public class OtherProductionDTO extends ProductionsDTO {

	/** The authors. */
	private List<User> authors = new ArrayList<>();

	/** The date. */
	private Date date;

	/** The description. */
	private String description;

	/** The link. */
	private String link;

	/** The download possibility. */
	private Boolean downloadPossibility;

	/** The post type. */
	private String postType;

	/**
	 * Gets the authors.
	 *
	 * @return the authors
	 */
	public List<User> getAuthors() {
		return authors;
	}

	/**
	 * Sets the authors.
	 *
	 * @param authors the new authors
	 */
	public void setAuthors(List<User> authors) {
		this.authors = authors;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the link.
	 *
	 * @return the link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * Sets the link.
	 *
	 * @param link the new link
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * Gets the download possibility.
	 *
	 * @return the download possibility
	 */
	public Boolean getDownloadPossibility() {
		return downloadPossibility;
	}

	/**
	 * Sets the download possibility.
	 *
	 * @param downloadPossibility the new download possibility
	 */
	public void setDownloadPossibility(Boolean downloadPossibility) {
		this.downloadPossibility = downloadPossibility;
	}

	/**
	 * Gets the post type.
	 *
	 * @return the post type
	 */
	public String getPostType() {
		return postType;
	}

	/**
	 * Sets the post type.
	 *
	 * @param postType the new post type
	 */
	public void setPostType(String postType) {
		this.postType = postType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProductionsDTO#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(authors, date, description, downloadPossibility, link, postType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProductionsDTO#equals(
	 * java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof OtherProductionDTO)) {
			return false;
		}
		OtherProductionDTO other = (OtherProductionDTO) obj;
		return Objects.equals(authors, other.authors) && Objects.equals(date, other.date)
				&& Objects.equals(description, other.description)
				&& Objects.equals(downloadPossibility, other.downloadPossibility) && Objects.equals(link, other.link)
				&& Objects.equals(postType, other.postType);
	}

}
