package com.arkeup.link_innov.gestion_profil_mcs.service.businessdelegate;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.RoomDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.CustomPageDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "chatMCS", url = "#{'${chat-mcs.url}'}")
public interface ChatMCS {

    @ApiOperation(value = "Add new admin room")
    @PutMapping(path = "/room/addAdmin/{admin_id}", consumes = {"application/json"})
    @CrossOrigin(origins = "*")
    public RoomDTO addAdminRoom(@PathVariable("admin_id") String adminId, @RequestBody RoomDTO roomDTO);

    @ApiOperation(value = "Add admins list at room")
    @PutMapping(path = "/room/updateAdmins", consumes = {"application/json"})
    @CrossOrigin(origins = "*")
    public RoomDTO updateAdmins(@RequestBody RoomDTO roomDTO);

    @ApiOperation(value = "Get rooms by id account and admin")
    @GetMapping(path = "/room/account/{id}/admin/{admin_id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @CrossOrigin(origins = "*")
    public CustomPageDTO<RoomDTO> findRoomByAccountId(@PathVariable("id") String id, @PathVariable("admin_id") String adminId, int page);

}
