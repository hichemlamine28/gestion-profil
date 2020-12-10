package com.arkeup.link_innov.gestion_profil_mcs.service.businessdelegate;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.MapMediaDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.MediaDTO;

import java.util.List;

/**
 *
 * @author bona
 */
@FeignClient(name = "MediaClient", url = "#{'${gestion-media.mcs.url}'}/media")
public interface MediaMCS {

	@GetMapping(path = "/get")
	public MediaDTO getMedia(@RequestParam("token") String mediaId);

	@DeleteMapping(path = "/picture/delete", params = { "mediaId" })
	public Boolean deletePicture(@RequestParam(name = "mediaId") String mediaId);

	@DeleteMapping(path = "/delete", params = { "token" })
	public MediaDTO deleteMedia(@RequestParam("token") String mediaId);

	@PostMapping(path = "/profil/picture/upload", consumes = { "multipart/form-data" })
	public MediaDTO updatePictureProfil(@RequestParam("token") String mediaId,
			@RequestParam("file") MultipartFile multipartFile);

	@PostMapping(path = "/profil/picture/token", params = { "ownerId", "type" })
	public MediaDTO generateProfilPictureToken(@RequestParam(name = "ownerId") String ownerId,
			@RequestParam(name = "type") String type);

	@GetMapping(path = "/get/public")
	public MediaDTO getPublicMedia(@RequestParam("token") String mediaId);

	@ApiOperation(value = "list has media")
	@PostMapping(path = "/listHasMedia", produces = { MediaType.APPLICATION_JSON_VALUE })
	@CrossOrigin(origins = "*")
	public List<String> listHasMedia(@RequestBody List<MapMediaDTO> mapMediaDTOS);

	@GetMapping(path = "/get/user/picture")
	public MediaDTO findPictureByUserId(@RequestParam("ownerId") String ownerId);

	@PutMapping(path = "/update", produces = { MediaType.APPLICATION_JSON_VALUE })
	@CrossOrigin(origins = "*")
	public MediaDTO updateMedia(@RequestBody MediaDTO mediaDTO);

	@PostMapping(path = "/profil/picture/upload", produces = { MediaType.APPLICATION_JSON_VALUE })
	@CrossOrigin(origins = "*")
	public MediaDTO uploadMedia(@RequestParam("token") String token, @RequestParam("file") MultipartFile multipartFile);

	@PostMapping(path = "/profil/picture/uploadData", produces = { MediaType.APPLICATION_JSON_VALUE })
	@CrossOrigin(origins = "*")
	public MediaDTO uploadMediaData(@RequestParam("token") String token, @RequestParam("file") String base64);

	@PostMapping(value = "/profil/file/uploadData", produces = { MediaType.APPLICATION_JSON_VALUE })
	public MediaDTO uploadFile(@RequestHeader("Authorization") String token, @RequestParam("token") String fileToken,
			@RequestParam("file") String base64File, @RequestParam("fileName") String fileName);

	@PostMapping(value = "/token", params = { "type" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public MediaDTO generateMediaToken(@RequestHeader("Authorization") String token, @RequestParam("type") String type);
	
	@PostMapping(path = "/profil/picture/uploadData/anonymous", produces = { MediaType.APPLICATION_JSON_VALUE })
	@CrossOrigin(origins = "*")
	public MediaDTO anonymousUploadMediaData(@RequestParam("token") String token, @RequestParam("file") String base64);

	@PostMapping(path = "/profil/picture/token/anonymous", params = { "ownerId", "type" })
	public MediaDTO anonymousGenerateProfilPictureToken(@RequestParam(name = "ownerId") String ownerId,
			@RequestParam(name = "type") String type);
	
	@GetMapping(path = "/get/user/picture/anonymous")
	public MediaDTO anonymousFindPictureByUserId(@RequestParam("ownerId") String ownerId);
}
