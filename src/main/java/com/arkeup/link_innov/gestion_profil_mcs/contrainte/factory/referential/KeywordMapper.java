package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.referential;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.Keyword;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.KeywordDTO;

@Mapper
public interface KeywordMapper {
	
	KeywordDTO keywordToKeywordDTO(Keyword keyword);
	Keyword keywordDTOToKeyword(KeywordDTO keywordDTO);
	List<KeywordDTO> keywordToKeywordDTOs(List<Keyword> keywords);
	List<Keyword> keywordDTOToKeywords(List<KeywordDTO> keywordDTOs);
	
	KeywordMapper MAPPER = Mappers.getMapper(KeywordMapper.class);
	
	default Page<KeywordDTO> keywordPageToKeywordDTOPage(Page<Keyword> keywordPage, Pageable pageable) {

		List<KeywordDTO> keywordDtos = MAPPER.keywordToKeywordDTOs(keywordPage.getContent());
		Page<KeywordDTO> keywordDTOPage = new PageImpl<>(keywordDtos, pageable, keywordPage.getTotalElements());
		return keywordDTOPage;
	}

}
