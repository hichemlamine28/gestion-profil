package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.skill;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Skill;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.SkillDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class SkillMapperImpl implements SkillMapper {

    @Override
    public SkillDTO skillToSkillDTO(Skill skill) {
        if ( skill == null ) {
            return null;
        }

        SkillDTO skillDTO = new SkillDTO();

        skillDTO.setId( skill.getId() );
        skillDTO.setName( skill.getName() );
        List<String> list = skill.getRecommended();
        if ( list != null ) {
            skillDTO.setRecommended( new ArrayList<String>( list ) );
        }
        else {
            skillDTO.setRecommended( null );
        }
        skillDTO.setRecommendedCount( skill.getRecommendedCount() );
        skillDTO.setUserId( skill.getUserId() );
        skillDTO.setCreateDate( skill.getCreateDate() );
        skillDTO.setModifDate( skill.getModifDate() );

        return skillDTO;
    }

    @Override
    public Skill skillDTOToSkill(SkillDTO skillDTO) {
        if ( skillDTO == null ) {
            return null;
        }

        Skill skill = new Skill();

        skill.setId( skillDTO.getId() );
        skill.setName( skillDTO.getName() );
        List<String> list = skillDTO.getRecommended();
        if ( list != null ) {
            skill.setRecommended( new ArrayList<String>( list ) );
        }
        else {
            skill.setRecommended( null );
        }
        skill.setRecommendedCount( skillDTO.getRecommendedCount() );
        skill.setUserId( skillDTO.getUserId() );
        skill.setCreateDate( skillDTO.getCreateDate() );
        skill.setModifDate( skillDTO.getModifDate() );

        return skill;
    }

    @Override
    public List<SkillDTO> skillToSkillDTOs(List<Skill> skills) {
        if ( skills == null ) {
            return null;
        }

        List<SkillDTO> list = new ArrayList<SkillDTO>( skills.size() );
        for ( Skill skill : skills ) {
            list.add( skillToSkillDTO( skill ) );
        }

        return list;
    }

    @Override
    public List<Skill> skillDTOToSkills(List<SkillDTO> skillDTOs) {
        if ( skillDTOs == null ) {
            return null;
        }

        List<Skill> list = new ArrayList<Skill>( skillDTOs.size() );
        for ( SkillDTO skillDTO : skillDTOs ) {
            list.add( skillDTOToSkill( skillDTO ) );
        }

        return list;
    }
}
