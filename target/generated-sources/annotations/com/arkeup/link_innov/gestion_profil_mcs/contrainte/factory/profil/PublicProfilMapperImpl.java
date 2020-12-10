package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.profil;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Profil;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.MediaDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.PublicProfilDTO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class PublicProfilMapperImpl implements PublicProfilMapper {

    @Override
    public PublicProfilDTO profilToProfilPublicDTO(Profil profil) {
        if ( profil == null ) {
            return null;
        }

        PublicProfilDTO publicProfilDTO = new PublicProfilDTO();

        publicProfilDTO.setMediaDTO( profilToMediaDTO( profil ) );
        publicProfilDTO.setBackgroundDTO( profilToMediaDTO1( profil ) );
        publicProfilDTO.setError( profil.isError() );
        publicProfilDTO.setInfo( profil.isInfo() );
        publicProfilDTO.setWarning( profil.isWarning() );
        publicProfilDTO.setErrorCode( profil.getErrorCode() );
        publicProfilDTO.setErrorMessage( profil.getErrorMessage() );
        publicProfilDTO.setMessage( profil.getMessage() );
        publicProfilDTO.setUuid( profil.getUuid() );
        publicProfilDTO.setId( profil.getId() );
        publicProfilDTO.setFirstname( profil.getFirstname() );
        publicProfilDTO.setLastname( profil.getLastname() );
        publicProfilDTO.setOccupation( profil.getOccupation() );
        publicProfilDTO.setResume( profil.getResume() );

        return publicProfilDTO;
    }

    protected MediaDTO profilToMediaDTO(Profil profil) {
        if ( profil == null ) {
            return null;
        }

        MediaDTO mediaDTO = new MediaDTO();

        mediaDTO.setId( profil.getMediaId() );

        return mediaDTO;
    }

    protected MediaDTO profilToMediaDTO1(Profil profil) {
        if ( profil == null ) {
            return null;
        }

        MediaDTO mediaDTO = new MediaDTO();

        mediaDTO.setId( profil.getBackgroundId() );

        return mediaDTO;
    }
}
