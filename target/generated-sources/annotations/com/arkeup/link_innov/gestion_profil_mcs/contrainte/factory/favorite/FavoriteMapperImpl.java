package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.favorite;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Favorite;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.FavoriteDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class FavoriteMapperImpl implements FavoriteMapper {

    @Override
    public Favorite favoriteDtoToFavorite(FavoriteDTO favoriteDTO) {
        if ( favoriteDTO == null ) {
            return null;
        }

        Favorite favorite = new Favorite();

        favorite.setId( favoriteDTO.getId() );
        favorite.setCreateDate( favoriteDTO.getCreateDate() );
        favorite.setModifDate( favoriteDTO.getModifDate() );
        favorite.setOwnerId( favoriteDTO.getOwnerId() );
        favorite.setTargetId( favoriteDTO.getTargetId() );
        favorite.setType( favoriteDTO.getType() );

        return favorite;
    }

    @Override
    public FavoriteDTO favoriteToFavoriteDTO(Favorite favorite) {
        if ( favorite == null ) {
            return null;
        }

        FavoriteDTO favoriteDTO = new FavoriteDTO();

        favoriteDTO.setId( favorite.getId() );
        favoriteDTO.setCreateDate( favorite.getCreateDate() );
        favoriteDTO.setModifDate( favorite.getModifDate() );
        favoriteDTO.setOwnerId( favorite.getOwnerId() );
        favoriteDTO.setTargetId( favorite.getTargetId() );
        favoriteDTO.setType( favorite.getType() );

        return favoriteDTO;
    }

    @Override
    public List<FavoriteDTO> listFavoriteToListFavoriteDTO(List<Favorite> favorite) {
        if ( favorite == null ) {
            return null;
        }

        List<FavoriteDTO> list = new ArrayList<FavoriteDTO>( favorite.size() );
        for ( Favorite favorite1 : favorite ) {
            list.add( favoriteToFavoriteDTO( favorite1 ) );
        }

        return list;
    }
}
