package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.referential.keyword;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.KeywordDTO;

public interface KeywordCUDSA {
	
	public KeywordDTO addKeyword(KeywordDTO keywordDTO);
	   
    public KeywordDTO updateKeyword(KeywordDTO keywordsDTO);
    
    public KeywordDTO deleteKeyword(String idKeyword);

}
