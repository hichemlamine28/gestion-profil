package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.user_auth;


import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.UserAuthDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.CustomPageDTO;

/**
 * Created by bgh on 18/10/18.
 */
public interface UserAuthRSA {

	public UserAuthDTO findByUserName(String username);

	public CustomPageDTO<String> findAllAdmin(int page, int size); // Benj 20/12/2018

}
