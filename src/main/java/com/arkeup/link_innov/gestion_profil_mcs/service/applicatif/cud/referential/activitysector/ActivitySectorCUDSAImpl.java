package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.referential.activitysector;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ErrorsEnum;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.referential.ActivitySectorMapper;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.ActivitySector;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.ActivitySectorDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.referential.activitysector.ActivitySectorCUDSM;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.referential.activitysector.ActivitySectorRSM;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.ActivitySectorRepository;

@Service
public class ActivitySectorCUDSAImpl implements ActivitySectorCUDSA {

	@Autowired
	private ActivitySectorCUDSM activitySectorCUDSM;

	@Autowired
	private ActivitySectorRSM activitySectorRSM;

	@Autowired
	private ActivitySectorMapper activitySectorFactory;

	@Autowired
	private ActivitySectorRepository activitySectorRepository;

	@Override
	public ActivitySectorDTO addActivitySector(ActivitySectorDTO activitySectorDTO) {

		if (StringUtils.isEmpty(activitySectorDTO.getLabel())) {
			activitySectorDTO.setError(true);
			activitySectorDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0043.getErrorMessage());
			activitySectorDTO.setErrorCode("ERR_MCS_PROFIL_0043");
			return activitySectorDTO;
		}

		ActivitySector result = activitySectorCUDSM
				.create(activitySectorFactory.activitySectorDTOToActivitySector(activitySectorDTO));

		if (result.getId() == null) {
			activitySectorDTO.setError(true);
			activitySectorDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0043.getErrorMessage());
			activitySectorDTO.setErrorCode("ERR_MCS_PROFIL_0043");
		} else {
			activitySectorDTO.setError(false);
			activitySectorDTO.setMessage("Activity sector added");
			activitySectorDTO.setId(result.getId());
		}

		return activitySectorDTO;
	}

	@Override
	public ActivitySectorDTO updateActivitySector(ActivitySectorDTO activitySectorDTO) {

		if (StringUtils.isEmpty(activitySectorDTO.getLabel())) {
			activitySectorDTO.setError(true);
			activitySectorDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0043.getErrorMessage());
			activitySectorDTO.setErrorCode("ERR_MCS_PROFIL_0043");
			return activitySectorDTO;
		}

		activitySectorCUDSM.update(activitySectorFactory.activitySectorDTOToActivitySector(activitySectorDTO));
		activitySectorDTO.setError(false);
		activitySectorDTO.setMessage("Activity sector updated");
		return activitySectorDTO;
	}

	@Override
	public ActivitySectorDTO deleteActivitySector(String idActivitySector) {
		Optional<ActivitySector> res = activitySectorRSM.findById(idActivitySector);

		ActivitySectorDTO result = new ActivitySectorDTO();
		result.setId(idActivitySector);
		if (res.isPresent()) {
			activitySectorCUDSM.delete(idActivitySector);
			result.setError(false);
			result.setMessage("Activity sector deleted");
		} else {
			result.setError(true);
			result.setErrorMessage(ErrorsEnum.ERR_MCS_OBJECT_NOT_FOUND.getErrorMessage());
			result.setErrorCode("ERR_MCS_OBJECT_NOT_FOUND");
		}
		return result;
	}

	@Override
	public void initDatabase() {

		List<ActivitySector> activitySectors = new ArrayList<>();

		ActivitySector activitySector = new ActivitySector();
		activitySector.setId("uuid-activity-sector-assainissement");
		activitySector.setLabel("Industrie de l'assainissement, gestion des déchets et dépollution");
		activitySectors.add(activitySector);
		
		activitySector = new ActivitySector();
		activitySector.setId("uuid-activity-sector-auto-constructeurs");
		activitySector.setLabel("Industrie de l'automobile (constructeurs)");
		activitySectors.add(activitySector);
		
		activitySector = new ActivitySector();
		activitySector.setId("uuid-activity-sector-auto-equipments");
		activitySector.setLabel("Industrie de l'automobile (équipements pour véhicules)");
		activitySectors.add(activitySector);
		
		activitySector = new ActivitySector();
		activitySector.setId("uuid-activity-sector-imprimerie");
		activitySector.setLabel("Industrie de l'imprimerie");
		activitySectors.add(activitySector);
		
		activitySector = new ActivitySector();
		activitySector.setId("uuid-activity-sector-internet");
		activitySector.setLabel("Industrie de l'Internet");
		activitySectors.add(activitySector);
		
		activitySector = new ActivitySector();
		activitySector.setId("uuid-activity-sector-cosmetique");
		activitySector.setLabel("Industrie de la beauté, cosmétique");
		activitySectors.add(activitySector);
		
		activitySector = new ActivitySector();
		activitySector.setId("uuid-activity-sector-chimie");
		activitySector.setLabel("Industrie de la chimie");
		activitySectors.add(activitySector);
		
		activitySector = new ActivitySector();
		activitySector.setId("uuid-activity-sector-plasturgie");
		activitySector.setLabel("Industrie de la plasturgie et des composites");
		activitySectors.add(activitySector);
		
		activitySector = new ActivitySector();
		activitySector.setId("uuid-activity-sector-production");
		activitySector.setLabel("Industrie de la production et transport d'énergie");
		activitySectors.add(activitySector);
		
		activitySector = new ActivitySector();
		activitySector.setId("uuid-activity-sector-ingenierie");
		activitySector.setLabel("Industrie de la R&D, ingénierie et conseil en technologies");
		activitySectors.add(activitySector);
		
		activitySector = new ActivitySector();
		activitySector.setId("uuid-activity-sector-mines");
		activitySector.setLabel("Industrie des carrières et mines");
		activitySectors.add(activitySector);
		
		activitySector = new ActivitySector();
		activitySector.setId("uuid-activity-sector-equipment-elec");
		activitySector.setLabel("Industrie des équipements électriques, électroniques, et de communication");
		activitySectors.add(activitySector);
		
		activitySector = new ActivitySector();
		activitySector.setId("uuid-activity-sector-materiaux");
		activitySector.setLabel("Industrie des matériaux");
		activitySectors.add(activitySector);
		
		activitySector = new ActivitySector();
		activitySector.setId("uuid-activity-sector-art");
		activitySector.setLabel("Industrie des métiers d’art");
		activitySectors.add(activitySector);
		
		activitySector = new ActivitySector();
		activitySector.setId("uuid-activity-sector-papiers");
		activitySector.setLabel("Industrie des papiers, cartons et celluloses");
		activitySectors.add(activitySector);
		
		activitySector = new ActivitySector();
		activitySector.setId("uuid-activity-sector-peintures");
		activitySector.setLabel("Industrie des peintures, encres, couleurs, adhésifs, et préservation du bois");
		activitySectors.add(activitySector);
		
		activitySector = new ActivitySector();
		activitySector.setId("uuid-activity-sector-batiment");
		activitySector.setLabel("Industrie du bâtiment, travaux publics");
		activitySectors.add(activitySector);
		
		activitySector = new ActivitySector();
		activitySector.setId("uuid-activity-sector-bois");
		activitySector.setLabel("Industrie du bois");
		activitySectors.add(activitySector);
		
		activitySector = new ActivitySector();
		activitySector.setId("uuid-activity-sector-luxe");
		activitySector.setLabel("Industrie du luxe");
		activitySectors.add(activitySector);
		
		activitySector = new ActivitySector();
		activitySector.setId("uuid-activity-sector-textile");
		activitySector.setLabel("Industrie du textile, cuir, habillement");
		activitySectors.add(activitySector);
		
		activitySector = new ActivitySector();
		activitySector.setId("uuid-activity-sector-verre");
		activitySector.setLabel("Industrie du verre");
		activitySectors.add(activitySector);
		
		activitySector = new ActivitySector();
		activitySector.setId("uuid-activity-sector-ferroviaire");
		activitySector.setLabel("Industrie ferroviaire");
		activitySectors.add(activitySector);
		
		activitySector = new ActivitySector();
		activitySector.setId("uuid-activity-sector-navale");
		activitySector.setLabel("Industrie nautique et navale");
		activitySectors.add(activitySector);
		
		activitySector = new ActivitySector();
		activitySector.setId("uuid-activity-sector-petroliere");
		activitySector.setLabel("Industrie pétrolière");
		activitySectors.add(activitySector);
		
		activitySector = new ActivitySector();
		activitySector.setId("uuid-activity-sector-siderurgique");
		activitySector.setLabel("Industrie sidérurgique");
		activitySectors.add(activitySector);
		
		activitySector = new ActivitySector();
		activitySector.setId("uuid-activity-sector-technoligique");
		activitySector.setLabel("Industrie technologique");
		activitySectors.add(activitySector);
		
		activitySector = new ActivitySector();
		activitySector.setId("uuid-activity-sector-amenagement");
		activitySector.setLabel("Industrie de l’ameublement et de l’aménagement des espaces de vie");
		activitySectors.add(activitySector);
		
		activitySector = new ActivitySector();
		activitySector.setId("uuid-activity-sector-mecanique");
		activitySector.setLabel("Industrie mécaniques");
		activitySectors.add(activitySector);
		
		activitySector = new ActivitySector();
		activitySector.setId("uuid-activity-sector-sante");
		activitySector.setLabel("Industrie pharmaceutiques, de santé");
		activitySectors.add(activitySector);
                
		activitySector = new ActivitySector();
		activitySector.setId("uuid-activity-sector-finance");
		activitySector.setLabel("Industrie de la finance");
		activitySectors.add(activitySector);

		activitySectorRepository.saveAll(activitySectors);

	}

}
