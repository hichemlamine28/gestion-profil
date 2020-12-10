package com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.group;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Group;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bona
 */
@Service
public class GroupCUDSMImpl implements GroupCUDSM {

    @Autowired
    GroupRepository groupRepository;

    @Override
    public Group create(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public Group update(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public void delete(String idGroup) {
        groupRepository.deleteById(idGroup);
    }

    @Override
    public List<Group> updateAll(List<Group> groups) {
        Iterable<Group> groupIterable = groupRepository.saveAll(groups);
        List<Group> groupList = new ArrayList<>();
        groupIterable.forEach(groupList::add);
        return groupList;
    }

}
