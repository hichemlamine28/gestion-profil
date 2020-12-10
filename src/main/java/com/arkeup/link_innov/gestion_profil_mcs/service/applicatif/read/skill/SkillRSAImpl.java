package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.skill;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ErrorsEnum;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.skill.SkillMapper;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Skill;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProfilDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.RecommandationDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.SkillDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.SkillRecommendationDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.SkillRecommendationsDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.SkillsDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.profil.ProfilRSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.skill.SkillRSM;

@Service
public class SkillRSAImpl implements SkillRSA {

	@Autowired
	SkillRSM skillRSM;

	@Autowired
	SkillMapper skillFactory;

	@Autowired
	ProfilRSA profilRSA;

	@Override
	public SkillDTO getSkill(String id) {
		Optional<Skill> res = skillRSM.getSkill(id);

		SkillDTO result = new SkillDTO();
		if (!res.isPresent()) {
			result.setError(true);
			result.setErrorCode("ERR_MCS_PROFIL_0018");
			result.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0018.getErrorMessage() + id);

			return result;
		}

		result = skillFactory.skillToSkillDTO(res.get());
		result.setError(false);
		result.setMessage("Skill information");

		return result;
	}

	@Override
	public SkillRecommendationsDTO listSkills(String username, Pageable pageable) {
		SkillRecommendationsDTO result = new SkillRecommendationsDTO();
		ProfilDTO user = profilRSA.getProfil(username);

		if (user.isError()) {
			result.setError(true);
			result.setErrorMessage("User is not in BD");
			result.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0007.getErrorMessage());
			result.setErrorCode("ERR_MCS_PROFIL_0007");
			return result;
		}

		Page<Skill> res = skillRSM.listSkill(username, pageable);

		result.setSkillDTOs(res.map(new Function<Skill, SkillRecommendationDTO>() {
			@Override
			public SkillRecommendationDTO apply(Skill t) {
				SkillRecommendationDTO d = new SkillRecommendationDTO();
				d.setId(t.getId());
				d.setModifDate(t.getModifDate());
				d.setName(t.getName());
				d.setCreateDate(t.getCreateDate());
				d.setUserId(t.getUserId());
				d.setRecommendedCount(t.getRecommendedCount());
				List<RecommandationDTO> l = new ArrayList<RecommandationDTO>();

				for (String rec : t.getRecommended()) {
					ProfilDTO p = profilRSA.getProfil(rec);
					RecommandationDTO reco = new RecommandationDTO();
					reco.setSkillId(t.getId());
					reco.setUsername(rec);
					reco.setUserPhoto(p.getPhoto());
					l.add(reco);
				}

				d.setRecommended(l);

				return d;
			}
		}));

		result.setError(false);
		result.setMessage("Skill list");

		return result;
	}

	@Override
	public SkillsDTO getSkills(String username, Pageable pageable) {
		SkillsDTO result = new SkillsDTO();
		ProfilDTO user = profilRSA.getProfil(username);

		if (user.isError()) {
			result.setError(true);
			result.setErrorMessage("User is not in BD");
			result.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0007.getErrorMessage());
			result.setErrorCode("ERR_MCS_PROFIL_0007");
			return result;
		}

		Page<Skill> res = skillRSM.listSkill(user.getId(), pageable);
		result.setSkillDTOs(skillFactory.skillPageToSkillDtoPage(res, pageable));
		result.setError(false);
		result.setMessage("Skill list");

		return result;
	}

	@Override
	public SkillsDTO findSkill(String name, Pageable pageable) {
		SkillsDTO result = new SkillsDTO();
		Page<Skill> res = skillRSM.findSkill(name, pageable);

		result.setSkillDTOs(skillFactory.skillPageToSkillDtoPage(res, pageable));
		result.setError(false);
		result.setMessage("Skill list");

		return result;
	}

	@Override
	public List<String> findUsersBySkillLabel(String label) {
		List<Skill> skillResult = skillRSM.findByLabel(label);
		List<String> profileIds = new ArrayList<>();
		for (Skill skillRst : skillResult) {
			if (skillRst != null) {

				profileIds.add(skillRst.getUserId());
			}

		}

		return profileIds;
	}
}
