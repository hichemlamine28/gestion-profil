package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.user_auth;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.UserAuthDTO;

/**
 * Created by bgh on 18/10/18.
 */
public interface UserAuthCUDSA {

	public UserAuthDTO create(UserAuthDTO userLdapDto);
	public UserAuthDTO getUserDetails(String username);
	public UserAuthDTO updatePassword(UserAuthDTO userAuthDTO,Boolean validateAccount);
	public UserAuthDTO choosePassword(UserAuthDTO userAuthDTO,Boolean validateAccount);
	public UserAuthDTO validateAccount(UserAuthDTO userAuthDTO);
	public UserAuthDTO findUserAuthByUserName(String userName);
	public void initUserTestPremiumAuth();
}
