package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.user_auth;

import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserAuth;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.UserAuthDTO;

@Mapper
public interface UserAuthMapper {

	UserAuthDTO userAuthToUserAuthDTO(UserAuth userAuth);

    List<UserAuthDTO> userAuthListToUserAuthDTOList(List<UserAuth> userAuth);

    @InheritInverseConfiguration
    UserAuth userAuthDTOtoUserAuth(UserAuthDTO userAuthDto);
    
}
