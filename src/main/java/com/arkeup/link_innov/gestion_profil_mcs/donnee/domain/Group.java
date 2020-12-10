package com.arkeup.link_innov.gestion_profil_mcs.donnee.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@org.springframework.data.mongodb.core.mapping.Document
@org.springframework.data.elasticsearch.annotations.Document(indexName = "gestion_profil_mcs", type = "Group")
public class Group {

	@Id
	private String id;

	/**
	 * owner group
	 *
	 * @type {string}
	 * @memberof Group
	 */
	private String ownerId;

	/**
	 * Description
	 *
	 * @type {string}
	 * @memberof Group
	 */
	private String description;

	/**
	 * Image de fond
	 *
	 * @type {string}
	 * @memberof Group
	 */
	private String backgroundImage;

	/**
	 * Date of creation
	 *
	 * @type {date}
	 * @memberof Group
	 */
	private Date createDate;

	/**
	 * Date of modification
	 *
	 * @type {date}
	 * @memberof Group
	 */
	private Date modifDate;

	/**
	 * Name
	 *
	 * @type {string}
	 * @memberof Group
	 */
	@JsonProperty("groupName")
	private String name;

	/**
	 * isPublic
	 *
	 * @type {boolean}
	 * @memberof Group
	 */

	private Boolean isPublic = false;

	private String mediaId;

	private Boolean isMembersAllowedToPost = true;

	private String backgroundId;

	private Boolean isPostValidationRequired = false;

	private Boolean hasMedia = false;

	private Boolean hasBackground = false;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBackgroundImage() {
		return backgroundImage;
	}

	public void setBackgroundImage(String backgroundImage) {
		this.backgroundImage = backgroundImage;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifDate() {
		return modifDate;
	}

	public void setModifDate(Date modifDate) {
		this.modifDate = modifDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public Boolean getIsMembersAllowedToPost() {
		return isMembersAllowedToPost;
	}

	public void setIsMembersAllowedToPost(Boolean isMembersAllowedToPost) {
		this.isMembersAllowedToPost = isMembersAllowedToPost;
	}

	public String getBackgroundId() {
		return backgroundId;
	}

	public void setBackgroundId(String backgroundId) {
		this.backgroundId = backgroundId;
	}

	public Boolean getIsPostValidationRequired() {
		return isPostValidationRequired;
	}

	public void setIsPostValidationRequired(Boolean isPostValidationRequired) {
		this.isPostValidationRequired = isPostValidationRequired;
	}

	public Boolean getHasMedia() {
		return hasMedia;
	}

	public void setHasMedia(Boolean hasMedia) {
		this.hasMedia = hasMedia;
	}

	public Boolean getHasBackground() {
		return hasBackground;
	}

	public void setHasBackground(Boolean hasBackground) {
		this.hasBackground = hasBackground;
	}
}
