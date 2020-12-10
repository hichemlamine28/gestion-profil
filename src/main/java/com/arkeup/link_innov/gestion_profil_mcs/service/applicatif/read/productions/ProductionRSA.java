package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.productions;

import java.util.List;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.PostDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.NumberOfProductionDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProductionsDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.CustomPageDTO;

public interface ProductionRSA {

    public Page<ProductionsDTO> getByOwnerId(String ownerId, Pageable pageable, String order);

    public List<ProductionsDTO> findAllByOwnerId(String ownerId);

    public CustomPageDTO<ProductionsDTO> getByIds(List<String> productionIds, Pageable pageable);

    public NumberOfProductionDTO getNumberScientificDocumentByUser(String ownerId);

    public ProductionsDTO share(String userName, String productionId, PostDTO postDTO);

    public ProductionsDTO getProductionById(String userName, String id);

    CustomPageDTO<ProductionsDTO> getPaginatedProduction(List<String> productionIds, String filter, String categorie, Pageable pageable);

}
