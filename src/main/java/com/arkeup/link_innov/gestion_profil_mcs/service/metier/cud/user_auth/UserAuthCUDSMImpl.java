package com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.user_auth;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserAuth;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserConnexion;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.ldap.UserAuthLdapRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.oauth2.UserConnexionRepository;

/**
 * Created by bgh on 18/10/18.
 */
@Service
public class UserAuthCUDSMImpl implements UserAuthCUDSM {

    @Autowired
    private UserAuthLdapRepository userAuthLdapRepository;
    
    @Autowired
	private UserConnexionRepository userConnexionRepository;
    
    @Override
    public UserAuth create(UserAuth newUserAuth){
        return userAuthLdapRepository.save(newUserAuth);
    }

	@Override
	public void deleteByUsername(String username) {
		UserAuth userAuth = userAuthLdapRepository.findByUsername(username);
		if(userAuth != null) {
			userAuthLdapRepository.delete(userAuth);
		}
	}

	@Override
	public UserAuth findByUsername(String username) {
		return userAuthLdapRepository.findByUsername(username);
	}

	@Override
	public UserAuth findByPseudonName(String pseudoName) {
		return userAuthLdapRepository.findByPseudoName(pseudoName);
	}

	@Override
	public Boolean isPseudoExist(String pseudoName) {
		if (userAuthLdapRepository.findByPseudoName(pseudoName) !=null) return true;
		return false;
	}

	@Override
	public Boolean isMailExist(String mail) {
		if (userAuthLdapRepository.findByMail(mail) !=null) return true;
		return false;
	}
        
	@Override
	public UserAuth findByMail(String mail) {
		return userAuthLdapRepository.findByMail(mail);
	}

	@Override
	public void userAuthLastConnexion(String username) {

		UserConnexion userConnexion = new UserConnexion();
		UserConnexion userLogged = userConnexionRepository.findByUsername(username);	

		if (userLogged == null) {
			userConnexion.setId(username);
			userConnexion.setNewConnexion(new Date());
			userConnexion.setOldConnexion(new Date());
			userConnexion.setUsername(username);
			userConnexionRepository.save(userConnexion);
		}
		else {
			userLogged.setOldConnexion(userLogged.getNewConnexion());
			userLogged.setNewConnexion(new Date());
			userConnexionRepository.save(userLogged);
		}

	}

	@Override
	public Date getDateOflastConnexion(String username) {
		UserConnexion userLogged = userConnexionRepository.findByUsername(username);	 
		if (userLogged != null) {
			return userLogged.getNewConnexion();
		}
		return null;
	}    
}
