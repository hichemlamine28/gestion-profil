package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.skill;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Skill;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.SkillDTO;

@Mapper
public interface SkillMapper {
	SkillDTO skillToSkillDTO(Skill skill);
	Skill skillDTOToSkill (SkillDTO skillDTO);
	List <SkillDTO> skillToSkillDTOs(List <Skill> skills);
	List <Skill> skillDTOToSkills (List <SkillDTO> skillDTOs);

	SkillMapper MAPPER = Mappers.getMapper(SkillMapper.class);

	default Page<SkillDTO> skillPageToSkillDtoPage(Page<Skill> skillPage, Pageable pageable) {
	
		List<SkillDTO> skillDtos = MAPPER.skillToSkillDTOs(skillPage.getContent());
		Page<SkillDTO> skillDTOPage = new PageImpl<>(skillDtos, pageable, skillPage.getTotalElements());
		return skillDTOPage;
	}
}
