package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.referential;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.Keyword;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.KeywordDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class KeywordMapperImpl implements KeywordMapper {

    @Override
    public KeywordDTO keywordToKeywordDTO(Keyword keyword) {
        if ( keyword == null ) {
            return null;
        }

        KeywordDTO keywordDTO = new KeywordDTO();

        keywordDTO.setId( keyword.getId() );
        keywordDTO.setLabel( keyword.getLabel() );

        return keywordDTO;
    }

    @Override
    public Keyword keywordDTOToKeyword(KeywordDTO keywordDTO) {
        if ( keywordDTO == null ) {
            return null;
        }

        Keyword keyword = new Keyword();

        keyword.setId( keywordDTO.getId() );
        keyword.setLabel( keywordDTO.getLabel() );

        return keyword;
    }

    @Override
    public List<KeywordDTO> keywordToKeywordDTOs(List<Keyword> keywords) {
        if ( keywords == null ) {
            return null;
        }

        List<KeywordDTO> list = new ArrayList<KeywordDTO>( keywords.size() );
        for ( Keyword keyword : keywords ) {
            list.add( keywordToKeywordDTO( keyword ) );
        }

        return list;
    }

    @Override
    public List<Keyword> keywordDTOToKeywords(List<KeywordDTO> keywordDTOs) {
        if ( keywordDTOs == null ) {
            return null;
        }

        List<Keyword> list = new ArrayList<Keyword>( keywordDTOs.size() );
        for ( KeywordDTO keywordDTO : keywordDTOs ) {
            list.add( keywordDTOToKeyword( keywordDTO ) );
        }

        return list;
    }
}
