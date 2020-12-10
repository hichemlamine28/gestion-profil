package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;
import com.googlecode.jmapper.annotations.JGlobalMap;

@JGlobalMap
public class PostDTO extends BaseDTO {

    private String id;
    private Long clics;
    private Date creationDate;
    private Date deleteDate;
    private Date lastEditDate;
    private Date modifictionDate;
    private String ownerAvatarId;
    private String ownerDisplayName;
    private String ownerId;
    private String personneMorale;
    private Integer recommandationCount;
    private List<String> tags;
    private String text;
    private String type;
    private Integer viewCount;
    private String sharedFromUser;
    private String sharedFromPost;
    private Integer sharedCount;
    private Set<CommentDTO> comments = new HashSet<>();
    private List<RecommandationDTO> recommandations = new ArrayList<>();
    private Integer commentCount;
    private MediaDTO mediaDTO;
    private String mediaId;
    private Boolean isApprovedByGroupAdmin = true;
    private String groupToShare;

    // liste des id groupe a poster
    private List<String> groupId;
    // liste de personne qui ont droit de voir les postes (without all)
    private List<String> userId;

    private String postOption;

    private List<String> previewToClear;

    private Integer mediaViewCount = 0;

    /**
     * Empty constructor
     */
    public PostDTO() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getClics() {
        return clics;
    }

    public void setClics(Long clics) {
        this.clics = clics;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }

    public Date getLastEditDate() {
        return lastEditDate;
    }

    public void setLastEditDate(Date lastEditDate) {
        this.lastEditDate = lastEditDate;
    }

    public Date getModifictionDate() {
        return modifictionDate;
    }

    public void setModifictionDate(Date modifictionDate) {
        this.modifictionDate = modifictionDate;
    }

    public String getOwnerAvatarId() {
        return ownerAvatarId;
    }

    public void setOwnerAvatarId(String ownerAvatarId) {
        this.ownerAvatarId = ownerAvatarId;
    }

    public String getOwnerDisplayName() {
        return ownerDisplayName;
    }

    public void setOwnerDisplayName(String ownerDisplayName) {
        this.ownerDisplayName = ownerDisplayName;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getRecommandationCount() {
        return recommandationCount;
    }

    public void setRecommandationCount(Integer recommandationCount) {
        this.recommandationCount = recommandationCount;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public String getPersonneMorale() {
        return personneMorale;
    }

    public void setPersonneMorale(String personneMorale) {
        this.personneMorale = personneMorale;
    }

    public String getSharedFromUser() {
        return sharedFromUser;
    }

    public void setSharedFromUser(String sharedFromUser) {
        this.sharedFromUser = sharedFromUser;
    }

    public String getSharedFromPost() {
        return sharedFromPost;
    }

    public void setSharedFromPost(String sharedFromPost) {
        this.sharedFromPost = sharedFromPost;
    }

    public Integer getSharedCount() {
        return sharedCount;
    }

    public void setSharedCount(Integer sharedCount) {
        this.sharedCount = sharedCount;
    }

    public Set<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(Set<CommentDTO> comments) {
        this.comments = comments;
    }

    public void shared() {
        this.sharedCount++;
    }

    public void liked() {
        this.recommandationCount++;
    }

    public void unliked() {
        this.recommandationCount--;
    }

    public void comment(CommentDTO comment) {
        this.comments.add(comment);
    }

    public List<RecommandationDTO> getRecommandations() {
        return recommandations;
    }

    public void setRecommandations(List<RecommandationDTO> recommandations) {
        this.recommandations = recommandations;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public MediaDTO getMediaDTO() {
        return mediaDTO;
    }

    public void setMediaDTO(MediaDTO mediaDTO) {
        this.mediaDTO = mediaDTO;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public List<String> getGroupId() {
        return groupId;
    }

    public void setGroupId(List<String> groupId) {
        this.groupId = groupId;
    }

    public Boolean getIsApprovedByGroupAdmin() {
        return isApprovedByGroupAdmin;
    }

    public void setIsApprovedByGroupAdmin(Boolean isApprovedByGroupAdmin) {
        this.isApprovedByGroupAdmin = isApprovedByGroupAdmin;
    }

    public String getGroupToShare() {
        return groupToShare;
    }

    public void setGroupToShare(String groupToShare) {
        this.groupToShare = groupToShare;
    }

    public List<String> getUserId() {
        return userId;
    }

    public void setUserId(List<String> userId) {
        this.userId = userId;
    }

    public String getPostOption() {
        return postOption;
    }

    public void setPostOption(String postOption) {
        this.postOption = postOption;
    }

    public List<String> getPreviewToClear() {
        return previewToClear;
    }

    public void setPreviewToClear(List<String> previewToClear) {
        this.previewToClear = previewToClear;
    }

    public Integer getMediaViewCount() {
        return mediaViewCount;
    }

    public void setMediaViewCount(Integer mediaViewCount) {
        this.mediaViewCount = mediaViewCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(clics, comments, creationDate, deleteDate, id, lastEditDate, modifictionDate, ownerAvatarId,
                ownerDisplayName, ownerId, personneMorale, recommandationCount, sharedCount, sharedFromPost,
                sharedFromUser, tags, text, type, viewCount, commentCount, mediaId, groupId, isApprovedByGroupAdmin, groupToShare);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (!(obj instanceof PostDTO)) {
            return false;
        }

        PostDTO other = (PostDTO) obj;

        return Objects.equals(clics, other.clics) && Objects.equals(comments, other.comments)
                && Objects.equals(creationDate, other.creationDate) && Objects.equals(deleteDate, other.deleteDate)
                && Objects.equals(id, other.id) && Objects.equals(lastEditDate, other.lastEditDate)
                && Objects.equals(modifictionDate, other.modifictionDate)
                && Objects.equals(ownerAvatarId, other.ownerAvatarId)
                && Objects.equals(ownerDisplayName, other.ownerDisplayName) && Objects.equals(ownerId, other.ownerId)
                && Objects.equals(personneMorale, other.personneMorale)
                && Objects.equals(recommandationCount, other.recommandationCount)
                && Objects.equals(sharedCount, other.sharedCount)
                && Objects.equals(sharedFromPost, other.sharedFromPost)
                && Objects.equals(sharedFromUser, other.sharedFromUser) && Objects.equals(tags, other.tags)
                && Objects.equals(text, other.text) && Objects.equals(type, other.type)
                && Objects.equals(viewCount, other.viewCount) && Objects.equals(commentCount, other.commentCount)
                && Objects.equals(mediaId, other.mediaId)
                && Objects.equals(groupId, other.groupId)
                && Objects.equals(groupToShare, other.groupToShare)
                && Objects.equals(isApprovedByGroupAdmin, other.isApprovedByGroupAdmin);
    }

}
