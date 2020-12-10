package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.productions;

import java.util.Comparator;
import java.util.List;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ErrorsEnum;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ObjectSaveException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ObjetNotFoundException;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.PostDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.businessdelegate.ReseauxSociauxMCS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.commun.PageableFactory;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.production.ProductionMapper;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Productions;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.NumberOfProductionDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProductionsDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.CustomPageDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.utils.HelpPage;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.productions.other.OtherRSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.productions.patent.PatentRSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.productions.ProductionRSM;

@Service
public class ProductionRSAImpl implements ProductionRSA {

    @Autowired
    private ProductionRSM productionRSM;

    @Autowired
    private ProductionMapper productionMapper;

    @Autowired
    private OtherRSA otherProductionRSA;

    @Autowired
    private PatentRSA patentRSA;

    @Autowired
    ReseauxSociauxMCS reseauxSociauxMCS;

    @Autowired
    private PageableFactory<Productions> pageableFactory;

    @Override
    public Page<ProductionsDTO> getByOwnerId(String ownerId, Pageable pageable, String order) {
        Page<Productions> productionPage = productionRSM.getByOwnerId(ownerId, pageable, order);
        return  productionMapper.productionPageToProductionDtoPage(productionPage,
                pageable);
    }

    @Override
    public List<ProductionsDTO> findAllByOwnerId(String ownerId) {
        List<Productions> productions = productionRSM.findAllByOwnerId(ownerId);
        List<ProductionsDTO> productionsDTOs = productionMapper.productionToProductionDTOs(productions);
        productionsDTOs.sort(Comparator.comparing(ProductionsDTO::getCreationDate).reversed());
        return productionsDTOs;
    }

    @Override
    public CustomPageDTO<ProductionsDTO> getByIds(List<String> productionIds, Pageable pageable) {
        CustomPageDTO<ProductionsDTO> productions = new CustomPageDTO<>();
        Page<Productions> productionPage = productionRSM.getByIds(productionIds, pageable);
        HelpPage<Productions> helpPageProductions = pageableFactory.pageToHelpPage(productionPage);
        HelpPage<ProductionsDTO> helpPageProductionsDTO = productionMapper
                .helpPageCorporationToHelpPageCorporationDTOs(helpPageProductions);

        productions.setPageDTOs(helpPageProductionsDTO);

        return productions;
    }

    @Override
    public NumberOfProductionDTO getNumberScientificDocumentByUser(String ownerId) {
        NumberOfProductionDTO response = new NumberOfProductionDTO();
        response.setNumberOfProduction(
                patentRSA.getNumberPatentByOwnerId(ownerId) + otherProductionRSA.getOtherProductionByOwnerId(ownerId));
        return response;
    }

    @Override
    public ProductionsDTO share(String userName, String productionId, PostDTO postDTO) {
        ProductionsDTO productionsDTO = productionMapper.productionToProductionDTO(productionRSM.findById(productionId));

        if (productionsDTO == null || productionsDTO.isError()) {
            throw new ObjetNotFoundException(new ProductionsDTO(), ErrorsEnum.ERR_MCS_PROFIL_0065);
        }

        postDTO.setOwnerId(userName);
        postDTO.setMessage(productionsDTO.getTitle());
        postDTO.setSharedFromPost(productionsDTO.getId());
        postDTO.setType("SHARED_PRODUCTION");

        PostDTO post = reseauxSociauxMCS.createPost(postDTO);

        if (post.isError()) {
            throw new ObjectSaveException(postDTO, ErrorsEnum.ERR_MCS_PROFIL_0056);
        }

        productionsDTO.setError(false);
        productionsDTO.setMessage("Production has been shared");
        return productionsDTO;
    }

    @Override
    public ProductionsDTO getProductionById(String userName, String id) {
        ProductionsDTO productionsDTO = productionMapper.productionToProductionDTO(productionRSM.findById(id));
        if (productionsDTO == null || productionsDTO.isError()) {
            throw new ObjetNotFoundException(new ProductionsDTO(), ErrorsEnum.ERR_MCS_PROFIL_0065);
        }
        return productionsDTO;
    }

    @Override
    public CustomPageDTO<ProductionsDTO> getPaginatedProduction(List<String> productionIds, String filter, String categorie, Pageable pageable) {
        CustomPageDTO<ProductionsDTO> productions = new CustomPageDTO<>();
        Page<Productions> productionPage = productionRSM.getPaginatedProductions(productionIds, filter, categorie, pageable);

        HelpPage<Productions> helpPageProductions = pageableFactory.pageToHelpPage(productionPage);
        HelpPage<ProductionsDTO> helpPageProductionsDTO = productionMapper
                .helpPageCorporationToHelpPageCorporationDTOs(helpPageProductions);

        productions.setPageDTOs(helpPageProductionsDTO);

        return productions;
    }

}
