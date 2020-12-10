package com.arkeup.link_innov.gestion_profil_mcs.service.businessdelegate;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.AnnounceDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ListIdDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.CustomPageDTO;
import org.springframework.data.domain.Pageable;

@FeignClient(name = "AnnounceClient", url = "#{'${announce.mcs.url}'}")
public interface AnnounceMCS {

    /**
     *
     * @param announceIds
     * @param page
     * @param size
     * @return
     */
    @PutMapping(path = "/announce/getAnnounces")
    public CustomPageDTO<AnnounceDTO> getAnnounces(@RequestBody ListIdDTO announceIds, @RequestParam int page, @RequestParam int size);

    @PutMapping(path = "/announce/getPaginatedAnnounces")
    public CustomPageDTO<AnnounceDTO> getPaginatedAnnounces(
            @RequestBody ListIdDTO announceIds,
            @RequestParam String filter,
            @RequestParam String categorie,
            @RequestParam Pageable pageable);
}
