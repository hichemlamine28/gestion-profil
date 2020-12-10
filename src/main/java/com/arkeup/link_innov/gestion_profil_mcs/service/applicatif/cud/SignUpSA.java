/**
 *
 */
package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud;

import java.util.List;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.IsLinkValidDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.IsMailSendDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.MediaDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProfilDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.UserAuthDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.inscription.InscriptionDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.inscription.SignUpDTO;

/**
 * @author mikajy
 *
 */
public interface SignUpSA {

	public ProfilDTO doSignUp(SignUpDTO dto);

	public ProfilDTO doSignUp(InscriptionDTO dto);

	public UserAuthDTO choosePassword(UserAuthDTO userAuthDTO, Boolean validateAccount);

	public UserAuthDTO updatePassword(UserAuthDTO userAuthDTO, Boolean validateAccount);

	public UserAuthDTO retrievePassword(UserAuthDTO userAuthDTO);

	public UserAuthDTO updateMail(String userName, UserAuthDTO userAuthDTO);

	public UserAuthDTO validateAccount(UserAuthDTO userAuthDTO);

	public MediaDTO generatePictureToken(String userId);

	public void importBetaTesteurs(List<SignUpDTO> signUpDTOS);

	IsMailSendDTO reSendMailValidate(String userId);

	ProfilDTO importBetaTestInLandinPage(SignUpDTO signUpDTO);

	IsLinkValidDTO isLinkValid(String keyValidateProfil);
}
