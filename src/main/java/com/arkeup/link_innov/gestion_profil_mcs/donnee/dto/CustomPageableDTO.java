package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto;

/**
 * 
 * @author Jéremy
 *
 */
public class CustomPageableDTO {
	
	private CustomSortDTO sort;
	private Integer pageSize;
	private Integer pageNumber;
	private Long offset;
	private Boolean unpaged;
	private Boolean paged;
	
	public CustomSortDTO getSort() {
		return sort;
	}
	public void setSort(CustomSortDTO sort) {
		this.sort = sort;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	public Long getOffset() {
		return offset;
	}
	public void setOffset(Long offset) {
		this.offset = offset;
	}
	public Boolean isUnpaged() {
		return unpaged;
	}
	public void setUnpaged(Boolean unpaged) {
		this.unpaged = unpaged;
	}
	public Boolean isPaged() {
		return paged;
	}
	public void setPaged(Boolean paged) {
		this.paged = paged;
	}
	
	
	
}
