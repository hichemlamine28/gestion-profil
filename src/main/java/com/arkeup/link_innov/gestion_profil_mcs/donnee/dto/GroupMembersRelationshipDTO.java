package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto;

import java.util.Date;
import java.util.Objects;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.constants.RequestSenderEnum;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.constants.RequestStatus;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;

public class GroupMembersRelationshipDTO extends BaseDTO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UserDTO user;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private GroupDTO group;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date createDate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date requestSentDate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date acceptedDate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date modificationDate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private RequestStatus status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private RequestSenderEnum requestSender;

    private String senderId;

    /**
     * @param user
     * @param group
     * @param createDate
     * @param requestSentDate
     * @param acceptedDate
     * @param modificationDate
     * @param status
     * @param requestDirection
     *
     * @return
     */
    public GroupMembersRelationshipDTO init(UserDTO user, GroupDTO group, Date createDate, Date requestSentDate,
            Date acceptedDate, Date modificationDate, RequestStatus status, RequestSenderEnum requestSender) {
        this.user = user;
        this.group = group;
        this.createDate = createDate;
        this.requestSentDate = requestSentDate;
        this.acceptedDate = acceptedDate;
        this.modificationDate = modificationDate;
        this.status = status;
        this.requestSender = requestSender;

        return this;
    }

    /**
     * Use this to init sent request
     *
     * @param user
     * @param group
     *
     * @return
     */
    public GroupMembersRelationshipDTO sentRequest(UserDTO user, GroupDTO group) {
        return this.init(user, group, new Date(), new Date(), null, null, RequestStatus.PENDING,
                RequestSenderEnum.USER);
    }

    /**
     * Use this to init invite request
     *
     * @param user
     * @param group
     *
     * @return
     */
    public GroupMembersRelationshipDTO invite(UserDTO user, GroupDTO group) {
        return this.init(user, group, new Date(), new Date(), null, null, RequestStatus.PENDING,
                RequestSenderEnum.GROUP);
    }

    /**
     * Use this to init accepted request
     *
     * @param user
     * @param group
     *
     * @return
     */
    public GroupMembersRelationshipDTO accept(UserDTO user, GroupDTO group) {
        return this.init(user, group, new Date(), new Date(), new Date(), null, RequestStatus.ACCEPTED,
                RequestSenderEnum.GROUP);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public GroupDTO getGroup() {
        return group;
    }

    public void setGroup(GroupDTO group) {
        this.group = group;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getRequestSentDate() {
        return requestSentDate;
    }

    public void setRequestSentDate(Date requestSentDate) {
        this.requestSentDate = requestSentDate;
    }

    public Date getAcceptedDate() {
        return acceptedDate;
    }

    public void setAcceptedDate(Date acceptedDate) {
        this.acceptedDate = acceptedDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public RequestSenderEnum getRequestSender() {
        return requestSender;
    }

    public void setRequestSender(RequestSenderEnum requestSender) {
        this.requestSender = requestSender;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(acceptedDate, createDate, group, id, modificationDate, requestSender, requestSentDate,
                status, user);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof GroupMembersRelationshipDTO)) {
            return false;
        }
        GroupMembersRelationshipDTO other = (GroupMembersRelationshipDTO) obj;
        return Objects.equals(acceptedDate, other.acceptedDate) && Objects.equals(createDate, other.createDate)
                && Objects.equals(group, other.group) && Objects.equals(id, other.id)
                && Objects.equals(modificationDate, other.modificationDate) && requestSender == other.requestSender
                && Objects.equals(requestSentDate, other.requestSentDate) && status == other.status
                && Objects.equals(user, other.user);
    }

}
