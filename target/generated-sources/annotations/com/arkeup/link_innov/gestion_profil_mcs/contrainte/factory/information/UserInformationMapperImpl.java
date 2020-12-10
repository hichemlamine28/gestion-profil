package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.information;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserInformation;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.UserInformationDTO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class UserInformationMapperImpl implements UserInformationMapper {

    @Override
    public UserInformationDTO userInformationToUserInformationDTO(UserInformation userInformation) {
        if ( userInformation == null ) {
            return null;
        }

        UserInformationDTO userInformationDTO = new UserInformationDTO();

        userInformationDTO.setId( userInformation.getId() );
        userInformationDTO.setUsername( userInformation.getUsername() );
        userInformationDTO.setFirstname( userInformation.getFirstname() );
        userInformationDTO.setLastname( userInformation.getLastname() );
        userInformationDTO.setEmail( userInformation.getEmail() );
        userInformationDTO.setPassword( userInformation.getPassword() );
        userInformationDTO.setPhoto( userInformation.getPhoto() );

        return userInformationDTO;
    }

    @Override
    public UserInformation userInformationDTOToUserInformation(UserInformationDTO userInformationDTO) {
        if ( userInformationDTO == null ) {
            return null;
        }

        UserInformation userInformation = new UserInformation();

        userInformation.setId( userInformationDTO.getId() );
        userInformation.setUsername( userInformationDTO.getUsername() );
        userInformation.setFirstname( userInformationDTO.getFirstname() );
        userInformation.setLastname( userInformationDTO.getLastname() );
        userInformation.setEmail( userInformationDTO.getEmail() );
        userInformation.setPassword( userInformationDTO.getPassword() );
        userInformation.setPhoto( userInformationDTO.getPhoto() );

        return userInformation;
    }
}
