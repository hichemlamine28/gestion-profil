package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.favorite;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ErrorsEnum;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.FunctionalInvalidDataException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.FunctionalOperationDeniedException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ObjetNotFoundException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.favorite.FavoriteMapper;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.constants.FavoriteTypeEnum;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Favorite;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.FavoriteDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.businessdelegate.ReseauxSociauxMCS;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.favorite.FavoriteCUDSM;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.favorite.FavoriteRSM;

@Service
public class FavoriteCUDSAImpl implements FavoriteCUDSA {

	@Autowired
	private FavoriteCUDSM favoriteCUDSM;

	@Autowired
	private FavoriteRSM favoriteRSM;

	@Autowired
	private FavoriteMapper favoriteFactory;

	@Autowired
	private ReseauxSociauxMCS reseauxSociauxMCS;

	@Override
	public FavoriteDTO create(String username, FavoriteDTO favoriteDTO) {

		if (favoriteDTO == null) {
			throw new FunctionalInvalidDataException(new FavoriteDTO(), ErrorsEnum.ERR_MCS_PROFIL_0060);
		}

		if (!StringUtils.isNotEmpty(favoriteDTO.getOwnerId())) {
			throw new FunctionalInvalidDataException(new FavoriteDTO(), ErrorsEnum.ERR_MCS_PROFIL_0001);
		}

		if (!StringUtils.isNotEmpty(favoriteDTO.getTargetId())) {
			throw new FunctionalInvalidDataException(new FavoriteDTO(), ErrorsEnum.ERR_MCS_PROFIL_0061);
		}

		if (FavoriteTypeEnum.valueOf(StringUtils.upperCase(favoriteDTO.getType())) == null) {
			throw new FunctionalInvalidDataException(new FavoriteDTO(), ErrorsEnum.ERR_MCS_PROFIL_0062);
		}
		
		if (!username.equals(favoriteDTO.getOwnerId()) && !reseauxSociauxMCS.hasPermission(favoriteDTO.getOwnerId()).getIsAdmin()) {
			throw new FunctionalOperationDeniedException(new FavoriteDTO(), ErrorsEnum.ERR_MCS_PROFIL_0058);
		}
		
		favoriteDTO.setType(FavoriteTypeEnum.valueOf(StringUtils.upperCase(favoriteDTO.getType())).toString());
		Favorite favorite = favoriteRSM.verifyIfFavoriteExists(favoriteDTO.getOwnerId(), favoriteDTO.getTargetId());

		if (favorite == null) {
			favoriteDTO = favoriteFactory
					.favoriteToFavoriteDTO(favoriteCUDSM.create(favoriteFactory.favoriteDtoToFavorite(favoriteDTO)));
		} else {
			favoriteCUDSM.delete(favorite.getId());
		}
		return favoriteDTO;
	}

	@Override
	public FavoriteDTO update(String username, FavoriteDTO favoriteDTO) {
		if (!username.equals(favoriteDTO.getOwnerId())) {
			throw new FunctionalOperationDeniedException(favoriteDTO, ErrorsEnum.ERR_MCS_PROFIL_0058);
		}
		if (FavoriteTypeEnum.valueOf(StringUtils.upperCase(favoriteDTO.getType())) == null) {
			throw new FunctionalInvalidDataException(new FavoriteDTO(), ErrorsEnum.ERR_MCS_PROFIL_0062);
		}
		favoriteDTO.setType(StringUtils.upperCase(favoriteDTO.getType()));
		favoriteCUDSM.update(favoriteFactory.favoriteDtoToFavorite(favoriteDTO));
		return favoriteDTO;
	}

	@Override
	public FavoriteDTO delete(String username, String id) {
		FavoriteDTO result = new FavoriteDTO();
		result.setId(id);

		Optional<Favorite> favorite = favoriteRSM.getFavorite(id);

		if (favorite.isPresent()) {
			if (!username.equals(favorite.get().getOwnerId()) && !reseauxSociauxMCS.hasPermission(favorite.get().getOwnerId()).getIsAdmin()) {
				throw new FunctionalOperationDeniedException(new FavoriteDTO(), ErrorsEnum.ERR_MCS_PROFIL_0058);
			}
		} else {
			throw new ObjetNotFoundException(result, ErrorsEnum.ERR_MCS_PROFIL_0059);
		}

		favoriteCUDSM.delete(id);
		return result;
	}

}
