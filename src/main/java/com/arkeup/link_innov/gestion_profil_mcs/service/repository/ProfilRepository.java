package com.arkeup.link_innov.gestion_profil_mcs.service.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Profil;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.common.mongo_es.CommonMongoToESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.elastic_search.ProfilESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.ProfilMongoRepository;

/**
 *
 * @author bona
 */
public interface ProfilRepository
        extends CommonMongoToESRepository<Profil, String, ProfilMongoRepository, ProfilESRepository> {

    public Profil getInformation(String username);

    public Page<Profil> getContactInformationsByIds(List<String> ids, String type, String filter, Pageable pageable);

    public String findUserNameByProfileId(String id);

    public List<Profil> getProfilsInformationsByIds(List<String> userIds);

    public Page<Profil> getProfilsInformationsByIds(List<String> userIds, Pageable pageable);

    public Page<Profil> getNewSubscribedUsers(List<String> admins, Pageable pageable);

    Page<Profil> getProfilsInformationsByIds(List<String> ids, String filter, String categorie, Pageable pageable);

    Boolean isExistMail(String mail);

    List<Profil> findAll();

	public List<Profil> findByFirstName(String firstName);

	List<Profil> getBykeyValidateProfil(String keyValidateProfil);

	public Collection<? extends Profil> findByFirstNameConcatLastName(String concat);

	public Collection<? extends Profil> findLastCreatedProfil();
}
