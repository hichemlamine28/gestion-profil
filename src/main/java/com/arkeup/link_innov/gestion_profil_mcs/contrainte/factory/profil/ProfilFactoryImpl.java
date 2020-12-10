/**
 *
 */
package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.profil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.referential.ActivitySectorMapper;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Category;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Corporation;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Profil;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.ActivitySector;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.CategoryDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.CorporationDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProfilDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.inscription.InscriptionDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.inscription.SignUpDTO;

/**
 * @author mikajy
 *
 */
@Component
public class ProfilFactoryImpl implements ProfilFactory {
	
	@Autowired
	private ActivitySectorMapper activitySectorMapper;

    /*
	 * (non-Javadoc)
	 * 
	 * @see com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.profil.
	 * ProfilFactory#getInstance()
     */
    @Override
    public ProfilDTO getDTOInstance() {
        return new ProfilDTO();
    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.profil.
	 * ProfilFactory#getInstance(com.arkeup.link_innov.gestion_profil_mcs.donnee.dto
	 * .UserAuthDTO)
     */
    @Override
    public ProfilDTO getDTOInstance(SignUpDTO signUpDTO) {
        if (signUpDTO == null) {
            return null;
        }
        ProfilDTO profilDto = new ProfilDTO();
        profilDto.setUsername(signUpDTO.getUsername());
        profilDto.setLastname(signUpDTO.getLastName());
        profilDto.setFirstname(signUpDTO.getFirstName());
        profilDto.setEmail(signUpDTO.getMail());
        profilDto.setWebSite(signUpDTO.getWebSite());
        profilDto.setPhoneNumber(signUpDTO.getPhoneNumber());
        profilDto.setOccupation(signUpDTO.getProfession());

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(signUpDTO.getType().getId());
        categoryDTO.setName(signUpDTO.getType().getName());

        CorporationDTO corporationDTO = new CorporationDTO();
        corporationDTO.setId(signUpDTO.getEmployer().getId());
        corporationDTO.setName(signUpDTO.getEmployer().getName());

        profilDto.setCompany(corporationDTO);
        profilDto.setCategory(categoryDTO);
        profilDto.setActivityArea(signUpDTO.getActivityArea());
        return profilDto;
    }

    @Override
    public Profil getEntityInstance(SignUpDTO signUpDTO) {
        if (signUpDTO == null) {
            return null;
        }
        Profil entity = new Profil();
        entity.setUsername(signUpDTO.getUsername());
        entity.setLastname(signUpDTO.getLastName());
        entity.setFirstname(signUpDTO.getFirstName());
        entity.setEmail(signUpDTO.getMail());
        entity.setWebSite(signUpDTO.getWebSite());
        entity.setPhoneNumber(signUpDTO.getPhoneNumber());
        entity.setOccupation(signUpDTO.getProfession());

        if (signUpDTO.getType() != null) {
        	Category category = new Category();
        	category.setId(signUpDTO.getType().getId());
        	category.setName(signUpDTO.getType().getName());
        	entity.setCategory(category);
        }

        if (signUpDTO.getEmployer() != null) {
        	Corporation corporation = new Corporation();
        	corporation.setId(signUpDTO.getEmployer().getId());
        	corporation.setName(signUpDTO.getEmployer().getName());
        	entity.setCompany(corporation);
        }

        entity.setMale(signUpDTO.getMale());

        if (signUpDTO.getActivityArea() != null && !signUpDTO.getActivityArea().isEmpty()) {
    		List<ActivitySector> activitySectors = activitySectorMapper.activitySectorDTOToActivitySectors(signUpDTO.getActivityArea());
    		entity.setActivityArea(activitySectors);
    	}

        return entity;
    }
    
    @Override
    public Profil getEntityInstance(InscriptionDTO inscriptionDTO) {
    	if (inscriptionDTO == null) {
    		return null;
    	}
    	Profil entity = new Profil();
    	entity.setUsername(inscriptionDTO.getUsername());
    	entity.setLastname(inscriptionDTO.getLastName());
    	entity.setFirstname(inscriptionDTO.getFirstName());
    	entity.setEmail(inscriptionDTO.getMail());
    	entity.setWebSite(inscriptionDTO.getWebSite());
    	entity.setPhoneNumber(inscriptionDTO.getPhoneNumber());
    	entity.setOccupation(inscriptionDTO.getProfession());
    	entity.setDomaine(inscriptionDTO.getDomaine());
    	
    	if (inscriptionDTO.getType() != null) {
    		Category category = new Category();
    		category.setId(inscriptionDTO.getType().getId());
    		category.setName(inscriptionDTO.getType().getName());
    		entity.setCategory(category);
    	}
    	
    	if (inscriptionDTO.getEmployer() != null) {
    		Corporation corporation = new Corporation();
    		corporation.setId(inscriptionDTO.getEmployer().getId());
    		corporation.setName(inscriptionDTO.getEmployer().getName());
    		entity.setCompany(corporation);
    	}
    	
    	entity.setMale(inscriptionDTO.getMale());
    	
    	if (inscriptionDTO.getActivityArea() != null && !inscriptionDTO.getActivityArea().isEmpty()) {
    		List<ActivitySector> activitySectors = activitySectorMapper.activitySectorDTOToActivitySectors(inscriptionDTO.getActivityArea());
    		entity.setActivityArea(activitySectors);
    	}
    	
    	return entity;
    }

}
