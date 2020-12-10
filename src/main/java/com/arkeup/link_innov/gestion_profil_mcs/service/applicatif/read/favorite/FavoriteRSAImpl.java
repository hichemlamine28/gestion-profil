package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.favorite;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.*;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.group.GroupRSA;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ErrorsEnum;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.FunctionalInvalidDataException;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.constants.FavoriteTypeEnum;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Favorite;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.CustomPageDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.utils.HelpPage;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.corporation.CorporationRSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.productions.ProductionRSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.profil.ProfilRSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.businessdelegate.AnnounceMCS;
import com.arkeup.link_innov.gestion_profil_mcs.service.businessdelegate.ReseauxSociauxMCS;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.favorite.FavoriteRSM;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Service
public class FavoriteRSAImpl implements FavoriteRSA {

    @Autowired
    private FavoriteRSM favoriteRSM;

    @Autowired
    private AnnounceMCS announceMCS;

    @Autowired
    private ReseauxSociauxMCS reseauxSociauxMCS;

    @Autowired
    private ProductionRSA productionRSA;

    @Autowired
    private ProfilRSA profilRSA;

    @Autowired
    private GroupRSA groupRSA;

    @Autowired
    private CorporationRSA corporationRSA;

    @Override
    public FavoritesDTO listFavorite(String username, String type, Pageable pageable) {
        FavoritesDTO result = new FavoritesDTO();

        if (FavoriteTypeEnum.valueOf(StringUtils.upperCase(type)) == null) {
            throw new FunctionalInvalidDataException(new FavoritesDTO(), ErrorsEnum.ERR_MCS_PROFIL_0062);
        }
        Page<Favorite> res = favoriteRSM.listFavoriteByUser(username, StringUtils.upperCase(type), pageable);

        List<String> ids = new ArrayList<String>();

        for (Favorite elem : res) {
            if (!ids.contains(elem.getTargetId())) {
                ids.add(elem.getTargetId());
            }
        }

        ListIdDTO listId = new ListIdDTO();
        listId.setIds(ids);

        if (type.equalsIgnoreCase(FavoriteTypeEnum.ANNOUNCE.name())) {
            CustomPageDTO<AnnounceDTO> announces = announceMCS.getAnnounces(listId, pageable.getPageNumber(), pageable.getPageSize());
            sortAnnounces(ids, announces, pageable);
            result.setAnnounceDTOs(new PageImpl<AnnounceDTO>(announces.getPageDTOs().getContent(), pageable, announces.getPageDTOs().getNumberOfElements()));
        } else if (type.equalsIgnoreCase(FavoriteTypeEnum.POST.name())) {
            CustomPageDTO<PostDTO> posts = reseauxSociauxMCS.getPosts(listId, pageable);
            sortPosts(listId.getIds(), posts, pageable);
            result.setPostDTOs(new PageImpl<>(posts.getPageDTOs().getContent(), pageable, posts.getPageDTOs().getNumberOfElements()));
        } else if (type.equalsIgnoreCase(FavoriteTypeEnum.PRODUCTION.name())) {
            CustomPageDTO<ProductionsDTO> productionDTOs = productionRSA.getByIds(listId.getIds(), pageable);
            sortProductions(ids, productionDTOs, pageable);
            result.setProductionDTOs(new PageImpl<>(productionDTOs.getPageDTOs().getContent(), pageable, productionDTOs.getPageDTOs().getNumberOfElements()));
        } else if (type.equalsIgnoreCase(FavoriteTypeEnum.PROFIL.name())) {
            CustomPageDTO<ProfilDTO> profils = profilRSA.getPaginatedProfilByIds(listId.getIds(), pageable);
            sortProfils(ids, profils, pageable);
            result.setProfilDTOs(new PageImpl<>(profils.getPageDTOs().getContent(), pageable, profils.getPageDTOs().getNumberOfElements()));
        } else if (type.equalsIgnoreCase(FavoriteTypeEnum.CORPORATION.name())) {
            CustomPageDTO<CorporationDTO> corporations = corporationRSA.getPaginatedCorporationByIds(listId.getIds(), pageable);
            sortCorporations(ids, corporations, pageable);
            result.setCorporationDTOs(new PageImpl<>(corporations.getPageDTOs().getContent(), pageable, corporations.getPageDTOs().getNumberOfElements()));
        } else if (type.equalsIgnoreCase(FavoriteTypeEnum.GROUP.name())) {
            CustomPageDTO<GroupDTO> groups = groupRSA.getPaginatedGroupes(listId.getIds(), pageable);
            sortGroups(ids, groups, pageable);
            result.setGroupDTOs(new PageImpl<>(groups.getPageDTOs().getContent(), pageable, groups.getPageDTOs().getTotalElements()));

        }

        result.setError(false);
        result.setMessage("List of favorites by types");
        return result;
    }

    @Override
    public FavoriteStatusDTO checkFavorite(String username, String targetId) {
        FavoriteStatusDTO favoriteStatusDTO = new FavoriteStatusDTO();
        favoriteStatusDTO.setIsFavorite(favoriteRSM.verifyIfFavoriteExists(username, targetId) != null);
        return favoriteStatusDTO;
    }

    private void sortPosts(List<String> ids, CustomPageDTO<PostDTO> posts, Pageable pageable) {
        List<PostDTO> lstDTO = new ArrayList<>(posts.getPageDTOs().getContent());
        lstDTO.sort(Comparator.comparing(item -> ids.indexOf(item.getId())));
        HelpPage<PostDTO> helpPost = new HelpPage<>(lstDTO, pageable, posts.getPageDTOs().getTotalElements());
        posts.setPageDTOs(helpPost);
    }

    private void sortAnnounces(List<String> ids, CustomPageDTO<AnnounceDTO> announces, Pageable pageable) {
        List<AnnounceDTO> lstDTO = new ArrayList<>(announces.getPageDTOs().getContent());
        lstDTO.sort(Comparator.comparing(item -> ids.indexOf(item.getId())));
        HelpPage<AnnounceDTO> helpAnnounce = new HelpPage<>(lstDTO, pageable, announces.getPageDTOs().getTotalElements());
        announces.setPageDTOs(helpAnnounce);
    }

    private void sortProductions(List<String> ids, CustomPageDTO<ProductionsDTO> productions, Pageable pageable) {
        List<ProductionsDTO> lstDTO = new ArrayList<>(productions.getPageDTOs().getContent());
        lstDTO.sort(Comparator.comparing(item -> ids.indexOf(item.getId())));
        HelpPage<ProductionsDTO> helpProduction = new HelpPage<>(lstDTO, pageable, productions.getPageDTOs().getTotalElements());
        productions.setPageDTOs(helpProduction);
    }

    private void sortProfils(List<String> ids, CustomPageDTO<ProfilDTO> profils, Pageable pageable) {
        List<ProfilDTO> lstDTO = new ArrayList<>(profils.getPageDTOs().getContent());
        lstDTO.sort(Comparator.comparing(item -> ids.indexOf(item.getUsername())));
        HelpPage<ProfilDTO> helpProfil = new HelpPage<>(lstDTO, pageable, profils.getPageDTOs().getTotalElements());
        profils.setPageDTOs(helpProfil);
    }

    private void sortCorporations(List<String> ids, CustomPageDTO<CorporationDTO> corporations, Pageable pageable) {
        List<CorporationDTO> lstDTO = new ArrayList<>(corporations.getPageDTOs().getContent());
        lstDTO.sort(Comparator.comparing(item -> ids.indexOf(item.getId())));
        HelpPage<CorporationDTO> helpCorporation = new HelpPage<>(lstDTO, pageable, corporations.getPageDTOs().getTotalElements());
        corporations.setPageDTOs(helpCorporation);
    }

    private void sortGroups(List<String> ids, CustomPageDTO<GroupDTO> groups, Pageable pageable) {
        List<GroupDTO> lstDTO = new ArrayList<>(groups.getPageDTOs().getContent());
        lstDTO.sort(Comparator.comparing(item -> ids.indexOf(item.getId())));
        HelpPage<GroupDTO> helpGroup = new HelpPage<>(lstDTO, pageable, groups.getPageDTOs().getTotalElements());
        groups.setPageDTOs(helpGroup);
    }

    @Override
    public FavoritesDTO listFavoriteFiltered(String type, String filter, String categorie, Pageable pageable) {
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        FavoritesDTO result = new FavoritesDTO();

        if (FavoriteTypeEnum.valueOf(StringUtils.upperCase(type)) == null) {
            throw new FunctionalInvalidDataException(new FavoritesDTO(), ErrorsEnum.ERR_MCS_PROFIL_0062);
        }

        List<Favorite> res = favoriteRSM.listFavoriteByUser(username, StringUtils.upperCase(type));

        List<String> ids = new ArrayList<>();

        res.stream().filter((elem) -> (!ids.contains(elem.getTargetId()))).forEachOrdered((elem) -> {
            ids.add(elem.getTargetId());
        });

        ListIdDTO listId = new ListIdDTO();
        listId.setIds(ids);

        filter = StringUtils.isEmpty(filter) ? filter : "";

        switch (type.toUpperCase()) {
            case "ANNOUNCE":
                CustomPageDTO<AnnounceDTO> announces = announceMCS.getPaginatedAnnounces(listId, filter, categorie, pageable);
                result.setAnnounceDTOs(new PageImpl<>(announces.getPageDTOs().getContent(), pageable, announces.getPageDTOs().getTotalElements()));
                break;

            case "POST":
                CustomPageDTO<PostDTO> posts = reseauxSociauxMCS.getPaginatedPosts(listId, filter, pageable);
                result.setPostDTOs(new PageImpl<>(posts.getPageDTOs().getContent(), pageable, posts.getPageDTOs().getTotalElements()));
                break;

            case "PRODUCTION":
                CustomPageDTO<ProductionsDTO> productionDTOs = productionRSA.getPaginatedProduction(listId.getIds(), filter, categorie, pageable);
                result.setProductionDTOs(new PageImpl<>(productionDTOs.getPageDTOs().getContent(), pageable, productionDTOs.getPageDTOs().getTotalElements()));
                break;

            case "PROFIL":
                CustomPageDTO<ProfilDTO> profils = profilRSA.getPaginatedProfil(listId.getIds(), filter, categorie, pageable);
                result.setProfilDTOs(new PageImpl<>(profils.getPageDTOs().getContent(), pageable, profils.getPageDTOs().getTotalElements()));
                break;

            case "CORPORATION":
                CustomPageDTO<CorporationDTO> corporations = corporationRSA.getPaginatedCorporation(listId.getIds(), filter, categorie, pageable);
                result.setCorporationDTOs(new PageImpl<>(corporations.getPageDTOs().getContent(), pageable, corporations.getPageDTOs().getTotalElements()));
                break;
        }

        result.setError(false);
        result.setMessage("List of favorites by types");
        return result;
    }

    @Override
    public FavoritesDTO listFavoriteFilter(String username, String type, String categorie, Pageable pageable) {
        FavoritesDTO result = new FavoritesDTO();

        if (FavoriteTypeEnum.valueOf(StringUtils.upperCase(type)) == null) {
            throw new FunctionalInvalidDataException(new FavoritesDTO(), ErrorsEnum.ERR_MCS_PROFIL_0062);
        }
        String filter = "";
        List<Favorite> res = favoriteRSM.listFavoriteByUser(username, StringUtils.upperCase(type));

        List<String> ids = new ArrayList<>();

        res.stream().filter((elem) -> (!ids.contains(elem.getTargetId()))).forEachOrdered((elem) -> {
            ids.add(elem.getTargetId());
        });

        ListIdDTO listId = new ListIdDTO();
        listId.setIds(ids);


        if(StringUtils.isEmpty(categorie))
            return listFavorite(username, type, pageable);

        switch (type.toUpperCase()) {
            case "ANNOUNCE":
                CustomPageDTO<AnnounceDTO> announces = announceMCS.getAnnounces(listId, pageable.getPageNumber(), pageable.getPageSize());
                sortAnnounces(ids, announces, pageable);
                result.setAnnounceDTOs(new PageImpl<AnnounceDTO>(announces.getPageDTOs().getContent(), pageable, announces.getPageDTOs().getNumberOfElements()));
                break;

            case "POST":
                CustomPageDTO<PostDTO> posts = reseauxSociauxMCS.getPaginatedPosts(listId, filter, pageable);
                result.setPostDTOs(new PageImpl<>(posts.getPageDTOs().getContent(), pageable, posts.getPageDTOs().getTotalElements()));
                break;

            case "PRODUCTION":
                CustomPageDTO<ProductionsDTO> productionDTOs = productionRSA.getPaginatedProduction(listId.getIds(), filter, categorie, pageable);
                result.setProductionDTOs(new PageImpl<>(productionDTOs.getPageDTOs().getContent(), pageable, productionDTOs.getPageDTOs().getTotalElements()));
                break;

            case "PROFIL":
                CustomPageDTO<ProfilDTO> profils = profilRSA.getPaginatedProfil(listId.getIds(), filter, categorie, pageable);
                result.setProfilDTOs(new PageImpl<>(profils.getPageDTOs().getContent(), pageable, profils.getPageDTOs().getTotalElements()));
                break;

            case "CORPORATION":
                CustomPageDTO<CorporationDTO> corporations = corporationRSA.getPaginatedCorporation(listId.getIds(), filter, categorie, pageable);
                result.setCorporationDTOs(new PageImpl<>(corporations.getPageDTOs().getContent(), pageable, corporations.getPageDTOs().getTotalElements()));
                break;

            case "GROUP":
                CustomPageDTO<GroupDTO> groups = groupRSA.getPaginatedGroupes(listId.getIds(), pageable);
                result.setGroupDTOs(new PageImpl<>(groups.getPageDTOs().getContent(), pageable, groups.getPageDTOs().getTotalElements()));
                break;
        }

        result.setError(false);
        result.setMessage("List of favorites by types");
        return result;
    }

}
