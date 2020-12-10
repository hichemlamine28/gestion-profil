package com.arkeup.link_innov.gestion_profil_mcs.donnee.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Stéphan R.
 *
 */
@Document
public class DynamicPage {

	@Id
	private String id;

	private Date creationDate;

	private Date modificationDate;

	private String content;

	private String urlLabel;

	private String tag;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrlLabel() {
		return urlLabel;
	}

	public void setUrlLabel(String urlLabel) {
		this.urlLabel = urlLabel;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
}
