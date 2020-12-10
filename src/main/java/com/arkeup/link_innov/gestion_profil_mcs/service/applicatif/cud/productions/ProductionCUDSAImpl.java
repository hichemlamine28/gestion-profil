package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.productions;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.production.ProductionMapper;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Productions;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.MapMediaDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProductionHasMediaDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProductionsDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.businessdelegate.MediaMCS;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.productions.ProductionCUDSM;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.productions.ProductionRSM;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductionCUDSAImpl implements ProductionCUDSA {

    @Autowired
    private ProductionCUDSM productionCUDSM;

    @Autowired
    private ProductionRSM productionsRSM;

    @Autowired
    private ProductionMapper productionMapper;

    @Autowired
    private MediaMCS mediaMCS;


    @Override
    public ProductionsDTO setProductionHasMedia(ProductionHasMediaDTO productionHasMediaDTO) {
        if (StringUtils.isEmpty(productionHasMediaDTO.getProductionId()))
            return null;
        if (productionHasMediaDTO.getHasMedia() == null)
            productionHasMediaDTO.setHasMedia(false);
        return productionMapper.productionToProductionDTO(productionCUDSM.setProductionHasMedia(productionHasMediaDTO));
    }

    @Override
    public boolean updateHasMediaProduction() {
        boolean isUpdated = false;
        List<Productions> productionsList = productionsRSM.findAll()
                .stream()
                .filter(p -> p.getHasMedia() == null || !p.getHasMedia())
                .collect(Collectors.toList());
        List<ProductionsDTO> productionsDTOS = productionMapper.productionToProductionDTOs(productionsList);
        if (productionsDTOS != null && productionsDTOS.size() > 0) {

            List<MapMediaDTO> mapMediaDTOS = productionsDTOS
                    .stream()
                    .map(this::mapMediaDTO)
                    .collect(Collectors.toList());
            if (mapMediaDTOS.size() > 0) {
                List<String> postIds = mediaMCS.listHasMedia(mapMediaDTOS);
                if (postIds.size() > 0) {
                    List<ProductionsDTO> productionsDTOList = productionsDTOS
                            .stream()
                            .filter(profilDTO -> postIds.contains(profilDTO.getId()))
                            .map(this::mapToHasMedia)
                            .collect(Collectors.toList());
                    List<Productions> posts = productionCUDSM.updateAll(productionMapper.productionDTOsToProduction(productionsDTOList));
                    isUpdated = posts.stream().allMatch(Productions::getHasMedia);
                }
            }
        }
        return isUpdated;
    }

    private MapMediaDTO mapMediaDTO(ProductionsDTO productionsDTO) {
        MapMediaDTO mapMediaDTO = new MapMediaDTO();
        mapMediaDTO.setMediaId(productionsDTO.getMediaId());
        mapMediaDTO.setTarget(productionsDTO.getId());
        return mapMediaDTO;
    }


    private ProductionsDTO mapToHasMedia(ProductionsDTO productionsDTO) {
        productionsDTO.setHasMedia(true);
        return productionsDTO;
    }

}
