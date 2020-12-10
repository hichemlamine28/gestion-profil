package com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.productions.patent;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Patent;

public interface PatentRSM {

	public Page<Patent> getByOwnerId(String ownerId, Pageable pageable);

	public Patent getById(String patentId);

	public int getNumberPatentByOwnerId(String ownerId);

	public List<Patent> findAllByOwnerId(String ownerId);

	public Boolean publicationNumberIsAlreadyExist(String publicationNumber, String ownerId);
}
