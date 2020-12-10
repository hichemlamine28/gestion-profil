package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.user_auth;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserAuth;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.UserAuthDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class UserAuthMapperImpl implements UserAuthMapper {

    @Override
    public UserAuthDTO userAuthToUserAuthDTO(UserAuth userAuth) {
        if ( userAuth == null ) {
            return null;
        }

        UserAuthDTO userAuthDTO = new UserAuthDTO();

        userAuthDTO.setUsername( userAuth.getUsername() );
        userAuthDTO.setPassword( userAuth.getPassword() );
        userAuthDTO.setFullName( userAuth.getFullName() );
        List<String> list = userAuth.getRoles();
        if ( list != null ) {
            userAuthDTO.setRoles( new ArrayList<String>( list ) );
        }
        else {
            userAuthDTO.setRoles( null );
        }
        userAuthDTO.setMail( userAuth.getMail() );
        userAuthDTO.setPseudoName( userAuth.getPseudoName() );

        return userAuthDTO;
    }

    @Override
    public List<UserAuthDTO> userAuthListToUserAuthDTOList(List<UserAuth> userAuth) {
        if ( userAuth == null ) {
            return null;
        }

        List<UserAuthDTO> list = new ArrayList<UserAuthDTO>( userAuth.size() );
        for ( UserAuth userAuth1 : userAuth ) {
            list.add( userAuthToUserAuthDTO( userAuth1 ) );
        }

        return list;
    }

    @Override
    public UserAuth userAuthDTOtoUserAuth(UserAuthDTO userAuthDto) {
        if ( userAuthDto == null ) {
            return null;
        }

        UserAuth userAuth = new UserAuth();

        userAuth.setUsername( userAuthDto.getUsername() );
        userAuth.setPassword( userAuthDto.getPassword() );
        userAuth.setFullName( userAuthDto.getFullName() );
        List<String> list = userAuthDto.getRoles();
        if ( list != null ) {
            userAuth.setRoles( new ArrayList<String>( list ) );
        }
        else {
            userAuth.setRoles( null );
        }
        userAuth.setMail( userAuthDto.getMail() );
        userAuth.setPseudoName( userAuthDto.getPseudoName() );

        return userAuth;
    }
}
