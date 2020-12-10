package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GroupDTO extends BaseDTO {
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String id;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String ownerId;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String description;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String backgroundImage;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Date createDate;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Date modifDate;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private @JsonProperty("groupName") String name;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Boolean isPublic = false;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private MediaDTO mediaDTO;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String mediaId;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Boolean isMembersAllowedToPost = true;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Boolean isPostValidationRequired = false;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private MediaDTO backgroundDTO;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String backgroundId;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer memberCount;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer numberNewPost;

	private Boolean hasMedia = false;

	private Boolean hasBackground = false;

	private List<String> admins = new ArrayList<>();

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

	public MediaDTO getMediaDTO() {
		return mediaDTO;
	}

	public void setMediaDTO(MediaDTO mediaDTO) {
		this.mediaDTO = mediaDTO;
	}

	public Boolean getIsMembersAllowedToPost() {
		return isMembersAllowedToPost;
	}

	public void setIsMembersAllowedToPost(Boolean isMembersAllowedToPost) {
		this.isMembersAllowedToPost = isMembersAllowedToPost;
	}

	public Boolean getIsPostValidationRequired() {
		return isPostValidationRequired;
	}

	public void setIsPostValidationRequired(Boolean isPostValidationRequired) {
		this.isPostValidationRequired = isPostValidationRequired;
	}

	public MediaDTO getBackgroundDTO() {
		return backgroundDTO;
	}

	public void setBackgroundDTO(MediaDTO backgroundDTO) {
		this.backgroundDTO = backgroundDTO;
	}

	public Integer getMemberCount() {
		return memberCount;
	}

	public void setMemberCount(Integer memberCount) {
		this.memberCount = memberCount;
	}

	public Integer getNumberNewPost() {
		return numberNewPost;
	}

	public void setNumberNewPost(Integer numberNewPost) {
		this.numberNewPost = numberNewPost;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getBackgroundId() {
		return backgroundId;
	}

	public void setBackgroundId(String backgroundId) {
		this.backgroundId = backgroundId;
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

	public List<String> getAdmins() {
		return admins;
	}

	public void setAdmins(List<String> admins) {
		this.admins = admins;
	}
}
