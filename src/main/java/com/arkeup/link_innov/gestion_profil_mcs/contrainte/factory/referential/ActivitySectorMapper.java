package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.referential;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.ActivitySector;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.ActivitySectorDTO;

@Mapper
public interface ActivitySectorMapper {
	
	ActivitySectorDTO activitySectorToActivitySectorDTO(ActivitySector activitySector);
	ActivitySector activitySectorDTOToActivitySector(ActivitySectorDTO activitySectorDTO);
	List<ActivitySectorDTO> activitySectorToActivitySectorDTOs(List<ActivitySector> activitySectors);
	List<ActivitySector> activitySectorDTOToActivitySectors(List<ActivitySectorDTO> activitySectorDTOs);
	
	ActivitySectorMapper MAPPER = Mappers.getMapper(ActivitySectorMapper.class);
	
	default Page<ActivitySectorDTO> activitySectorPageToActivitySectorDTOPage(Page<ActivitySector> activitySectorPage, Pageable pageable) {

		List<ActivitySectorDTO> activitySectorDtos = MAPPER.activitySectorToActivitySectorDTOs(activitySectorPage.getContent());
		Page<ActivitySectorDTO> activitySectorDTOPage = new PageImpl<>(activitySectorDtos, pageable, activitySectorPage.getTotalElements());
		return activitySectorDTOPage;
	}

}
