package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto;

import java.util.List;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.businessdelegate.ReseauSocialUserDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;

/**
 * 
 * @author Jéremy
 *
 */
public class PageContactsDTO extends BaseDTO {

	private List<ReseauSocialUserDTO> content;

	private CustomPageableDTO pageable;

	private Integer totalPages;

	private Long totalElements;

	private Boolean last;

	private Integer numberOfElements;

	private Integer size;

	private Integer number;

	private Boolean first;

	public List<ReseauSocialUserDTO> getContent() {
		return content;
	}

	public void setContent(List<ReseauSocialUserDTO> content) {
		this.content = content;
	}

	public CustomPageableDTO getPageable() {
		return pageable;
	}

	public void setPageable(CustomPageableDTO pageable) {
		this.pageable = pageable;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}

	public Boolean getLast() {
		return last;
	}

	public void setLast(Boolean last) {
		this.last = last;
	}

	public Integer getNumberOfElements() {
		return numberOfElements;
	}

	public void setNumberOfElements(Integer numberOfElements) {
		this.numberOfElements = numberOfElements;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Boolean getFirst() {
		return first;
	}

	public void setFirst(Boolean first) {
		this.first = first;
	}

}
