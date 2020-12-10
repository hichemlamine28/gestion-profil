package com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.productions;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.OtherProduction;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Patent;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Productions;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Project;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProductionHasMediaDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.OtherProductionRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.PatentRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductionCUDSMImpl implements ProductionCUDSM {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private OtherProductionRepository otherProductionRepository;

    @Autowired
    private PatentRepository patentRepository;


    @Override
    public Productions setProductionHasMedia(ProductionHasMediaDTO productionHasMediaDTO) {

        if (projectRepository.findById(productionHasMediaDTO.getProductionId()).isPresent()) {
            Project project = projectRepository.findById(productionHasMediaDTO.getProductionId()).get();
            project.setHasMedia(productionHasMediaDTO.getHasMedia());
            return projectRepository.save(project);
        } else if (otherProductionRepository.findById(productionHasMediaDTO.getProductionId()).isPresent()) {
            OtherProduction otherProduction = otherProductionRepository.findById(productionHasMediaDTO.getProductionId()).get();
            otherProduction.setHasMedia(productionHasMediaDTO.getHasMedia());
            return otherProductionRepository.save(otherProduction);
        } else if (patentRepository.findById(productionHasMediaDTO.getProductionId()).isPresent()) {
            Patent patent = patentRepository.findById(productionHasMediaDTO.getProductionId()).get();
            patent.setHasMedia(productionHasMediaDTO.getHasMedia());
            return patentRepository.save(patent);
        }
        return null;
    }

    @Override
    public List<Productions> updateAll(List<Productions> productions) {
        return productions.stream().map(this::mapToHasMedia).collect(Collectors.toList());
    }

    private Productions mapToHasMedia(Productions productions) {
        ProductionHasMediaDTO productionHasMediaDTO = new ProductionHasMediaDTO();
        productionHasMediaDTO.setHasMedia(true);
        productionHasMediaDTO.setProductionId(productions.getId());
        return setProductionHasMedia(productionHasMediaDTO);
    }
}
