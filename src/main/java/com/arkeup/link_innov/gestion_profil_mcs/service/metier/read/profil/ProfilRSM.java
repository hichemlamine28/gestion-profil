package com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.profil;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Profil;

/**
 *
 * @author bona
 */
public interface ProfilRSM {

    Profil getInformation(String username);

    public Page<Profil> getContactInformationsByIds(List<String> ids, String type, String filter, Pageable pageable);

    public List<Profil> getProfilsInformationsByIds(List<String> userIds);

    public Page<Profil> getPaginatedProfilsInformationsByIds(List<String> userIds, Pageable pageable);

    public Page<Profil> getNewSubscribedUsers(Pageable pageable);

    Profil getProfilById(String id);

    Page<Profil> getPaginatedProfilsInformations(List<String> userIds, String filter, String categorie, Pageable pageable);

    Boolean isExistMail(String mail);

    List<Profil> findAll();

	List<Profil> getProfilsInformationsFirstName(String firstName);

	List<Profil> getBykeyValidateProfil(String keyValidateProfil);

	Collection<? extends Profil> getListProfilByFirstNameConcatLastName(String concat);

	long count();

	Collection<? extends Profil> findLastCreatedProfil();
}
