package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;

public class CommentDTO extends BaseDTO {

	private String id;
	private String avatarOwnerAdress;
	private String commentText;
	private Date creationDate;
	private Date modificationDate;
	private String ownerFullName;
	private String ownerIdentifier;
	private String postId;
	private String replyTo;
	private Double score;
	private Set<CommentDTO> replies = new HashSet<>();
	private Integer replyCount = 0;
	private List<RecommandationDTO> recommandations = new ArrayList<>();
	private Integer recommandationCount = 0;
	private String personneMorale;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAvatarOwnerAdress() {
		return avatarOwnerAdress;
	}

	public void setAvatarOwnerAdress(String avatarOwnerAdress) {
		this.avatarOwnerAdress = avatarOwnerAdress;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
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

	public String getOwnerFullName() {
		return ownerFullName;
	}

	public void setOwnerFullName(String ownerFullName) {
		this.ownerFullName = ownerFullName;
	}

	public String getOwnerIdentifier() {
		return ownerIdentifier;
	}

	public void setOwnerIdentifier(String ownerIdentifier) {
		this.ownerIdentifier = ownerIdentifier;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getReplyTo() {
		return replyTo;
	}

	public void setReplyTo(String replyTo) {
		this.replyTo = replyTo;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Set<CommentDTO> getReplies() {
		return replies;
	}

	public void setReplies(Set<CommentDTO> replies) {
		this.replies = replies;
	}

	public Integer getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}

	public List<RecommandationDTO> getRecommandations() {
		return recommandations;
	}

	public void setRecommandations(List<RecommandationDTO> recommandations) {
		this.recommandations = recommandations;
	}

	public Integer getRecommandationCount() {
		return recommandationCount;
	}

	public void setRecommandationCount(Integer recommandationCount) {
		this.recommandationCount = recommandationCount;
	}

	public void liked() {
		this.recommandationCount++;
	}

	public void unliked() {
		this.recommandationCount--;
	}

	public String getPersonneMorale() {
		return personneMorale;
	}

	public void setPersonneMorale(String personneMorale) {
		this.personneMorale = personneMorale;
	}

	@Override
	public int hashCode() {
		return Objects.hash(avatarOwnerAdress, commentText, creationDate, id, modificationDate, ownerFullName,
				ownerIdentifier, personneMorale, postId, recommandationCount, recommandations, replies, replyCount,
				replyTo, score);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof CommentDTO)) {
			return false;
		}
		CommentDTO other = (CommentDTO) obj;
		return Objects.equals(avatarOwnerAdress, other.avatarOwnerAdress)
				&& Objects.equals(commentText, other.commentText) && Objects.equals(creationDate, other.creationDate)
				&& Objects.equals(id, other.id) && Objects.equals(modificationDate, other.modificationDate)
				&& Objects.equals(ownerFullName, other.ownerFullName)
				&& Objects.equals(ownerIdentifier, other.ownerIdentifier)
				&& Objects.equals(personneMorale, other.personneMorale) && Objects.equals(postId, other.postId)
				&& Objects.equals(recommandationCount, other.recommandationCount)
				&& Objects.equals(recommandations, other.recommandations) && Objects.equals(replies, other.replies)
				&& Objects.equals(replyCount, other.replyCount) && Objects.equals(replyTo, other.replyTo)
				&& Objects.equals(score, other.score);
	}

}
