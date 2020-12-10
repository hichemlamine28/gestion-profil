package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.group;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.*;

/**
 *
 * @author bona
 */
public interface GroupCUDSA {

	public GroupDTO create(String username, GroupDTO groupDTO);

	public GroupDTO update(GroupDTO groupDTO);

	public GroupDTO delete(String idGroup);

	public MediaDTO generatePictureToken(String groupId);

	public PostDTO publishInGroup(PostDTO postDTO);

	public GroupDTO updatePicture(String groupId, MediaDTO mediaDTO);

	public GroupDTO share(String userName, String groupId, PostDTO postDTO);
	
	public GroupDTO updateBackground(String groupId, MediaDTO mediaDTO);

	public GroupDTO deleteBackground(String groupId);
	
	public GroupDTO deletePicture(String groupId);

	GroupDTO setGroupHasMedia(GroupHasMediaDTO groupHasMediaDTO);

	boolean updateHasMediaGroup();

	RepairRightsResultDTO repairGroupAdminRights();
}
