package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.information;

import org.mapstruct.Mapper;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserInformation;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.UserInformationDTO;

@Mapper
public interface UserInformationMapper {

	UserInformationDTO userInformationToUserInformationDTO(UserInformation userInformation);

	UserInformation userInformationDTOToUserInformation (UserInformationDTO userInformationDTO);

}