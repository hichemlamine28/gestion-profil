package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.type_publication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.type_publication.TypePublicationMapper;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.TypePublication;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.TypePublicationDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.type_publication.TypePublicationRSM;

@Service
public class TypePublicationRSAImpl implements TypePublicationRSA {

	@Autowired
	private TypePublicationRSM typePublicationRSM;

	@Autowired
	private TypePublicationMapper typePublicationMapper;

	@Override
	public List<TypePublicationDTO> findAll() {
		List<TypePublication> typePublications = typePublicationRSM.findAll();
		return typePublicationMapper.typePublicationToTypePublicationDTOs(typePublications);
	}

}
