package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.group_consultation;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ErrorsEnum;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ObjetNotFoundException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.group_consultation.GroupConsultationMapper;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.GroupConsultationDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.GroupDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.group_consultation.GroupConsultationRSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.group_consultation.GroupConsultationCUDSM;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.group.GroupRSM;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author André
 */
@Service
public class GroupConsultationCUDSAImpl implements GroupConsultationCUDSA {

    private Logger logger = LoggerFactory.getLogger(GroupConsultationCUDSA.class);
    @Autowired
    GroupConsultationCUDSM groupConsultationCUDSM;

    @Autowired
    GroupConsultationRSA groupConsultationRSA;

    @Autowired
    GroupRSM groupRSM;

    @Autowired
    GroupConsultationMapper groupConsultationMapper;


    @Override
    public GroupConsultationDTO saveGroupConsultation(GroupConsultationDTO groupConsultationDTO) {
        if(StringUtils.isNotEmpty(groupConsultationDTO.getGroupId()) && groupRSM.isGroupExistById(groupConsultationDTO.getGroupId())) {
            if (groupConsultationRSA.findByGroupIdAndUserGroupId(groupConsultationDTO.getGroupId(), groupConsultationDTO.getUserGroupId()) != null) {
                groupConsultationDTO = groupConsultationRSA.findByGroupIdAndUserGroupId(groupConsultationDTO.getGroupId(), groupConsultationDTO.getUserGroupId());
            }

            groupConsultationDTO.setGroupConsultationLastDate(new Date());
            groupConsultationCUDSM.update(groupConsultationMapper.groupConsultationDtoToGroupConsultation(groupConsultationDTO));
            return groupConsultationDTO;
        }
        else{
            throw new ObjetNotFoundException(new GroupDTO(), ErrorsEnum.ERR_MCS_PROFIL_0047);
        }
    }

}
