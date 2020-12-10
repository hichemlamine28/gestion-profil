package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.productions.patent;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.PatentDTO;

public interface PatentRSA {

	public Page<PatentDTO> getByOwnerId(String ownerId, Pageable pageable);

	public PatentDTO getById(String patentId);

	public int getNumberPatentByOwnerId(String ownerId);

	public List<PatentDTO> findAllByOwnerId(String ownerId);

	public Boolean publicationNumberIsAlreadyExist(String publicationNumber, String ownerId);
}
