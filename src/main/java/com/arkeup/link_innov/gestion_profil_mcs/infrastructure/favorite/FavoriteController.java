package com.arkeup.link_innov.gestion_profil_mcs.infrastructure.favorite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.FavoriteDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.FavoriteStatusDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.FavoritesDTO;
import com.arkeup.link_innov.gestion_profil_mcs.infrastructure.utils.PermissionsAndStatusUtils;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.favorite.FavoriteCUDSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.favorite.FavoriteRSA;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@Api("Favorite")
@RestController
@RequestMapping(value = "/favorite")
public class FavoriteController {

    @Autowired
    private FavoriteCUDSA favoriteCUDSA;

    @Autowired
    private FavoriteRSA favoriteRSA;

    @PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
    @PutMapping(value = {"/add"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Add favorite information")
    public FavoriteDTO add(
            @ApiParam(name = "FavoriteDTO", value = "{\"ownerId\":\"testUser@yopmail.com\",\"targetId\":\"uuid\", \"type\": \"ANNOUNCE\"}", required = true) @RequestBody FavoriteDTO favoriteDTO) {

        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = user.getUsername();
        return favoriteCUDSA.create(userName, favoriteDTO);
    }

    @PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
    @PutMapping(value = {"/update"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Update favorite information")
    public FavoriteDTO update(
            @ApiParam(name = "FavoriteDTO", value = "{\"ownerId\":\"testUser@yopmail.com\",\"targetId\":\"uuid\", \"type\": \"ANNOUNCE\", \"id\":\"fav69\"}", required = true) @RequestBody FavoriteDTO favoriteDTO) {

        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = user.getUsername();
        return favoriteCUDSA.update(userName, favoriteDTO);
    }

    @PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
    @DeleteMapping(value = {"/delete"}, params = {"favoriteId"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Delete favorite information")
    public FavoriteDTO delete(
            @ApiParam(value = "{\"favoriteId\":\"favoriteId\"}", required = true) @RequestParam String favoriteId) {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return favoriteCUDSA.delete(user.getUsername(), favoriteId);
    }

    @PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
    @GetMapping(value = {"/listFavorite"}, params = {"ownerId", "type"}, produces = {
        MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "List favorites")
    public FavoritesDTO listFavorite(
            @ApiParam(value = "{\"ownerId\":\"ownerId\"}", required = true) @RequestParam String ownerId,
            @ApiParam(value = "{\"type\":\"post\"}", required = true) @RequestParam String type,
            @ApiParam(value = "{\"category\":\"category\"}", required = true) @RequestParam(value = "category", required = false) String category, Pageable pageable) {
        return favoriteRSA.listFavoriteFilter(ownerId, type, category, pageable);
       // return favoriteRSA.listFavorite(ownerId, type, pageable);
    }

    @PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
    @GetMapping(value = {"/check"}, params = {"ownerId", "targetId"}, produces = {
        MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Check if item exists in user's favorites")
    public FavoriteStatusDTO checkFavorite(
            @ApiParam(value = "{\"ownerId\":\"ownerId\"}", required = true) @RequestParam String ownerId,
            @ApiParam(value = "{\"targetId\":\"targetId\"}", required = true) @RequestParam String targetId) {

        return favoriteRSA.checkFavorite(ownerId, targetId);
    }

    @PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
    @ApiOperation(value = "Get sorted favorites filtered by firstname or lastname and type", notes = "Get sorted favorites filtered by firstname or lastname and type")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N)", defaultValue = "0")
        ,
	@ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page(1..100).", defaultValue = "15")
        ,
	@ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting criteria in the format: property(,asc|desc). "
                + "Default sort order is ascending. " + "Multiple sort criteria are supported.")})

    @GetMapping(value = {"/listFavorites"}, produces = {
        MediaType.APPLICATION_JSON_VALUE})
    public FavoritesDTO listFavorites(
            @RequestParam(value = "type", required = true) String type,
            @RequestParam(value = "filter", required = false) String filter,
            @RequestParam(value = "categorie", required = false) String categorie,
            Pageable pageable) {

        return favoriteRSA.listFavoriteFiltered(type, filter, categorie, pageable);
    }

}
