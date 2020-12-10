package com.arkeup.link_innov.gestion_profil_mcs.infrastructure.referential;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.KeywordDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.KeywordsDTO;
import com.arkeup.link_innov.gestion_profil_mcs.infrastructure.utils.PermissionsAndStatusUtils;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.referential.keyword.KeywordCUDSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.referential.keyword.KeywordRSA;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/referential/keyword")
public class KeywordController {
	
	@Autowired
    KeywordCUDSA keywordCUDSA;

    @Autowired
    KeywordRSA keywordRSA;

    @PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
    @ApiOperation(value = "add Keyword", notes = "This WS is used to add Keyword information.")
    @PostMapping(path = "/add")
    public KeywordDTO addKeyword(
    		@ApiParam(name = "KeywordDTO", value = "{\"label\":\"example\"}", required = true)
    		@RequestBody KeywordDTO keywordDTO) {
       return keywordCUDSA.addKeyword(keywordDTO);
    }

    @PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
    @ApiOperation(value = "update Keyword", notes = "This WS is used to update Keyword information.")
    @PutMapping(path = "/update")
    public KeywordDTO updateKeyword(
    		@ApiParam(name = "KeywordDTO", value = "{\"label\":\"example\", \"id\":\"123456\"}", required = true)
    		@RequestBody KeywordDTO keywordDTO) {
        return keywordCUDSA.updateKeyword(keywordDTO);
    }

    @PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
    @ApiOperation(value = "get all Keyword information", notes = "This WS is used to get all Keyword information.")
    @GetMapping(value = {"/list"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public KeywordsDTO listKeyword(Pageable pageable) {
       return keywordRSA.listKeywords(pageable);
    }
    
    @PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
    @ApiOperation(value = "delete Keyword", notes = "This WS is used to delete Keyword information.")
    @DeleteMapping(path = "/delete/{id}")
    public KeywordDTO deleteKeyword(@PathVariable("id") String idKeyword) {
        return keywordCUDSA.deleteKeyword(idKeyword);
    }
}
