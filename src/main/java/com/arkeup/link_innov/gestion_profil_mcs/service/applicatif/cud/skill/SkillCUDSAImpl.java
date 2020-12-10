package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.skill;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ErrorsEnum;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.skill.SkillMapper;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.constants.RecommandationActionType;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Recommandation;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Skill;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProfilDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.RecommandationDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.SkillDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.notification.NotificationSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.profil.ProfilRSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.recommandation.RecommandationCUDSM;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.skill.SkillCUDSM;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.skill.SkillRSM;

@Service
public class SkillCUDSAImpl implements SkillCUDSA {

	@Autowired
	SkillCUDSM skillCUDSM;

	@Autowired
	SkillRSM skillRSM;

	@Autowired
	SkillMapper skillFactory;

	@Autowired
	ProfilRSA profilRSA;

	@Autowired
	private NotificationSA notificationSA;

	@Autowired
	private RecommandationCUDSM recommandationActionCUDSM;

	@Override
	public SkillDTO create(String username, SkillDTO skillDTO) {
		ProfilDTO user = profilRSA.getProfil(username);

		if (user.isError()) {
			skillDTO.setError(true);
			skillDTO.setErrorMessage("User is not in BD");
			skillDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0007.getErrorMessage());
			skillDTO.setErrorCode("ERR_MCS_PROFIL_0007");
			return skillDTO;
		}

		if ((skillDTO.getName() == null) || (skillDTO.getName().equals(""))) {
			skillDTO.setError(true);
			skillDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0013.getErrorMessage());
			skillDTO.setErrorCode("ERR_MCS_PROFIL_0013");
			return skillDTO;
		}

		skillDTO.setUserId(user.getUsername());

		SkillDTO result = skillFactory.skillToSkillDTO(skillCUDSM.create(skillFactory.skillDTOToSkill(skillDTO)));

		if (result.getId()== null) {
			skillDTO.setError(true);
			skillDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0019.getErrorMessage());
			skillDTO.setErrorCode("ERR_MCS_PROFIL_0019");
		} else {
			skillDTO.setError(false);
			skillDTO.setMessage("Skill added");
			skillDTO.setId(result.getId());
		}

		return skillDTO;
	}

	@Override
	public SkillDTO update(SkillDTO skillDTO) {

		if ((skillDTO.getName() == null) || (skillDTO.getName().equals(""))) {
			skillDTO.setError(true);
			skillDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0013.getErrorMessage());
			skillDTO.setErrorCode("ERR_MCS_PROFIL_0013");
			return skillDTO;
		}

		skillCUDSM.update(skillFactory.skillDTOToSkill(skillDTO));
		skillDTO.setError(false);
		skillDTO.setMessage("Corporation updated");

		return skillDTO;
	}

	@Override
	public SkillDTO delete(String id) {
		Optional<Skill> entity = skillCUDSM.delete(id);
		SkillDTO result = new SkillDTO();
		if (entity.isPresent()) {
			result = skillFactory.skillToSkillDTO(entity.get());
			result.setError(false);
			result.setMessage("Skill deleted");
		}
		else {

			result.setError(true);
			result.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0018.getErrorMessage() + id);
			result.setErrorCode("ERR_MCS_PROFIL_0018");
		}
		return result;
	}

	@Override
	public RecommandationDTO addRecommendation(RecommandationDTO recommendation, String username) {
		Optional<Skill> entity = skillRSM.getSkill(recommendation.getSkillId());
		if (entity.isPresent()) {
			skillCUDSM.addRecommendation(entity.get(), username);
			recommendation.setError(false);
			recommendation.setMessage("Recommendation added");

			ProfilDTO user = profilRSA.getProfil(username);

			// save and send Recommandation Action
			Recommandation recommandation = new Recommandation();
			String uuid = UUID.randomUUID().toString();
			recommandation.setId(uuid);
			recommandation.setSkillId(entity.get().getId());
			recommandation.setRecommandationOwner(username);
			recommandation.setSkillLabel(entity.get().getName());
			recommandation.setSkillOwner(entity.get().getUserId());
			recommandation.setRecommandationOwnerName(user.getFirstname()+" "+user.getLastname());
			recommandation.setRecommandationType(RecommandationActionType.SKILLRECOMMANDATION.name());
			recommandationActionCUDSM.saveRecommandation(recommandation);
			notificationSA.sendSkillRecommandation(username, uuid, RecommandationActionType.SKILLRECOMMANDATION.name());
		}
		else {
			recommendation.setError(true);
			recommendation.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0018.getErrorMessage() + recommendation.getSkillId());
			recommendation.setErrorCode("ERR_MCS_PROFIL_0018");
		}
		return recommendation;
	}

	@Override
	public RecommandationDTO deleteRecommendation(RecommandationDTO recommendation, String username) {
		Optional<Skill> entity = skillRSM.getSkill(recommendation.getSkillId());
		if (entity.isPresent()) {
			skillCUDSM.deleteRecommendation(entity.get(), username);
			recommendation.setError(false);
			recommendation.setMessage("Recommendation deleted");
		}
		else {
			recommendation.setError(true);
			recommendation.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0018.getErrorMessage() + recommendation.getSkillId());
			recommendation.setErrorCode("ERR_MCS_PROFIL_0018");
		}
		return recommendation;
	}
}
