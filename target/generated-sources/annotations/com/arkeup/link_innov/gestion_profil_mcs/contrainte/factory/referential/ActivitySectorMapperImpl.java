package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.referential;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.ActivitySector;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.ActivitySectorDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class ActivitySectorMapperImpl implements ActivitySectorMapper {

    @Override
    public ActivitySectorDTO activitySectorToActivitySectorDTO(ActivitySector activitySector) {
        if ( activitySector == null ) {
            return null;
        }

        ActivitySectorDTO activitySectorDTO = new ActivitySectorDTO();

        activitySectorDTO.setId( activitySector.getId() );
        activitySectorDTO.setLabel( activitySector.getLabel() );

        return activitySectorDTO;
    }

    @Override
    public ActivitySector activitySectorDTOToActivitySector(ActivitySectorDTO activitySectorDTO) {
        if ( activitySectorDTO == null ) {
            return null;
        }

        ActivitySector activitySector = new ActivitySector();

        activitySector.setId( activitySectorDTO.getId() );
        activitySector.setLabel( activitySectorDTO.getLabel() );

        return activitySector;
    }

    @Override
    public List<ActivitySectorDTO> activitySectorToActivitySectorDTOs(List<ActivitySector> activitySectors) {
        if ( activitySectors == null ) {
            return null;
        }

        List<ActivitySectorDTO> list = new ArrayList<ActivitySectorDTO>( activitySectors.size() );
        for ( ActivitySector activitySector : activitySectors ) {
            list.add( activitySectorToActivitySectorDTO( activitySector ) );
        }

        return list;
    }

    @Override
    public List<ActivitySector> activitySectorDTOToActivitySectors(List<ActivitySectorDTO> activitySectorDTOs) {
        if ( activitySectorDTOs == null ) {
            return null;
        }

        List<ActivitySector> list = new ArrayList<ActivitySector>( activitySectorDTOs.size() );
        for ( ActivitySectorDTO activitySectorDTO : activitySectorDTOs ) {
            list.add( activitySectorDTOToActivitySector( activitySectorDTO ) );
        }

        return list;
    }
}
