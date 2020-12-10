package com.arkeup.link_innov.gestion_profil_mcs.service.businessdelegate;

import java.util.List;

import javax.validation.Valid;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.businessdelegate.ReseauSocialUserDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.businessdelegate.ReseauSocialUsersDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.CustomPageDTO;

/**
 * @author Stéphan R.
 *
 */
@FeignClient(name = "ReseauxSociauxClient", url = "#{'${reseau-social.mcs.url}'}")
public interface ReseauxSociauxMCS {

    /**
     * @param productionId
     *
     * @return
     */
    @PostMapping(path = "/production/{production_id}/add")
    ProductionsDTO relies(@PathVariable("production_id") String productionId);

    /**
     * @param productionId
     *
     * @return
     */
    @DeleteMapping(path = "/production/{production_id}/delete")
    IsDeletedDTO delete(@PathVariable("production_id") String productionId);

    /**
     * check link of user
     *
     * @param second_user_id
     * @return
     */
    @GetMapping(path = "/contacts/relationShip/{secondUserId}")
    RelationShipDTO checkRelationShip(@PathVariable("secondUserId") String secondUserId);

    /**
     *
     * @param corporationId
     * @param userId
     * @return
     */
    @PostMapping(path = "/certification/{corporation_id}/{user_id}")
    public CorporationDTO certifyUser(@PathVariable("corporation_id") String corporationId,
            @PathVariable("user_id") String userId);

    /**
     *
     * @param corporationId
     * @param userId
     * @return
     */
    @DeleteMapping(path = "/certification/{corporation_id}/{user_id}")
    public CorporationDTO deleteCertificationByUser(@PathVariable("corporation_id") String corporationId,
            @PathVariable("user_id") String userId);

    /**
     *
     * @param corporationId
     * @return
     */
    @GetMapping(path = "/certification/{corporation_id}")
    public List<ReseauSocialUserDTO> getCertifiedUsers(@PathVariable("corporation_id") String corporationId);

    /**
     *
     * @param corporationDTO
     * @return
     */
    @PostMapping(path = "/corporation", consumes = {"application/json"})
    public CorporationDTO create(@RequestBody CorporationDTO corporationDTO);

    /**
     *
     * @param corporationId
     * @param users
     * @return
     */
    @PostMapping(path = "/corporation/add/admins")
    public CorporationDTO addAdmins(@RequestParam("corporationId") String corporationId, @RequestParam("users") List<String> users);

    /**
     *
     * @param corporationId
     * @param admin list
     * @return
     */
    @DeleteMapping(path = "/corporation/delete/admins")
    public CorporationDTO deleteAdmins(@RequestParam("corporationId") String corporationId, @RequestParam("admins") List<String> admins);

    /**
     *
     * @param corporationId
     * @return
     */
    @DeleteMapping(path = "/corporation/{corporation_id}")
    public CorporationDTO deleteCorporation(@PathVariable("corporation_id") String corporationId);

    /**
     *
     * @param userDTO
     * @return
     */
    @PostMapping("/user/create")
    public ReseauSocialUserDTO saveUser(@RequestBody ReseauSocialUserDTO userDTO);

    /**
     *
     * @param groupDTO
     * @return
     */
    @PostMapping(path = "/group/add", consumes = {"application/json"})
    public GroupDTO createGroup(@RequestBody GroupDTO groupDTO);

    /**
     *
     * @param corporationId
     * @return
     */
    @DeleteMapping(path = "/group/delete/{group_id}")
    public GroupDTO deleteGroup(@PathVariable("group_id") String groupId);

    /**
     *
     * @param groupId
     * @return
     */
    @GetMapping(value = "/group/member/count/{group_id}")
    public MemberNumbersDTO getGroupMembersCount(@PathVariable("group_id") String groupId);

    /**
     *
     * @return
     */
    @GetMapping(value = "/group/user/member")
    public List<String> getGroupsUserIsMember();

    /**
     * @param userName
     * @return
     */
    @GetMapping(value = "/group/user/member/{username}")
    public List<String> getGroupsUserIsMemberByUserName(@PathVariable("username") String userName);

    /**
     *
     * @param ownerDisplayName
     * @return
     */
    @PutMapping(path = "/timeline/post/owner_name/{owner_display_name}")
    public Boolean updatePostOwnerDisplayNameByUser(@PathVariable("owner_display_name") String ownerDisplayName);

    /**
     *
     * @param ownerDisplayName
     * @return
     */
    @PutMapping(path = "/timeline/post/comment/owner_name/{owner_display_name}")
    public Boolean updateCommentOwnerDisplayNameByUser(@PathVariable("owner_display_name") String ownerDisplayName);

    /**
     *
     * @param ownerDisplayName
     * @return
     */
    @PutMapping(path = "/timeline/post/recommandation/owner_name/{owner_display_name}")
    public Boolean updateRecommandationOwnerDisplayNameByUser(
            @PathVariable("owner_display_name") String ownerDisplayName);

    /**
     *
     * @param corporationId
     * @param corporationName
     * @return
     */
    @PutMapping(path = "/timeline/post/corporation_name/{corporation_id}/{corporation_name}")
    public Boolean updateAllPostsCorporationName(@PathVariable("corporation_id") String corporationId,
            @PathVariable("corporation_name") String corporationName);

    /**
     * /contacts
     *
     * @param corporationId
     * @param corporationName
     * @return
     */
    @PutMapping(path = "/timeline/post/comment/corporation_name/{corporation_id}/{corporation_name}")
    public Boolean updateAllCommentsCorporationName(@PathVariable("corporation_id") String corporationId,
            @PathVariable("corporation_name") String corporationName);

    /**
     *
     * @param corporationId
     * @param corporationName
     * @return
     */
    @PutMapping(path = "/timeline/post/recommandation/corporation_name/{corporation_id}/{corporation_name}")
    public Boolean updateAllRecommandationsCorporationName(@PathVariable("corporation_id") String corporationId,
            @PathVariable("corporation_name") String corporationName);

    /**
     *
     * @param postDTO
     * @param isMembersAllowedToPost
     * @param postValidationrequired
     * @return
     */
    @PostMapping(path = "/timeline/post/group/publish")
    @ResponseBody
    public PostDTO publishInGroup(@Valid @RequestBody PostDTO postDTO,
            @RequestParam("isMembersAllowedToPost") Boolean isMembersAllowedToPost,
            @RequestParam("postValidationrequired") Boolean postValidationrequired);

    /**
     *
     * @param corporationId
     * @return
     */
    @GetMapping(path = "/corporation/permission")
    public CorporationAdminDTO hasPermission(@RequestParam("corporationId") String corporationId);

    /**
     *
     * @param profilId
     * @return
     */
    @GetMapping(path = "/contacts/permission")
    public ProfilAdminDTO isOwner(@RequestParam("profilId") String profilId);

    /**
     *
     * @param userId
     * @return
     */
    @GetMapping(path = "/certification/user/{userId}")
    public HasCertificationDTO isCertifiedUsers(@PathVariable("userId") String userId);

    /**
     * @param postDTO
     *
     * @return
     */
    @PostMapping(path = "/timeline/post/publish")
    PostDTO createPost(@RequestBody PostDTO postDTO);

    /**
     * @param postDTO
     *
     * @return
     */
    @PostMapping(path = "/timeline/post/group/publish")
    PostDTO createPostGroup(@RequestBody PostDTO postDTO);

    /**
     *
     * @param list ids
     * @return
     */
    @PutMapping(path = "/timeline/post/getPosts")
    public CustomPageDTO<PostDTO> getPosts(@RequestBody ListIdDTO postIds, @RequestParam Pageable pageable);

    /**
     *
     * @param userId
     * @return
     */
    @GetMapping(path = "/contacts/isFriend/{user_id}")
    public IsFriendDTO isFriend(@PathVariable("user_id") String userId);

    /**
     *
     * @param corporationId
     * @return
     */
    @GetMapping(path = "/corporation/isFollow/{corporationId}")
    public IsFollowerDTO isFollowedByUser(@PathVariable("corporationId") String corporationId);

    /**
     *
     * @param pageable
     * @return
     */
    @GetMapping(path = "/contacts/list")
    public ReseauSocialUsersDTO getContacts(@RequestHeader("Authorization") String token,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size,
            @RequestParam(value = "sort", required = false) List<String> sort);

    @GetMapping(path = "/contacts/request/sender")
    public RelationShipDTO sendContactRequest(@RequestParam("senderId") String senderId, @RequestParam("receiverId") String receiverId);

    /**
     *
     * @param groupId
     * @return
     */
    @GetMapping(path = "/timeline/post/getListGroupPost")
    public List<PostDTO> getListGroupPost(@RequestParam("groupId") String groupId);

    /**
     *
     * @param postId
     * @return
     */
    @GetMapping(path = "/timeline/post/{postId}")
    public PostDTO getPost(@PathVariable("postId") String postId);

    /**
     *
     * @param postIds
     * @param filter
     * @param pageable
     * @return
     */
    @PutMapping(path = "/timeline/post/getPaginatedPosts")
    public CustomPageDTO<PostDTO> getPaginatedPosts(
            @RequestBody ListIdDTO postIds,
            @RequestParam String filter,
            @RequestParam Pageable pageable);

    /**
     *
     * @param userId
     * @param groupId
     * @return
     */
    @GetMapping(value = "/group/user/{user_Id}/isMember/{group_id}")
    public IsMemberDTO isUserMemberOfTheGroup(@PathVariable("user_Id") String userId,
                                              @PathVariable("group_id") String groupId);

    /**
     *
     * @param groupId
     * @param userId
     * @return
     */
    @PutMapping(path = "/group/add/{user_id}/member/{group_id}")
    public GroupMembersRelationshipDTO addUserToMember(@PathVariable("group_id") String groupId,
                                                       @PathVariable("user_id") String userId);
}
