package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.referential;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.NumberOfEmployee;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.NumberOfEmployeeDTO;

@Mapper
public interface NumberOfEmployeeMapper {
	
	NumberOfEmployeeDTO numberOfEmployeeToNumberOfEmployeeDTO(NumberOfEmployee numberOfEmployee);
	NumberOfEmployee numberOfEmployeeDTOToNumberOfEmployee(NumberOfEmployeeDTO numberOfEmployeeDTO);
	List<NumberOfEmployeeDTO> numberOfEmployeeToNumberOfEmployeeDTOs(List<NumberOfEmployee> numberOfEmployees);
	List<NumberOfEmployee> numberOfEmployeeDTOToNumberOfEmployees(List<NumberOfEmployeeDTO> numberOfEmployeeDTOs);
	
	NumberOfEmployeeMapper MAPPER = Mappers.getMapper(NumberOfEmployeeMapper.class);
	
	default Page<NumberOfEmployeeDTO> numberOfEmployeePageToNumberOfEmployeeDTOPage(Page<NumberOfEmployee> numberOfEmployeePage, Pageable pageable) {

		List<NumberOfEmployeeDTO> numberOfEmployeeDtos = MAPPER.numberOfEmployeeToNumberOfEmployeeDTOs(numberOfEmployeePage.getContent());
		Page<NumberOfEmployeeDTO> numberOfEmployeeDTOPage = new PageImpl<>(numberOfEmployeeDtos, pageable, numberOfEmployeePage.getTotalElements());
		return numberOfEmployeeDTOPage;
	}

}
